package cl.nvrrt.cvseguro.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "products")
@Getter
@Setter
@ToString
public class Product {
    
    @Id
    private String id;

    private String nameProdcut;

    @JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
    private LocalDateTime fechaPublicacion;
    private Integer price;
    private String description;

}
