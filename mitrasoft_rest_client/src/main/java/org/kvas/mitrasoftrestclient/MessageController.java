package org.kvas.mitrasoftrestclient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", new Message());
        return "index";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute Message message, Model model) {
        model.addAttribute("message", message);
        return "result";
    }
}
