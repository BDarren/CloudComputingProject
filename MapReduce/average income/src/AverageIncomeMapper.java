

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageIncomeMapper extends
		Mapper<LongWritable, Text, Text, IntArrayWritable> {

	private static final String MISSING = "Not in universe";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] person = line.split(",");
		IntWritable[] result = new IntWritable[2];

		String wage = person[5].trim();
		String capitalgain = person[16].trim();
		String capitalloss = person[17].trim();
		String weeks = person[39].trim();
		String state = person[21].trim();
		
		int wageperhour = Integer.parseInt(wage);
		int gain = Integer.parseInt(capitalgain);
		int loss = Integer.parseInt(capitalloss);
		int workweeks = Integer.parseInt(weeks);
		
		int income = wageperhour*35*workweeks+gain-loss;
		
		System.out.println(income);

		result[0] = new IntWritable(1);
		result[1] = new IntWritable(income);

		IntArrayWritable res = new IntArrayWritable();
		res.set(result);
		if (!state.equals(MISSING)&&!state.equals("?")) {
			context.write(new Text(state), res);
		}
	}
}
