

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class SexRateReducer extends 
		Reducer<Text, ArrayWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<ArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int male = 0;
		int female = 0;
		
		for (ArrayWritable val : values) {
			Writable[] writables = val.get();
			IntWritable maleNum = (IntWritable)writables[0];
			IntWritable femaleNum = (IntWritable)writables[1];

			male += maleNum.get();
			female += femaleNum.get();
		}
		//System.out.println(educated+" "+sum);
		String rate = Integer.toString(male)+ ":"+Integer.toString(female);
		context.write(key, new Text(rate));
	}
	
}
