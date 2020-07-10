package de.dstoll.sparkjobs.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class FilterGermanMeetups {

  def filter(df: DataFrame): DataFrame = df.where(lower(col("venue.country")) === "de")

}
