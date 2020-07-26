package ec.edu.espe.buzonESPE.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.User;

/**
 * @author Dany_Lasso
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
		
	List<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
