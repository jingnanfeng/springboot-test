package cn.com.nanfeng.boot.inputformat;

import com.jcraft.jsch.IO;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * 自定义Record，实现一次读取一个完整文件封装为KV
 * (1) 采用IO流读取一个文件输入到value中，
 * 因为设置了不可切片，最终把所有文件都封装到了value中
 * (2) 获取文件路径信息+名称，并设置key
 * @author liutao
 * @date 2020-08-27 9:38
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {

    private Configuration configuration;
    private FileSplit split;
    private boolean isProgress = true;
    private BytesWritable value = new BytesWritable();
    private Text k = new Text();

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.split = (FileSplit)split;
        configuration = context.getConfiguration();
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
       if (isProgress) {
           //定义缓存区
           byte[] contents = new byte[(int) split.getLength()];
           FileSystem fs = null;
           FSDataInputStream fis = null;
           try {
               //获取系统文件
               Path path = split.getPath();
               fs = path.getFileSystem(configuration);
               //读取文件
               fis = fs.open(path);
               //读取文件内容
               IOUtils.readFully(fis,contents,0,contents.length);
               //输出文件内容
               value.set(contents,0,contents.length);
               //获取文件名称以及路径
               String name = split.getPath().toString();
               //设置输出的key值
               k.set(name);
           }catch (Exception e){
               e.printStackTrace();
           }finally {
               IOUtils.closeStream(fis);
           }
           isProgress = false;
           return true;
       }
       return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return k;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
