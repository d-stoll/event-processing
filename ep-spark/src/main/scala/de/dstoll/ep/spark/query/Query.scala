package de.dstoll.ep.spark.query

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.streaming.{OutputMode, Trigger}

trait Query {

  def topic: String

  def outputMode: OutputMode

  def trigger: Trigger

  def transform(df: DataFrame): DataFrame

}
