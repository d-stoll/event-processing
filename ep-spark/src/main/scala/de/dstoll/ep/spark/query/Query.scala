package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.OutputMode

trait Query {

  def topic: String

  def outputMode: OutputMode

  def transform(df: DataFrame): DataFrame

}
