package cl.nvrrt.cvseguro.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "tipoUser")
@Getter
@Setter
@ToString
public class TipoUser {

    @Id
    private String id;

    private String name;
    
}
