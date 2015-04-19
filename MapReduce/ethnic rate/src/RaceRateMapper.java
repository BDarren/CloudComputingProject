

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RaceRateMapper extends
		Mapper<LongWritable, Text, Text, IntArrayWritable> {

	private static final String MISSING = "Not in universe";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] person = line.split(",");
		IntWritable[] ratio = new IntWritable[10];
		for(int i=0; i< 10; i++){
			ratio[i] = new IntWritable(0);
		}
		String region = person[20].trim();
		String race = person[10].trim();
		String education = person[4].trim();
		//race: White, Black, Other, Amer Indian Aleut or Eskimo, Asian or Pacific Islander.
		if(race.equals("White")){
			if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
					||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
					||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
					||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
				ratio[0] = new IntWritable(1);			
			}
			ratio[5] = new IntWritable(1);			
		}
		else if(race.equals("Black")){
			if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
					||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
					||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
					||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
				ratio[1] = new IntWritable(1);			
			}
			ratio[6] = new IntWritable(1);
		}
		else if(race.equals("Amer Indian Aleut or Eskimo")){
			if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
					||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
					||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
					||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
				ratio[2] = new IntWritable(1);			
			}
			ratio[7] = new IntWritable(1);
		}
		else if(race.equals("Asian or Pacific Islander")){
			if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
					||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
					||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
					||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
				ratio[3] = new IntWritable(1);			
			}
			ratio[8] = new IntWritable(1);
		}
		else if(race.equals("Other")){
			if(education.equals("Bachelors degree(BA AB BS)")||education.equals("Some college but no degree")
					||education.equals("Masters degree(MA MS MEng MEd MSW MBA)")||education.equals("Associates degree-occup /vocational")
					||education.equals("Associates degree-academic program")||education.equals("Doctorate degree(PhD EdD)")
					||education.equals("Prof school degree (MD DDS DVM LLB JD)")){
				ratio[4] = new IntWritable(1);			
			}
			ratio[9] = new IntWritable(1);
		}

		IntArrayWritable res = new IntArrayWritable();
		res.set(ratio);
		if (!region.equals(MISSING)&&!region.equals("?")) {
			context.write(new Text(region), res);
		}
	}
}
