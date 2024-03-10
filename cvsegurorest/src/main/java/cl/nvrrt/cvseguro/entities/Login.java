package cl.nvrrt.cvseguro.entities;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection =  "login")
@Getter
@Setter
@ToString
public class Login {


    private String email;
    private String password;    

    @JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	private LocalDateTime fecha; 

    
}
