package com.kitano.kafka.ui;

import com.kitano.kafka.dto.Person;
import com.kitano.kafka.sender.ISender;
import com.kitano.kafka.service.KafkaListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KafkaUIController {

    @Autowired
    private ISender sender;

    @Autowired
    private KafkaListenerService kafkaListenerService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("messages", kafkaListenerService.getReceivedMessages());
        return "index";
    }

    @PostMapping("/sendCustomPerson")
    public String sendPerson(@RequestBody Person person) {
        Person p = new Person(person.getFirstName(), person.getLastName(), person.getAge());
        sender.send("Tuto1", p);
        return "redirect:/";
    }
}
