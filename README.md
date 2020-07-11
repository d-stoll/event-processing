# Event processing course project

We are using Kafka + Spark Structured Streaming for our solution.


### Project overview ###

- _ep-dashboard_: Visualizes the events of different Kafka topics in a webapp. Reads the given "events.json" data and 
produces Kafka events in the topic "meetups". 
- _ep-kafka_: Kafka distribution, which is started and stopped via gradle tasks.
- _ep-spark_: Spark job, which reads events from the Kafka topic "meetups" and transforms them with various queries 
and finally writes the result back into Kafka topics.

### Setup Kafka ###

Open 2 terminals. Start zookeeper in the first one:
```shell script
./ep-kafka/zookeeper.sh
```

Start kafka in the second one:
```shell script
./ep-kafka/kafka.sh
```

### Setup dashboard application ###

In a new terminal, start the dashboard application:

```shell script
./gradlew :ep-dashboard:bootRun
```

### Setup Spark job ###

Finally, open a new terminal and submit the Spark job:

```shell script
./gradlew :ep-spark:run
```
