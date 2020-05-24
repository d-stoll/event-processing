package de.dstoll.kafkaproducer.service

import de.dstoll.kafkaproducer.properties.EventDataProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.RuntimeException
import java.nio.file.Files
import kotlin.system.exitProcess

@Service
class EventDataReader(
        private val eventDataProperties: EventDataProperties,
        private val applicationContext: ApplicationContext
) {

    private val reader: BufferedReader = BufferedReader(FileReader(eventDataProperties.location))

    fun readData(): String {
        return try {
            val line = reader.readLine()
            if(line != null) {
                line
            } else {
                reader.close()
                exitProcess(SpringApplication.exit(applicationContext))
            }
        } catch (iox: IOException) {
            reader.close()
            throw RuntimeException("Fehler beim Einlesen der Event-Daten!")
        }
    }

}