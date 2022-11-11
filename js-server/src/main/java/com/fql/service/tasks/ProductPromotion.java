package com.fql.service.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qian
 */
@Service
public class ProductPromotion {
    private SimpleDateFormat  format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    private static Logger log = (Logger) LoggerFactory.getLogger(ProductPromotion.class);

    @Scheduled(fixedRate = 5000)
    public void send(){
        log.debug("The time is now {}", format.format(new Date()));
    }

    @Scheduled(cron = "8/10 * * * * *  ")//启动8秒后每10s输出一次
    public void reportCurrentTimeByCron() {
        log.debug("cron => The time is now {}", format.format(new Date()));
    }



}
