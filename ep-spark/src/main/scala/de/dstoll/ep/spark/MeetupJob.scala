package de.dstoll.ep.spark

import de.dstoll.ep.spark.query.{GermanMeetups, Heatmap, MunichMeetups, TopK}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import de.dstoll.ep.spark.source.KafkaSource._
import de.dstoll.ep.spark.util.SchemaProvider._
import de.dstoll.ep.spark.sink.KafkaSink._

object MeetupJob {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Meetup job")
      .master("local[*]")
      .getOrCreate()

    val schema = inferSchema(spark)
    val meetupDF = readFromKafka("meetups", schema)

    val queries =
      new GermanMeetups ::
      new MunichMeetups ::
      new TopK ::
      new Heatmap :: Nil

    queries.foreach { query => writeToKafka(query.topic, query.transform(meetupDF), query.outputMode) }

    spark.streams.awaitAnyTermination()
  }

}
