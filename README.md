# Event processing course project

We are using Kafka + Spark Structured Streaming for our solution.


Project overview:
- _kafka-producer_: Reads the given "events.json" data and produces Kafka events in the topic "meetups"

### Project setup

1. Make sure you have docker and docker-compose installed on your system.
2. Clone the repository and execute:

```bash
docker-compose up -d
```

### Setup Apache Zeppelin 

1. Go to localhost:8085 to open Zeppelin
2. Click on "anonymous" in the top right corner
3. Click on "Interpreter"
4. Search for the Spark interpreter
5. In the Spark interpreter window, click on "Edit" in the top right corner
6. Scroll to the bottom of the page and add the artifact "org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.4" in the dependencies section

