package cn.com.nanfeng.boot.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author liutao
 * @date 2020-08-25 16:34
 */
public class KVTextMapper extends Mapper<Text,Text,Text, LongWritable> {

    //设置value
    LongWritable v = new LongWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        context.write(key,v);
    }
}
