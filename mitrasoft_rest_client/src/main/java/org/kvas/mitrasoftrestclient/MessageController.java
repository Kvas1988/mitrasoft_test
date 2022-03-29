package org.kvas.mitrasoftrestclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Controller
public class MessageController {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Value("${SERVER_URL:http://localhost:8080/rest/message}")
    private String SERVER_URL;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", new MessageDto());
        return "index";
    }

    @PostMapping("/send")
    public String sendMessage(@ModelAttribute MessageDto message,
                              Model model) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        logger.info("sending post request for url: " + SERVER_URL);
        HttpEntity<MessageDto> messageEntity = new HttpEntity<>(message, headers);
        restTemplate.postForObject(SERVER_URL, messageEntity, MessageDto.class);

        model.addAttribute("message", message);
        return "result";
    }

    @GetMapping("/messages")
    @ResponseBody
    public Message[] getMessages() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message[]> response = restTemplate.getForEntity(SERVER_URL, Message[].class);
        return response.getBody();
    }
}
