package ec.edu.espe.buzonESPE.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Profile;

/**
 * 
 * @author Dany_Lasso
 */
@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long>{

	Optional<Profile>  findByName(String name);
	
	
}
