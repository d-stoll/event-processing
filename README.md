# Event processing course project

We are using Kafka + Spark Structured Streaming for our solution.


### Project overview ###

- _ep-dashboard_: Visualizes the events of different Kafka topics in a webapp. Reads the given "events.json" data and 
produces Kafka events in the topic "meetups". 
- _ep-spark_: Spark job, which reads events from the Kafka topic "meetups" and transforms them with various queries 
and finally writes the result back into Kafka topics.

### Setup Kafka & Dashboard ###

Start Zookeeper, a Kafka broker and the dashboard application with:
```shell script
./gradlew :ep-dashboard:bootBuildImage
docker-compose up -d
```

### Setup Spark job ###

Submit the Spark job with:
```shell script
./gradlew :ep-spark:run
```
