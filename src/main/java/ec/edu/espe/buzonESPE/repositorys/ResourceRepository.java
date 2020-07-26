package ec.edu.espe.buzonESPE.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Profile;
import ec.edu.espe.buzonESPE.model.Resource;

/**
 * @author dany_lasso
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>{
	
	List<Resource> findByProfile(Profile profile);
	
}
