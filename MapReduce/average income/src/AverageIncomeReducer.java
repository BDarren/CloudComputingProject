

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageIncomeReducer extends 
		Reducer<Text, ArrayWritable, Text, DoubleWritable> {

	@Override
	public void reduce(Text key, Iterable<ArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		
		double totalnum = 0;
		double totalincome = 0;
		double average = 0;
		
		for (ArrayWritable val : values) {
			Writable[] writables = val.get();
			IntWritable Num = (IntWritable)writables[0];
			IntWritable incomeNum = (IntWritable)writables[1];

			totalnum += Num.get();
			totalincome += incomeNum.get();
		}
		//System.out.println(educated+" "+sum);
		
		average = totalincome/totalnum;
		context.write(key, new DoubleWritable(average));
	}
	
}
