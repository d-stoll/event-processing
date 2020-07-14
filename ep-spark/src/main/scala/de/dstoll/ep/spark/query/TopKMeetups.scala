package de.dstoll.ep.spark.query
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.OutputMode

class TopKMeetups extends Query {

  override def topic: String = "topK"

  override def outputMode: OutputMode = OutputMode.Complete()

  override def transform(df: DataFrame): DataFrame = {
    df.filter(col("venue.city").isNotNull)
      .groupBy(col("venue.city"))
      .agg(count("venue.city") alias "count")
      .orderBy(col("count").desc)
      .limit(10)
  };

}
