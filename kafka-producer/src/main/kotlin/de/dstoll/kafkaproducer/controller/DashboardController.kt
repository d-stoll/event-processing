package de.dstoll.kafkaproducer.controller

import de.dstoll.kafkaproducer.dto.ProducerConfigForm
import de.dstoll.kafkaproducer.service.MeetupEventScheduler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class DashboardController(private val meetupEventScheduler: MeetupEventScheduler) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute(ProducerConfigForm(100, 1000))
        return "index"
    }

    @PostMapping("/")
    fun start(producerConfigForm: ProducerConfigForm, bindingResult: BindingResult): String {
        meetupEventScheduler.scheduleMeetupEvents(producerConfigForm.numberOfMessages, producerConfigForm.interval)
        return "index"
    }



}