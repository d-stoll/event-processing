package de.dstoll.ep.spark.sink

import de.dstoll.ep.spark.config.KafkaConfig
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.{OutputMode, StreamingQuery, Trigger}
import org.apache.spark.sql.{Column, DataFrame}

import scala.concurrent.duration._

class KafkaSink(kafkaConfig: KafkaConfig) {

  def write(topic: String, df: DataFrame, outputMode: OutputMode, trigger: Trigger): StreamingQuery =
    df.select(to_json(struct(df.columns.map(column):_*)) alias "value")
      .writeStream
      .queryName(topic)
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaConfig.bootstrapServers)
      .option("topic", topic)
      .option("startingOffset", kafkaConfig.startingOffset)
      .option("checkpointLocation", s"/tmp/spark/streaming/checkpoints/$topic")
      .outputMode(outputMode)
      .trigger(trigger)
      .start()

}
