package org.kvas.mitrasoftserver.service;

import org.kvas.mitrasoftserver.dto.MessageDto;
import org.kvas.mitrasoftserver.model.Message;

public interface MessageService {
    Message createMessage(MessageDto newMessage);
    Iterable<Message> getAllMesssages();
}
