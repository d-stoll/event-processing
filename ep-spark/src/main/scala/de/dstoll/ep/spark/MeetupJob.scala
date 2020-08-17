package de.dstoll.ep.spark

import de.dstoll.ep.spark.config.{AppConfig, KafkaConfig}
import de.dstoll.ep.spark.query.Query
import de.dstoll.ep.spark.sink.KafkaSink
import de.dstoll.ep.spark.source.KafkaSource
import de.dstoll.ep.spark.util.SchemaProvider._
import org.apache.spark.sql.SparkSession
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object MeetupJob {

  def main(args: Array[String]): Unit = {

    val appConfig = ConfigSource.default.loadOrThrow[AppConfig]
    val kafkaConfig = ConfigSource.default.at("kafka-config").loadOrThrow[KafkaConfig]

    val spark = SparkSession.builder()
      .appName("Meetup job")
      .master("local[*]")
      .getOrCreate()
    val schema = inferSchema(spark)

    val source = new KafkaSource(kafkaConfig)
    val sink = new KafkaSink(kafkaConfig)

    val meetupDF = source.read("meetups", schema)

    appConfig.queryConfigs.map(_.queryInstance).foreach(q => {
      sink.write(q.topic, q.transform (meetupDF), q.outputMode, q.trigger)
    })

    spark.streams.awaitAnyTermination()
  }
}
