package net.zerotodev.api.consumer.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.zerotodev.api.consumer.domain.Message;
import net.zerotodev.api.consumer.service.MessageService;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public Flux<ServerSentEvent<Object>> consumeMessage() {
        return messageService.receive();
    }

    @GetMapping("/{nftid}")
    public Map test (@PathVariable String nftid)
    {
        return messageService.gethighcost(nftid);
    }
}
