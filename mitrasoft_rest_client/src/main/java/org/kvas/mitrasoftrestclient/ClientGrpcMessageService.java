package org.kvas.mitrasoftrestclient;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.kvas.mitrasoftserver.grpc.Message;
import org.kvas.mitrasoftserver.grpc.MessageServiceGrpc;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ClientGrpcMessageService {

    @GrpcClient("messageClient")
    private MessageServiceGrpc.MessageServiceBlockingStub serviceStub;

    public List<org.kvas.mitrasoftrestclient.Message> getMessages() {

        Empty request = Empty.getDefaultInstance();
        Iterator<Message.MitrasoftMsgResponse> response = serviceStub.getMessages(request);
        // ArrayList<Message.MitrasoftMsgResponse> messages = Lists.newArrayList(response);

        List<org.kvas.mitrasoftrestclient.Message> messages = new ArrayList<>();
        while (response.hasNext()) {
            Message.MitrasoftMsgResponse grpcMessage = response.next();
            messages.add(convertToMitrasoftMessages(grpcMessage));
        }
        return messages;
    }
    
    private org.kvas.mitrasoftrestclient.Message convertToMitrasoftMessages(Message.MitrasoftMsgResponse grpcMessage) {

        Timestamp createdAtTimestamp = grpcMessage.getCreatedAt();
        Date createdAt = Date.from(Instant.ofEpochSecond(createdAtTimestamp.getSeconds(),
                createdAtTimestamp.getNanos()));

        return new org.kvas.mitrasoftrestclient.Message(
                grpcMessage.getId(), grpcMessage.getMessage(), createdAt
        );
    }
}
