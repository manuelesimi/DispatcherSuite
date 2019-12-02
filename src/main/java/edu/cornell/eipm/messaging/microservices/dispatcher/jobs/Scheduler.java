package edu.cornell.eipm.messaging.microservices.dispatcher.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Scheduler for configured jobs.
 *
 * @author Manuele Simi
 */
@Component
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedDelay = 10000)
    public void cronJob() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        logger.info("Java cron job started at: " + strDate);
    }
}
