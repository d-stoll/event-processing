package de.dstoll.dashboard.controller;

import de.dstoll.dashboard.dto.SendEventsForm;
import de.dstoll.dashboard.service.MeetupEventScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final MeetupEventScheduler meetupEventScheduler;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(new SendEventsForm(100, 1000));
        return "index";
    }

    @PostMapping("/")
    public String start(SendEventsForm sendEventsForm) {
        meetupEventScheduler.scheduleMeetupEvents(sendEventsForm.getNumberOfMessages(), sendEventsForm.getInterval());
        return "index";
    }

}
