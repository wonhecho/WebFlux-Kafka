package net.zerotodev.api.producer.service;

import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<String> send(String key, Object value);
}
