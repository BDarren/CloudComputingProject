

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SexRateMapper extends
		Mapper<LongWritable, Text, Text, IntArrayWritable> {

	private static final String MISSING = "Not in universe";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] person = line.split(",");
		IntWritable[] ratio = new IntWritable[2];
		for(int i=0; i< 2; i++){
			ratio[i] = new IntWritable(0);
		}
		String state = person[21].trim();
		String sex = person[12].trim();

		//sex: Male, Female
		if(sex.equals("Male")){
			ratio[0] = new IntWritable(1);			
		}
		else if(sex.equals("Female")){
			ratio[1] = new IntWritable(1);
		}

		IntArrayWritable res = new IntArrayWritable();
		res.set(ratio);
		if (!state.equals(MISSING)&&!state.equals("?")) {
			context.write(new Text(state), res);
		}
	}
}
