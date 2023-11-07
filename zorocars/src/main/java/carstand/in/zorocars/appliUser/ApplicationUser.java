package carstand.in.zorocars.appliUser;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Setter
@Getter
@Table(name = "registration")
public class ApplicationUser  implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@Column(name="full_name")
	private String fullName;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="mobile_number")
	private long mobileNumber;
	@Column(name = "user_type", length = 255)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@Column(name="enabled")
	private Boolean enabled= false;
	@Column(name="locked")
	private Boolean locked= false;
	@CreationTimestamp
	@Column(name = "created_at",updatable = false)
	private Date regdata;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date newUpdates;
	
	

	

	public ApplicationUser(String fullName, String address, String email, String password, long mobileNumber,
						   UserType userType) {
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.userType = userType;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {	
		SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userType.name());
        return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {	
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	@Override
	public boolean isCredentialsNonExpired() {	
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String encodedPassword) {	
	this.password= encodedPassword;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	



	
	
	
	
	
	

	
	
	
	
	

}
