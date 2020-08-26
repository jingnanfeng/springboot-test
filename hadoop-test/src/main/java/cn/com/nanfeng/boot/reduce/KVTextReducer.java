package cn.com.nanfeng.boot.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author liutao
 * @date 2020-08-25 16:36
 */
public class KVTextReducer extends Reducer<Text, LongWritable,Text,LongWritable> {

    LongWritable v = new LongWritable();

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0L;
        //词汇统计
        for (LongWritable value : values) {
            sum += value.get();
        }
        v.set(sum);
        //输出
        context.write(key,v);
    }
}
