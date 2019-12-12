package edu.cornell.eipm.messaging.microservices.dispatcher.config;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.scheduler.FixedRate;
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

    FixedRate fixedRate1;
    FixedRate fixedRate2;
    FixedRate fixedRate3;

    public void setFixedRate1(FixedRate fixedRate1) {
        this.fixedRate1 = fixedRate1;
    }

    public void setFixedRate2(FixedRate fixedRate2) {
        this.fixedRate2 = fixedRate2;
    }

    public void setFixedRate3(FixedRate fixedRate3) {
        this.fixedRate3 = fixedRate3;
    }


    public FixedRate getRate(int number) {
        FixedRate selected = null;
        switch (number) {
            case 1:
                selected = fixedRate1;
                break;
            case 2:
                selected = fixedRate2;
                break;
            case 3:
                selected= fixedRate3;
                break;
        }

        return selected;
    }

}
