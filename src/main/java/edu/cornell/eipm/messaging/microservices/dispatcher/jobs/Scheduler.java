package edu.cornell.eipm.messaging.microservices.dispatcher.jobs;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import edu.cornell.eipm.messaging.microservices.dispatcher.config.SchedulerConfigurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Scheduler for configured jobs.
 *
 * @author Manuele Simi
 */
@Configuration
@Component
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private SchedulerConfigurations schedulerConfigurations;

    @Scheduled(fixedRateString = "#{'${scheduler.cron1.rate}'}")
    public void cronJob1() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron1 job started at: " + strDate);
        Objects.requireNonNull(schedulerConfigurations);
        for (Action action : schedulerConfigurations.getCron(1).getActions()) {
            logger.info("About to launch: " + action.getTrigger());
        }
    }

    @Scheduled(fixedRateString = "#{'${scheduler.cron2.rate}'}")
    public void cronJob2() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron2 job started at: " + strDate);
        Objects.requireNonNull(schedulerConfigurations);
        for (Action action : schedulerConfigurations.getCron(2).getActions()) {
            logger.info("About to launch: " + action.getTrigger());
        }
    }

    @Scheduled(fixedRateString = "#{'${scheduler.cron3.rate}'}")
    public void cronJob3() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron3 job started at: " + strDate);
        Objects.requireNonNull(schedulerConfigurations);
        for (Action action : schedulerConfigurations.getCron(3).getActions()){
            logger.info("About to launch: " + action.getTrigger());
        }
    }
}
