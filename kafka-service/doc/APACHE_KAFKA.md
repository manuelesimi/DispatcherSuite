## Install Kafka on MacOs
Install with HomeBrew:
~~~
  > sudo chown -R $(whoami) /usr/local/var/homebrew
  > brew cask install java
  > brew install kafka
~~~
## Configuration
### Zookeeper

Edit the configuration file at: 
~~~    
/usr/local/etc/kafka/zookeeper.properties
~~~
and set the client port as follows:
~~~
# the port at which the clients will connect
clientPort=2181
~~~
### Kafka Server
Edit the configuration file at:
~~~
/usr/local/etc/kafka/server.properties
~~~
and set the listeners to the following value:
~~~
############################# Socket Server Settings #############################

# The address the socket server listens on. 
#   FORMAT:
#     listeners = listener_name://host_name:port
#   EXAMPLE:
#     listeners = PLAINTEXT://your.host.name:9092
listeners=PLAINTEXT://localhost:9092
~~~


## Start 
Now start Zookeeper and then Kafka:
~~~
> zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
> kafka-server-start /usr/local/etc/kafka/server.properties
~~~

## Create a Topic
~~~
> kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 1 --partitions 1 \
--topic oncorseq_sequencing_in_progress

  Created topic "oncorseq_sequencing_in_progress".
~~~


## Delete a Topic
~~~
> kafka-topics --delete --zookeeper localhost:2181 --topic oncorseq_sequencing_in_progress
Topic oncorseq.sequencing.in_progress is marked for deletion.
~~~

It needs the following setting set to true in _/usr/local/etc/kafka/server.properties_: 
~~~
# Switch to enable topic deletion or not, default value is false
delete.topic.enable=tru
~~~
## List all Topics

~~~
> kafka-topics --list --zookeeper localhost:2181 
oncorseq_sequencing_pipeline_initialized
oncorseq_sequencing_analysis_started
oncorseq_sequencing_in_progress
~~~
