package cn.com.nanfeng.boot.controller;

import cn.com.nanfeng.boot.service.MapReduceService;
import cn.com.nanfeng.commit.response.WrapMapper;
import cn.com.nanfeng.commit.response.Wrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MapReduce处理控制层
 * @author liutao
 * @date 2020-08-24 18:21
 */
@RestController
public class MapReduceController {

    @Resource
    private MapReduceService mapReduceService;


    @PostMapping("/hadoop/reduce/maxWeather")
    public Wrapper wordCount(@RequestParam("jobName")String jobName, @RequestParam("inputPath")String inputPath) throws Exception{
        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(inputPath)){
            return WrapMapper.wrap(400,"请求参数为空");
        }
        mapReduceService.wordCount(jobName, inputPath);
        return WrapMapper.wrap(200,"温度同统计成功");
    }

}
