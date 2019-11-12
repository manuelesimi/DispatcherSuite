Running Kakfa-Dispatcher with Docker
---
Kafka-Dispatcher can run inside its Docker image (_eipm/kafka-dispatcher_)

You can start a container with an instance of the dispatcher as follows:

    docker run -p 8080:8080 --rm --network=host \
        -e HOST_HOSTNAME=`hostname` \
        -e HOST_USER=$LOGNAME \
        -v /home/mas2182/.ssh/:/ssh/ \
        -v /home/mas2182/application.yml:/config/application.yml eipm/kafka-dispatcher:latest
 
where 
* 8080 is the port configured for the Dispatcher (see [CONFIGURATION](CONFIGURATION.md))
* the file mounted as _/config/application.yml_ is the YAML configuration file (see [CONFIGURATION](CONFIGURATION.md))
* the folder mounted under _/ssh_ must include a public key (_id_rsa.pub_) authorized to access the hosting machine (this is needed to execute local actions)

If the configuration passed to the instance is correct, you should see the following output:

~~~
Using config file at /home/mas2182/application.yml

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.0.RELEASE)

21:26:23.405 [main] INFO  e.c.e.m.m.dispatcher.DispatcherApp - Starting DispatcherApp v1.0 on xits-ipmhpcd01.med.cornell.edu with PID 1 (/dispatcher-1.0.war started by root in /)
21:26:23.408 [main] DEBUG e.c.e.m.m.dispatcher.DispatcherApp - Running with Spring Boot v2.2.0.RELEASE, Spring v5.2.0.RELEASE
21:26:23.409 [main] INFO  e.c.e.m.m.dispatcher.DispatcherApp - No active profile set, falling back to default profiles: default
21:26:24.533 [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer - Tomcat initialized with port(s): 8080 (http)
21:26:25.490 [main] INFO  o.s.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 2022 ms
21:26:25.994 [main] INFO  o.s.s.c.ThreadPoolTaskExecutor - Initializing ExecutorService 'applicationTaskExecutor'
21:26:26.421 [main] INFO  o.s.s.c.ThreadPoolTaskScheduler - Initializing ExecutorService
21:26:26.450 [main] INFO  o.s.b.w.e.tomcat.TomcatWebServer - Tomcat started on port(s): 8080 (http) with context path '/dispatcher'
21:26:26.453 [main] INFO  e.c.e.m.m.dispatcher.DispatcherApp - Started DispatcherApp in 3.528 seconds (JVM running for 4.879)
~~~

