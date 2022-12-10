package com.fql.service.tasks;

import com.fql.entity.EsNewsEntity;
import com.fql.entity.NewsModel;
import com.fql.repository.es.NewsEsRepository;
import com.fql.repository.jpa.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 樊乾浪
 * @date 2022/12/7 15:35
 * @company xxx
 * 定时同步新闻数据到es
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class TaskNewsDataEs {
    @Resource
    private NewsEsRepository newsEs ;
    @Resource
    private NewsRepository newsRepo;

    /**
     * 删除es的新闻数据
     * 在重数据库读取以后重新写入
     * 2 分钟一次执行
     */
//    @Scheduled(cron = "0 0/2 * * * ? ")
    private void flushedNewsData(){
/*        log.info("定时清洗新闻数据开始");
        long l = System.currentTimeMillis();
        newsEs.deleteAll();
        List<NewsModel> all = newsRepo.findAll();
        newsEs.saveAll();
        log.info("定时任务刷新新闻数据结束 耗时：{}",System.currentTimeMillis()-l);*/
    }

}
