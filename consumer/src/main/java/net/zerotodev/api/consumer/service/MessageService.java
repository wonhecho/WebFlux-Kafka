package net.zerotodev.api.consumer.service;

import net.zerotodev.api.consumer.domain.Message;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface MessageService {


    Flux<ServerSentEvent<Object>> receive();
    Map gethighcost(String nftid);
}
