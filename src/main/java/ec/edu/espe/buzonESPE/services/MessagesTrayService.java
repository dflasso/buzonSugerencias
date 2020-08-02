package ec.edu.espe.buzonESPE.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.MessagesTray;
import ec.edu.espe.buzonESPE.model.User;
import ec.edu.espe.buzonESPE.repositorys.MessagesTrayRepository;

/**
 * Implementa la gestion de la bandeja de mensajes
 * 
 * @version 1.0.0
 * @author Dany Lasso
 */
@Service
@Primary
public class MessagesTrayService implements IMessagesTrayService {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private MessagesTrayRepository messagesTrayRepository;

	@Override
	public Optional<?> registerMessage(String emailUserTransmitter, String emailUserReceiver, MessagesTray messages) {
		User userTransmitter = iUserService.searchByEmail(emailUserTransmitter).get();
		User userReceiver = iUserService.searchByEmail(emailUserReceiver).get();
		messages.setSendMessage(LocalDateTime.now());
		messages.setStateMessage("No Leido");
		messages.setUserNameTransmitter(userTransmitter.getName() + " " +userTransmitter.getLastname());
		messages.setUserNameReceiver(userReceiver.getName() + " " + userReceiver.getLastname());
		messages.setUserTransmitter(userTransmitter);
		messages.setUserReceiver(userReceiver);

		try {
			messagesTrayRepository.save(messages);
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		return Optional.of("Mensaje Guardado. " + messages.getMessage());
	}

	@Override
	public Optional<?> getByUserTransmitterAndStateMessage(String emailUserTransmitter, String stateMessage, int page,
			int size) throws NotFoundException {
		Pageable pageable = PageRequest.of(page, size, Sort.by("sendMessage").descending());
		User userTransmitter = iUserService.searchByEmail(emailUserTransmitter).get();
		Page<MessagesTray> messages = messagesTrayRepository.findByUserTransmitterAndStateMessage(userTransmitter,
				stateMessage, pageable);
		if (messages.isEmpty()) {
			throw new NotFoundException(MessagesTray.class, "emailUserTransmitter", emailUserTransmitter,
					"stateMessage", stateMessage);
		}
		return Optional.of(messages);
	}

	@Override
	public Optional<?> getByUserReceiverAndStateMessage(String emailUserReceiver, String stateMessage, int page,
			int size) throws NotFoundException {
		Pageable pageable = PageRequest.of(page, size, Sort.by("sendMessage").descending());
		User userReceiver = iUserService.searchByEmail(emailUserReceiver).get();
		Page<MessagesTray> messages = messagesTrayRepository.findByUserReceiverAndStateMessage(userReceiver,
				stateMessage, pageable);
		if (messages.isEmpty()) {
			throw new NotFoundException(MessagesTray.class, "emailUserReceiver", emailUserReceiver, "stateMessage",
					stateMessage);
		}
		return Optional.of(messages);
	}

	@Override
	public Optional<?> getConversationBetweenUsers(String emailUserTransmitter, String emailUserReceiver)
			throws NotFoundException {
		User userTransmitter = iUserService.searchByEmail(emailUserTransmitter).get();
		User userReceiver = iUserService.searchByEmail(emailUserReceiver).get();
		List<MessagesTray> messages = messagesTrayRepository.findByUserTransmitterAndUserReceiver(userTransmitter,
				userReceiver);
		if (messages.isEmpty()) {
			throw new NotFoundException(MessagesTray.class, "emailUserReceiver", emailUserReceiver, "emailUserReceiver",
					emailUserReceiver);
		}
		return Optional.of(messages);
	}

	@Override
	public Optional<?> changeStateMessage(Long idMessagesTray, String stateMessage) throws NotFoundException {
		Optional<MessagesTray> messagesTrayOptional= messagesTrayRepository.findById(idMessagesTray);
		if (!messagesTrayOptional.isPresent()) {
			throw new NotFoundException(MessagesTray.class, "idMessagesTray", idMessagesTray);
		}
		try {
			messagesTrayOptional.get().setStateMessage(stateMessage);
			messagesTrayRepository.save(messagesTrayOptional.get());
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		
		return Optional.of("El mensaje cambio de estado");
	}

	@Override
	public Optional<?> getDetail(Long idMessagesTray) throws NotFoundException {
		Optional<MessagesTray> message = messagesTrayRepository.findById(idMessagesTray);
		if(!message.isPresent()) {
			throw new NotFoundException(MessagesTray.class, "idMessagesTray", idMessagesTray);
		}
		
		Map<String, Object> detailMessage = new HashMap<String, Object>();
		detailMessage.put("message", message.get());
		detailMessage.put("userTransmitter", message.get().getUserTransmitter());
		detailMessage.put("userReceiver", message.get().getUserReceiver());
		return Optional.of(detailMessage);
	}
	
	

}
