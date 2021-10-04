package net.zerotodev.api.consumer.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import net.zerotodev.api.consumer.Repository.ExampleRepository;
import net.zerotodev.api.consumer.domain.Message;
import org.apache.commons.logging.Log;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final ReceiverOptions<String, Object> receiverOptions;
    private final Sinks.Many<Object> sinksMany;
    private final ExampleRepository exampleRepository;

    private Disposable disposable;

    @PostConstruct
    public void init() {    // Consumer를 열어놓음
        disposable = KafkaReceiver.create(receiverOptions).receive()
                .doOnNext(processReceivedData())
                .doOnError(e -> {
                    System.out.println("Kafka read error");
                    init();     // 에러 발생 시, consumer가 종료되고 재시작할 방법이 없기 때문에 error시 재시작
                })
                .subscribe();
    }

    private Consumer<ReceiverRecord<String, Object>> processReceivedData() {
        return r -> {
            System.out.println("Kafka Consuming");
            System.out.println(r.value());
            Object receivedData = r.value();
            String deta = receivedData.toString();
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(deta);
                Message msg = Message.builder().name(obj.get("name").toString()).message(obj.get("message").toString())
                                .nftid(obj.get("nftid").toString()).build();
                exampleRepository.save(msg).subscribe();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (receivedData != null) {
                sinksMany.emitNext(r.value(), Sinks.EmitFailureHandler.FAIL_FAST);   // data를 consuming할때마다 sink로 전송
            }
            r.receiverOffset().acknowledge();
        };
    }
}
