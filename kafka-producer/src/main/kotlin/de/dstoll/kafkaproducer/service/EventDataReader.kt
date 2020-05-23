package de.dstoll.kafkaproducer.service

import de.dstoll.kafkaproducer.properties.EventDataProperties
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.lang.RuntimeException
import java.nio.file.Files

@Service
class EventDataReader(private val eventDataProperties: EventDataProperties) {

    private var reader: BufferedReader = BufferedReader(FileReader(eventDataProperties.location))

    fun readData(): String {
        return try {
            val line = reader.readLine()
            if(line != null) {
                line
            } else {
                reader.close()
                reader = BufferedReader(FileReader(eventDataProperties.location))
                readData()
            }
        } catch (iox: IOException) {
            throw RuntimeException("Fehler beim Einlesen der Event-Daten!")
        }
    }

}