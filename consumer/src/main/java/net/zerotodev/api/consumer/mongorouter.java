//package net.zerotodev.api.consumer;
//
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import net.zerotodev.api.consumer.Repository.ExampleRepository;
//import net.zerotodev.api.consumer.domain.Message;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RequiredArgsConstructor
//@Configuration
//public class mongorouter {
//
//    private final ExampleRepository db;
//
//    @Bean
//    public RouterFunction<ServerResponse> findAll(){
//        final RequestPredicate predicate = RequestPredicates
//                .GET("/find").
//                and(RequestPredicates.accept(MediaType.TEXT_PLAIN));
//        RouterFunction<ServerResponse> response = RouterFunctions.route(predicate, request->{
//            Flux<Message> mapper = db.findAll();
//            db.findnameBymessage("happy-john").collectList().subscribe(System.out::println);
//            Pageable page = PageRequest.of(0,5);
//            db.findBymessageWithnftid("john", page).collectList().subscribe(System.out::println);
//            Message john = new Message("john","happy-john","john");
//            db.insert(john).subscribe(System.out::println);
//            Message tom = new Message("tom","angry-tom","tom");
//            db.insert(tom).subscribe(System.out::println);
//            Mono<ServerResponse> res = ServerResponse.ok()
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(BodyInserters.fromProducer(mapper, Message.class));
//            return res;
//        });
//        return response;
//    }
//}
//
