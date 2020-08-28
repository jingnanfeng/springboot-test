package cn.com.nanfeng.boot.reduce;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author liutao
 * @date 2020-08-27 10:15
 */
public class SequenceFileReducer extends Reducer<Text, BytesWritable,Text,BytesWritable> {
    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
        super.reduce(key, values, context);
    }
}
