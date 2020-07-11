plugins {
    java
    scala
    application
}

java.sourceCompatibility = JavaVersion.VERSION_14

dependencies {
    implementation("org.scala-lang:scala-library:2.12.11")
    implementation("org.apache.spark:spark-core_2.12:2.4.5")
    implementation("org.apache.spark:spark-sql_2.12:2.4.5")
    implementation("org.apache.spark:spark-sql-kafka-0-10_2.12:2.4.5")
}

application {
    mainClass.set("de.dstoll.ep.spark.MeetupJob")
}

