

import java.io.IOException;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

public class RaceRateReducer extends 
		Reducer<Text, ArrayWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<ArrayWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int whiteEducated = 0;
		int blackEducated = 0;
		int indianEducated = 0;
		int asianEducated = 0;
		int otherEducated = 0;
		int white = 0;
		int black = 0;
		int indian = 0;
		int asian = 0;
		int other = 0;
		
		for (ArrayWritable val : values) {
			Writable[] writables = val.get();
			IntWritable whiteEducatedNum = (IntWritable)writables[0];
			IntWritable blackEducatedNum = (IntWritable)writables[1];
			IntWritable indianEducatedNum = (IntWritable)writables[2];
			IntWritable asianEducatedNum = (IntWritable)writables[3];
			IntWritable otherEducatedNum = (IntWritable)writables[4];
			IntWritable whitenum = (IntWritable)writables[5];
			IntWritable blacknum = (IntWritable)writables[6];
			IntWritable indiannum = (IntWritable)writables[7];
			IntWritable asiannum = (IntWritable)writables[8];
			IntWritable othernum = (IntWritable)writables[9];
			whiteEducated += whiteEducatedNum.get();
			blackEducated += blackEducatedNum.get();
			indianEducated += indianEducatedNum.get();
			asianEducated += asianEducatedNum.get();
			otherEducated += otherEducatedNum.get();
			white += whitenum.get();
			black += blacknum.get();
			indian += indiannum.get();
			asian += asiannum.get();
			other += othernum.get();
		}
		//System.out.println(educated+" "+sum);
		String rate = "Educated ratio White : Black : Amer Indian Aleut or Eskimo : Asian or Pacific Islander : Other  " + 
		Integer.toString(whiteEducated)+":"+Integer.toString(blackEducated)+":"+Integer.toString(indianEducated)+
				":"+Integer.toString(asianEducated)+":"+Integer.toString(otherEducated)+
				"   Population Ratio White : Black : Amer Indian Aleut or Eskimo : Asian or Pacific Islander : Other: " +
				Integer.toString(white)+":"+Integer.toString(black)+":"+Integer.toString(indian)+
				":"+Integer.toString(asian)+":"+Integer.toString(other);
		context.write(key, new Text(rate));
	}
	
}
