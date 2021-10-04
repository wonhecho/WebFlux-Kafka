package net.zerotodev.api.consumer.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("Message")
@Getter @Setter
public class Message implements Serializable{
    private static final long serialVersionUID = 142466781L;
    @Id private String id;
    private String nftid;
    private String name;
    private String message;


    @Builder
    public Message (String name, String message, String nftid){
        this.name = name;
        this.message = message;
        this.nftid = nftid;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id='" + name + '\'' +
                ", book='" + message + '\'' +
                ", content='" + nftid + '\'' +
                '}';
    }
}
