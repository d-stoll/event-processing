package de.dstoll.sparkjobs

import de.dstoll.sparkjobs.query.{FilterGermanMeetups, FilterMunichMeetups, Query}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import de.dstoll.sparkjobs.source.KafkaSource._
import de.dstoll.sparkjobs.util.SchemaProvider._
import de.dstoll.sparkjobs.sink.KafkaSink._

object MeetupJob {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Meetup job")
      .master("spark://spark-master:7077")
      .getOrCreate()

    val schema = inferSchema(spark)
    val meetupDF = readFromKafka(spark, "meetups", schema)

    val queries = Map(
      "germanMeetups" -> new FilterGermanMeetups,
      "munichMeetups" -> new FilterMunichMeetups
    )

    queries.foreach { case (topic, query) => writeToKafka(topic, query.transform(meetupDF), col("id")) }

    spark.streams.awaitAnyTermination()
  }

}
