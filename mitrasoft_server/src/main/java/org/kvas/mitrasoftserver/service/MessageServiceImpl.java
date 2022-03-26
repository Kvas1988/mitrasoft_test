package org.kvas.mitrasoftserver.service;

import org.kvas.mitrasoftserver.dto.MessageDto;
import org.kvas.mitrasoftserver.model.Message;
import org.kvas.mitrasoftserver.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(MessageDto newMessage) {
        Message message = new Message(newMessage.getMessage());
        return messageRepository.save(message);
    }

    @Override
    public Iterable<Message> getAllMesssages() {
        return messageRepository.findAll();
    }
}
