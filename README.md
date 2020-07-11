# Event processing course project

We are using Kafka + Spark Structured Streaming for our solution.


### Project overview ###

- _ep-dashboard_: Visualizes the events of different Kafka topics in a webapp. Reads the given "events.json" data and 
produces Kafka events in the topic "meetups". 
- _ep-kafka_: Kafka distribution, which is started and stopped via gradle tasks.
- _ep-spark_: Spark job, which reads events from the Kafka topic "meetups" and transforms them with various queries 
and finally writes the result back into Kafka topics.

### Setup ###

Each component can be started via gradle tasks:

* First, set up Kafka (Replace ./gradlew with ./gradlew.bat on Windows):

```shell script
./gradlew :ep-kafka:setup
```

Open 2 terminals. Start zookeeper in the first one:
```shell script
./ep-kafka/zookeeper.sh
```

Start kafka in the second one:
```shell script
./ep-kafka/kafka.sh
```

* In a new terminal, start the dashboard application:

```shell script
./gradlew :ep-dashboard:bootRun
```

* Finally, open a new terminal and submit the Spark job:

```shell script
./gradlew :ep-spark:run
```

### Clean up ###

When you are finished, run:

```shell script
./gradlew :ep-kafka:stop-kafka
```