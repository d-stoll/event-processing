package de.dstoll.ep.dashboard.controller;

import de.dstoll.ep.dashboard.dto.SendEventsForm;
import de.dstoll.ep.dashboard.model.TopicColumn;
import de.dstoll.ep.dashboard.model.TopicTable;
import de.dstoll.ep.dashboard.service.MeetupEventScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @ModelAttribute("tables")
    public List<TopicTable> addTables() {
        TopicTable meetupsTable = new TopicTable("meetups", List.of(
                new TopicColumn("Timestamp", "20"),
                new TopicColumn("Name", "60"),
                new TopicColumn("Country", "10"),
                new TopicColumn("City", "10")
        ));
        TopicTable germanMeetupsTable = new TopicTable("germanMeetups", List.of(
                new TopicColumn("Timestamp", "20"),
                new TopicColumn("Name", "60"),
                new TopicColumn("Country", "10"),
                new TopicColumn("City", "10")
        ));
        TopicTable munichMeetupsTable = new TopicTable("munichMeetups", List.of(
                new TopicColumn("Timestamp", "20"),
                new TopicColumn("Name", "60"),
                new TopicColumn("Country", "10"),
                new TopicColumn("City", "10")
        ));
        TopicTable topKTable = new TopicTable("topK", List.of(
                new TopicColumn("Index", "10"),
                new TopicColumn("Group", "60"),
                new TopicColumn("Count", "30")
        ));
        TopicTable topGroupsTable = new TopicTable("topGroups", List.of(
                new TopicColumn("Index", "10"),
                new TopicColumn("Group", "60"),
                new TopicColumn("Count", "30")
        ));
        return List.of(meetupsTable, germanMeetupsTable, munichMeetupsTable, topKTable, topGroupsTable);
    }

}
