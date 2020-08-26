package cn.com.nanfeng.boot.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author liutao
 * @date 2020-08-25 16:48
 */
public class NLineMapper extends Mapper<LongWritable, Text,Text,LongWritable> {
    private Text k = new Text();
    private LongWritable v = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取一行
        String line = value.toString();
        //切割
        String[] split = line.split(" ");
        //循环写出
        for (int i = 0; i < split.length; i++) {
            k.set(split[i]);
        }
        context.write(k,v);

    }
}
