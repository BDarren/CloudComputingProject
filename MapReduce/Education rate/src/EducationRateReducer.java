

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class EducationRateReducer extends 
		Reducer<Text, ArrayWritable, Text, DoubleWritable> {

	@Override
	public void reduce(Text key, Iterable<ArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		
		double educated = 0;
		double sum = 0;
		double rate = 0;
		
		for (ArrayWritable val : values) {
			Writable[] writables = val.get();
			IntWritable education = (IntWritable)writables[0];
			IntWritable num = (IntWritable)writables[1];
			educated += education.get();
			sum += num.get();
		}
		//System.out.println(educated+" "+sum);
		rate = educated/sum;
		context.write(key, new DoubleWritable(rate));
	}
	
}
