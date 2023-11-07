package carstand.in.zorocars.registration;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;





@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class RegistrationRequest {
	
    private final   String fullName;
    private  final String email;
	private final String address;
    private  final String password;
    private final long moblieNumber;
    
 
}
