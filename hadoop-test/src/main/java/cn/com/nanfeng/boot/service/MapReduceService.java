package cn.com.nanfeng.boot.service;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;

/**
 *单词统计
 * @author liutao
 * @date 2020-08-24 17:59
 */
@Service
public class MapReduceService {

    @Resource
    private HdfsService hdfsService;
    @Resource
    private ReduceJobsUtils reduceJobsUtils;

    /**
     * 默认reduce输出目录
     */
    private static final String OUTPUT_PATH = "/output";

    public void wordCount(String jobName,String inputPath) throws Exception{
        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(inputPath)){
            return;
        }
        //输出目录
        String outputPath ="hdfs://hadoop1:9000/"+OUTPUT_PATH + "/" + jobName;
        if (hdfsService.existFile(outputPath)){
            hdfsService.deleteFile(outputPath);
        }
        reduceJobsUtils.getWrodCountJobsConf(jobName,outputPath,inputPath);
    }
}
