# MitraSoft Test assignment

This repo contains code for test assignment for MitraSoft company (Junior Java Developer vacancy).

The Structure of repo:
- Server: Spring application with endpoint for REST API and also gRPC service.
- Rest Client: also a small Spring app with Thymeleaf templates for an easy user communicating with server.

## Build
Apps are set up via docker-compose. Also there's a command in Makefile so after cloning this repo you can easily build and run project with:

```bash
make rebuild-docker
```

This command will set docker-compose down at first (if it is already runing), rebuild apps with gradle and run ```docker-compose up --build``` to run project.

## API

### REST
There's only one enpoint (for both GET and POST methods) to create message and get list of ones:
- http://localhost:8080/rest/message

Sending POST method:

```bash
curl -X POST http://localhost:8080/rest/message \
    -H 'Content-Type: application/json' -d '{"message":"hello rest"}'
```

Get all messages:
```bash
curl  http://localhost:8080/rest/message
```

And of course you can send and get messages by client (http://localhost:8081)

### gRPC

At this moment you can send messages by gRPC with [grpcurl tool](https://github.com/fullstorydev/grpcurl).
For MacOs you can get it with Homebrew:
```bash
brew install grpcurl
```

List of grpc methods:

```bash
grpcurl -proto mitrasoft_server/src/proto/message.proto \
    localhost:9090 list org.kvas.mitrasoftserver.grpc.MessageService
```

Create message via grpc:

```bash
grpcurl --plaintext -proto mitrasoft_server/src/proto/message.proto \
    -d '{"message": "grpc test"}' localhost:9090 \
    org.kvas.mitrasoftserver.grpc.MessageService.createMessage
```

Get list of messages:

```bash
grpcurl --plaintext -proto mitrasoft_server/src/proto/message.proto \
    localhost:9090 \
    org.kvas.mitrasoftserver.grpc.MessageService.getMessages
```
