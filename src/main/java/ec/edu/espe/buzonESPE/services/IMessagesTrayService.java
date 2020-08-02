package ec.edu.espe.buzonESPE.services;

import java.util.Optional;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.MessagesTray;

/**
 * @author Dany_Lasso
 */
public interface IMessagesTrayService {
	
	public Optional<?> registerMessage(String emailUserTransmitter, String emailUserReceiver, MessagesTray messages);
	
	public Optional<?> getByUserTransmitterAndStateMessage(String emailUserTransmitter,  String stateMessage, int page, int size) throws NotFoundException ;
	
	public Optional<?> getByUserReceiverAndStateMessage(String emailUserReceiver,  String stateMessage, int page, int size) throws NotFoundException;
	
	public Optional<?> getConversationBetweenUsers(String emailUserTransmitter, String emailUserReceiver) throws NotFoundException;

	public Optional<?> changeStateMessage(Long idComplaint, String stateMessage)throws NotFoundException;
	
	public Optional<?> getDetail(Long idComplaint)throws NotFoundException;
}
