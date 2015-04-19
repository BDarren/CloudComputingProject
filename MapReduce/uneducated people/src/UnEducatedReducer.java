

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class UnEducatedReducer extends 
		Reducer<Text, ArrayWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterable<ArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int uneducated = 0;

		
		for (ArrayWritable val : values) {
			Writable[] writables = val.get();
			IntWritable education = (IntWritable)writables[0];
			uneducated += education.get();
		}
		//System.out.println(educated+" "+sum);
		context.write(key, new IntWritable(uneducated));
	}
	
}
