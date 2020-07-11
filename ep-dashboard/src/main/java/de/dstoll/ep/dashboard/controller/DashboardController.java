package de.dstoll.ep.dashboard.controller;

import de.dstoll.ep.dashboard.dto.SendEventsForm;
import de.dstoll.ep.dashboard.service.MeetupEventScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
