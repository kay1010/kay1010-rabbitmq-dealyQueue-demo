package fkk.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
public class tryServiceImp implements TryService {
    private static Logger logger= LoggerFactory.getLogger(tryServiceImp.class);
    private int count;
    @Retryable(value = Exception.class,maxAttempts = 5,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public void cacheProduct() throws Exception {
            count++;
            logger.info("----cacheProduct------start----"+count);
            int res= 1/0;
            logger.info("减库存成功");
    }

    @Override
    @Async
    public void doTask(int i) throws Exception {

        try {
            logger.info(Thread.currentThread().getName()+" -----dotask-----start---");
            if(i==6){
                System.out.println(i/0);
            }
            Thread.sleep(1000);
            logger.info(Thread.currentThread().getName()+" -----dotask-----end---");
        }catch (Exception e){
           System.out.println( e.getMessage());
        }


    }

    @Recover
    public void recover(Exception e) {
        logger.warn("减库存失败！！！" + LocalTime.now());
    }

}
