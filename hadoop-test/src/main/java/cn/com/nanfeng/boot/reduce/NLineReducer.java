package cn.com.nanfeng.boot.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author liutao
 * @date 2020-08-25 16:59
 */
public class NLineReducer extends Reducer<Text, LongWritable,Text,LongWritable> {
    LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0L;
        for (LongWritable value : values) {
            sum += value.get();
        }
        v.set(sum);
        context.write(key,v);
    }

}
