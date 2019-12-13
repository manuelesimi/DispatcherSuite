package edu.cornell.eipm.messaging.microservices.executors.schedulers;

import edu.cornell.eipm.messaging.microservices.executors.model.scheduler.Scheduler;
import edu.cornell.eipm.messaging.microservices.executors.model.service.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * FixedRateScheduler for configured schedulers.
 *
 * @author Manuele Simi
 */
@Configuration
@Component
public class FixedRateScheduler {

    private final Logger logger = LoggerFactory.getLogger(FixedRateScheduler.class);

    @Autowired
    private Scheduler scheduler;

    //@Scheduled(fixedRateString = "#{'${scheduler.fixedRate1.rate}'}")
    public void rateJob1() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron1 job started at: " + strDate);
        Objects.requireNonNull(scheduler);
        for (Action action : scheduler.getRate(1).getActions()) {
            logger.info("About to launch: " + action.getTrigger());
        }
    }

    //@Scheduled(fixedRateString = "#{'${scheduler.fixedRate2.rate}'}")
    public void rateJob2() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron2 job started at: " + strDate);
        Objects.requireNonNull(scheduler);
        for (Action action : scheduler.getRate(2).getActions()) {
            logger.info("About to launch: " + action.getTrigger());
        }
    }

    //@Scheduled(fixedRateString = "#{'${scheduler.fixedRate3.rate}'}")
    public void rateJob3() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron3 job started at: " + strDate);
        Objects.requireNonNull(scheduler);
        for (Action action : scheduler.getRate(3).getActions()){
            logger.info("About to launch: " + action.getTrigger());
        }
    }
}
