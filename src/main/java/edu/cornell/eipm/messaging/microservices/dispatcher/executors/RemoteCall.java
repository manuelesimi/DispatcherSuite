package edu.cornell.eipm.messaging.microservices.dispatcher.executors;

import edu.cornell.eipm.messaging.microservices.dispatcher.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * A call to a remote service.
 *
 * @author Manuele Simi
 */
public class RemoteCall extends BaseExecutor {

    private final Logger logger = LoggerFactory.getLogger(RemoteCall.class);

    RestTemplate restTemplate = new RestTemplate();

    public RemoteCall(Action action) {
        this.setAction(action);
    }

    @Override
    protected boolean run(String url) {
        logger.info("Remote call to: {}", url );
        // Fire the request.
        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        logger.info("Remote Call Dispatched");
        logger.debug(response.getBody());
        return  response.getStatusCode() == HttpStatus.OK;
    }

}
