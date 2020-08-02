package ec.edu.espe.buzonESPE.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.MessagesTray;
import ec.edu.espe.buzonESPE.model.User;

/**
 * @author Dany_Lasso
 */
@Repository
public interface MessagesTrayRepository extends JpaRepository<MessagesTray, Long> {
	
	Page<MessagesTray> findByUserTransmitterAndStateMessage(User userTransmitter, String stateMessage, Pageable pageable);
	
	Page<MessagesTray> findByUserReceiverAndStateMessage(User userReceiver, String stateMessage, Pageable pageable);
	
	List<MessagesTray> findByUserTransmitterAndUserReceiver(User userTransmitter, User userReceiver);
	
}
