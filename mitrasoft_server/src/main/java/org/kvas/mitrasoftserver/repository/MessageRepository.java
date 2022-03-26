package org.kvas.mitrasoftserver.repository;

import org.kvas.mitrasoftserver.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
