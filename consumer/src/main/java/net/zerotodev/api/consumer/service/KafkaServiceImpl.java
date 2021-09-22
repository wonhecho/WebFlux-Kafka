package net.zerotodev.api.consumer.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final ReceiverOptions<String, Object> receiverOptions;
    private final Sinks.Many<Object> sinksMany;

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
            Object receivedData = r.value();
            if (receivedData != null) {
                sinksMany.emitNext(r.value(), Sinks.EmitFailureHandler.FAIL_FAST);   // data를 consuming할때마다 sink로 전송
            }
            r.receiverOffset().acknowledge();
        };
    }
}
