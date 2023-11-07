package carstand.in.zorocars.appliUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser, Long>{
	
	 Optional<ApplicationUser> findByEmail(String email);
	 

	    @Transactional
	    @Modifying
	    @Query("UPDATE ApplicationUser a " +
	            "SET a.enabled = TRUE WHERE a.email = ?1")
	    int enableApplicationUser(String email);


		

	

}
