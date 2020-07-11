package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class FilterGermanMeetups extends Query {

  override def transform(df: DataFrame): DataFrame = df.where(lower(col("venue.country")) === "de")

}
