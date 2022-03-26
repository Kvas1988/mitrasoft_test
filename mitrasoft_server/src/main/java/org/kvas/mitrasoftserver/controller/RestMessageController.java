package org.kvas.mitrasoftserver.controller;

import org.kvas.mitrasoftserver.dto.MessageDto;
import org.kvas.mitrasoftserver.model.Message;
import org.kvas.mitrasoftserver.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/message")
public class RestMessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping("")
    public void createMessage(@RequestBody MessageDto newMessage) {
        messageService.createMessage(newMessage);
    }

    @GetMapping("")
    public Iterable<Message> getAllMessages() {
        return messageService.getAllMesssages();
    }
}
