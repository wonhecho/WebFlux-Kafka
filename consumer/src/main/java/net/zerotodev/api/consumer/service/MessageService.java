package net.zerotodev.api.consumer.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {


    Flux<ServerSentEvent<Object>> receive();
}
