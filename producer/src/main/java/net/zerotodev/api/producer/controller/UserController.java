package net.zerotodev.api.producer.controller;

import lombok.RequiredArgsConstructor;

import net.zerotodev.api.producer.domain.Message;
import net.zerotodev.api.producer.service.MessageService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final MessageService messageService;

    @PostMapping
    public Mono<String> produceMessage(@RequestBody Mono<Message> message) {
        return message
                .flatMap(msg -> messageService.send(msg.getName(), msg));
    }


}
