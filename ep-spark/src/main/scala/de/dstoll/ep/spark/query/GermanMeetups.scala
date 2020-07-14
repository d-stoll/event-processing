package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class GermanMeetups extends Query {

  override def topic: String = "germanMeetups"

  override def transform(df: DataFrame): DataFrame = df.where(lower(col("venue.country")) === "de")

}
