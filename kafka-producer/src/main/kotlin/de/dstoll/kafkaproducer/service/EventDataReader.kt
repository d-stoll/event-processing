package de.dstoll.kafkaproducer.service

import de.dstoll.kafkaproducer.configuration.properties.EventDataProperties
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.RuntimeException
import java.util.zip.ZipFile
import kotlin.system.exitProcess

@Service
class EventDataReader(
        private val eventDataProperties: EventDataProperties,
        private val applicationContext: ApplicationContext
) {

    private val reader: BufferedReader = ResourceUtils.getFile(eventDataProperties.location).bufferedReader()

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