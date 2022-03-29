package org.kvas.mitrasoftserver.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.kvas.mitrasoftserver.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.Instant;

@GrpcService()
public class MessageServiceGrpcImpl extends MessageServiceGrpc.MessageServiceImplBase {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    public static final Logger logger = LoggerFactory.getLogger(MessageServiceGrpcImpl.class);

    @Override
    public void createMessage(Message.MitrasoftMsgRequest request, StreamObserver<Empty> responseObserver) {

        logger.info("got new message: " + request.getMessage());
        messageRepository.save(new org.kvas.mitrasoftserver.model.Message(request.getMessage()));
        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(Empty request, StreamObserver<Message.MitrasoftMsgResponse> responseObserver) {

        Iterable<org.kvas.mitrasoftserver.model.Message> messages = messageRepository.findAll();
        for (org.kvas.mitrasoftserver.model.Message message : messages) {
            // Convert java.util.Date to protobuf.Timestamp
            Instant instant = message.getCreatedAt().toInstant();
            Timestamp createAtTimestamp = Timestamp.newBuilder()
                    .setSeconds(instant.getEpochSecond())
                    .setNanos(instant.getNano())
                    .build();

            Message.MitrasoftMsgResponse grpcMessage = Message.MitrasoftMsgResponse.newBuilder()
                    .setMessage(message.getMessage())
                    .setId(message.getId())
                    .setCreatedAt(createAtTimestamp)
                    .build();

            responseObserver.onNext(grpcMessage);
        }

        responseObserver.onCompleted();
    }
}
