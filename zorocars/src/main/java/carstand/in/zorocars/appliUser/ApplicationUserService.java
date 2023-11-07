package carstand.in.zorocars.appliUser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import carstand.in.zorocars.token.ConfirmationToken;
import carstand.in.zorocars.token.ConfirmationTokenService;


@Service

public class ApplicationUserService implements UserDetailsService {
	
	@Autowired
	private ApplicationUserRepo applicationUserRepo;
	@Autowired
	private  BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


		 return applicationUserRepo.findByEmail(email)
	                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
	}
	 public String signUpUser(ApplicationUser applicationUser) {
	        boolean userExists = applicationUserRepo
	                .findByEmail(applicationUser.getEmail())
	                .isPresent();

	        if (userExists) {
	            // TODO check of attributes are the same and
	            // TODO if email not confirmed send confirmation email.

	            throw new IllegalStateException("email already taken");
	        }

	        String encodedPassword = bCryptPasswordEncoder
	                .encode(applicationUser.getPassword());

	        applicationUser.setPassword(encodedPassword);

	        applicationUserRepo.save(applicationUser);

	        String token = UUID.randomUUID().toString();

	        ConfirmationToken confirmationToken = new ConfirmationToken(
	                token,
	                LocalDateTime.now(),
	                LocalDateTime.now().plusMinutes(15),
	                applicationUser
	        );

	        confirmationTokenService.saveConfirmationToken(
	                confirmationToken);

//	        TODO: SEND EMAIL

	        return token;
	    }

	    public int enableAppliUser(String email) {
	        return applicationUserRepo.enableApplicationUser(email);
	    }
	
	
	
	
	

	   

}
