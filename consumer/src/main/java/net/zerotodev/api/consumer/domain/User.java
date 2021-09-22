package net.zerotodev.api.consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
public class User {
    private String id;
    private String name;
    private String email;

    public User(){}

    @Override
    public String toString(){
        return String.format("%s,%s,%s\n", id, name, email);
    }

}
