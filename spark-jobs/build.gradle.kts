plugins {
    scala
}

dependencies {
    compileOnly("org.apache.spark:spark-core_2.12:2.4.5")
    compileOnly("org.apache.spark:spark-sql_2.12:2.4.5")
    implementation("org.apache.spark:spark-sql-kafka-0-10_2.12:2.4.5")
}

