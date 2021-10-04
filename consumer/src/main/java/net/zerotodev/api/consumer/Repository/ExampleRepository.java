package net.zerotodev.api.consumer.Repository;

import net.zerotodev.api.consumer.domain.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExampleRepository extends ReactiveMongoRepository<Message,String> {
    Flux<Message> findAll();
    @Query("{'nft_id': {$regex: ?0}}")
    List<HashMap<String,Object>> findBynftid(String nftid);
    @Query("{'message' : {name: ?0}}")
    Flux<Message> findBymessageWithnftid(String alias, Pageable page);
}
