package cl.nvrrt.cvseguro.entities;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
	
	@Id
	@Indexed(unique = true)
	private String id;
	
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	private LocalDateTime fecha; //deberia ser fecha de nacimiento
	
	private String username;
	private String name;
	private String lastname;
	@Indexed(unique = true) 
	private String email;
	private String password;
	private String direccion;

	@DBRef
	private Set<TipoUser> tipoUser = new HashSet<>();

    public Object orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
	}

}
