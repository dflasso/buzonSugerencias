package ec.edu.espe.buzonESPE.repositorys;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Complaint;
import ec.edu.espe.buzonESPE.model.User;

/**
 * @author Dany_Lasso
 */
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

	List<Complaint> findByUserInformerAndStateComplaint(User userInformer, String stateComplaint);
	
	
	List<Complaint> findByStateComplaintAndSendDateComplaintBetween(String stateComplaint, LocalDateTime sendDateComplaintStart, LocalDateTime sendDateComplaintEnd);
	
}
