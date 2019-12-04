package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.cron.Cron;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @author Manuele Simi
 */
@Component
@Validated
@Configuration
@ConfigurationProperties("scheduler")
public class SchedulerConfigurations {

    Cron cron1;
    Cron cron2;
    Cron cron3;

    public void setCron1(Cron cron1) {
        this.cron1 = cron1;
    }

    public void setCron2(Cron cron2) {
        this.cron2 = cron2;
    }

    public void setCron3(Cron cron3) {
        this.cron3 = cron3;
    }

    public Cron getCron(int number) {
        Cron selected = null;
        switch (number) {
            case 1:
                selected = cron1;
                break;
            case 2:
                selected = cron2;
                break;
            case 3:
                selected= cron3;
                break;
        }

        return selected;
    }

}
