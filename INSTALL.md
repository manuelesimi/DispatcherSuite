## Install Kafka on MacOs
Install with HomeBrew:
~~~
  > sudo chown -R $(whoami) /usr/local/var/homebrew
  > brew cask install java
  > brew install kafka
~~~

Edit the configuration files at: 
~~~    
/usr/local/etc/kafka/zookeeper.properties:
/usr/local/etc/kafka/server.properties
~~~

Now start Zookeeper and then Kafka:
~~~
> zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
> kafka-server-start /usr/local/etc/kafka/server.properties
~~~

## Create a Topic
~~~
> kafka-topics --create --zookeeper localhost:2181 \
--replication-factor 1 --partitions 1 \
--topic oncorseq.sequencing.in_progress
  WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
 
  Created topic "oncorseq.sequencing.in_progress".
~~~