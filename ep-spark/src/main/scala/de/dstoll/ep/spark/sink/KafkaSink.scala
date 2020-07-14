package de.dstoll.ep.spark.sink

import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery, Trigger}
import org.apache.spark.sql.{Column, DataFrame}

import scala.concurrent.duration._

object KafkaSink {

  def writeToKafka(topic: String, df: DataFrame, outputMode: OutputMode): StreamingQuery =
    df.select(to_json(struct(df.columns.map(column):_*)) alias "value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("topic", topic)
      .option("checkpointLocation", s"/tmp/spark/streaming/checkpoints/$topic")
      .outputMode(outputMode)
      .trigger(Trigger.ProcessingTime(1.seconds))
      .start()

}
