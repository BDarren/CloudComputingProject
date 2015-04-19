

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EducationRateMapper extends
		Mapper<LongWritable, Text, Text, IntArrayWritable> {

	private static final String MISSING = "Not in universe";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] person = line.split(",");
		IntWritable[] ratio = new IntWritable[2];
		String state = person[21].trim();
		String education = person[4].trim();
		//education: Children, 7th and 8th grade, 9th grade, 10th grade, High school graduate, 11th grade, 12th grade no diploma, 5th or 6th grade, Less than 1st grade, Bachelors degree(BA AB BS), 1st 2nd 3rd or 4th grade, Some college but no degree, Masters degree(MA MS MEng MEd MSW MBA), Associates degree-occup /vocational, Associates degree-academic program, Doctorate degree(PhD EdD), Prof school degree (MD DDS DVM LLB JD).
		if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
				||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
				||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
				||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
			ratio[0] = new IntWritable(1);			
		}
		else{
			ratio[0] = new IntWritable(0);
		}
		ratio[1] = new IntWritable(1);
		IntArrayWritable res = new IntArrayWritable();
		res.set(ratio);
		if (!state.equals(MISSING)&&!state.equals("?")) {
			context.write(new Text(state), res);
		}
	}
}
