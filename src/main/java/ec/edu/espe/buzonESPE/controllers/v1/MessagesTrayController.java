package ec.edu.espe.buzonESPE.controllers.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.MessagesTray;
import ec.edu.espe.buzonESPE.services.IMessagesTrayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") // Colocar la IPs de los front en produccion mas seguridad
@RequestMapping(value = "/v1/messages-tray")
@Api(tags = "Bandeja de Mensajes")
public class MessagesTrayController {

	@Autowired
	private IMessagesTrayService iMessagesTrayService;

	@PostMapping("/{emailUserTransmitter}/{emailUserReceiver}/save")
	@ApiOperation(value = "Registrar mensaje", notes = "<b>Obligatorios:</b>"
			+ "<ul><li>PathVariable -> emailUserTransmitter </li>" + "<li>PathVariable -> emailUserReceiver</li>"
			+ "<li>RequestBody -> messages.message</li></ul>")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Mensaje Registrado"),
			@ApiResponse(code = 409, message = "Problemas al registrar el mensaje") })
	public ResponseEntity<?> registerMessage(@PathVariable(value = "emailUserTransmitter") String emailUserTransmitter,
			@PathVariable(value = "emailUserReceiver") String emailUserReceiver,
			@Valid @RequestBody MessagesTray messages) {
		return new ResponseEntity<>(
				iMessagesTrayService.registerMessage(emailUserTransmitter, emailUserReceiver, messages).get(),
				HttpStatus.ALREADY_REPORTED);
	}

	@GetMapping("/{emailUserTransmitter}/{stateMessage}/{page}/{size}/get-by-user-transmitter")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Lista de mensajes"),
			@ApiResponse(code = 409, message = "Problemas al registrar el mensaje") })
	@ApiOperation(value = "Listar mensajes por usuario emisor y estado", notes = "<b>Todas las variables del path son obligatorias</b>")
	public ResponseEntity<?> getByUserTransmitterAndStateMessage(
			@PathVariable(value = "emailUserTransmitter") String emailUserTransmitter,
			@PathVariable(value = "stateMessage") String stateMessage, @PathVariable(value = "page") int page,
			@PathVariable(value = "size") int size) throws NotFoundException {
		return new ResponseEntity<>(iMessagesTrayService
				.getByUserTransmitterAndStateMessage(emailUserTransmitter, stateMessage, page, size).get(),
				HttpStatus.ALREADY_REPORTED);
	}

	@GetMapping("/{emailUserReceiver}/{stateMessage}/{page}/{size}/get-by-user-receiver")
	@ApiOperation(value = "Listar mensajes por usuario emisor y estadoe", notes = "<b>Todas las variables del path son obligatorias</b>")
	public ResponseEntity<?> getByUserReceiverAndStateMessage(
			@PathVariable(value = "emailUserReceiver") String emailUserReceiver,
			@PathVariable(value = "stateMessage") String stateMessage, @PathVariable(value = "page") int page,
			@PathVariable(value = "size") int size) throws NotFoundException {
		return new ResponseEntity<>(iMessagesTrayService
				.getByUserReceiverAndStateMessage(emailUserReceiver, stateMessage, page, size).get(),
				HttpStatus.ALREADY_REPORTED);
	}

	@GetMapping("/{emailUserTransmitter}/{emailUserReceiver}/get-conversation-between-users")
	@ApiOperation(value = "Listar conversación entre emisor y receptor", notes = "<b>Todas las variables del path son obligatorias</b>")
	public ResponseEntity<?> getConversationBetweenUsers(
			@PathVariable(value = "emailUserTransmitter") String emailUserTransmitter,
			@PathVariable(value = "emailUserReceiver") String emailUserReceiver) throws NotFoundException {
		return new ResponseEntity<>(
				iMessagesTrayService.getConversationBetweenUsers(emailUserTransmitter, emailUserReceiver).get(),
				HttpStatus.ALREADY_REPORTED);
	}
	
	@PutMapping("/{idMessagesTray}/{stateMessage}/change-state")
	@ApiOperation(value = "Cambiar de estado un mensaje")
	public ResponseEntity<?> changeStateMessage(@PathVariable(value = "idMessagesTray") Long idMessagesTray,
			@PathVariable(value = "stateMessage") String stateMessage) throws NotFoundException {
		return new ResponseEntity<>(iMessagesTrayService.changeStateMessage(idMessagesTray, stateMessage), HttpStatus.ALREADY_REPORTED);
	}
	
	@GetMapping("/{idMessagesTray}/get-detail")
	@ApiOperation(value = "Obtener detalles de un mensaje")
	public ResponseEntity<?> getDetail(@PathVariable(value = "idMessagesTray") Long idMessagesTray) throws NotFoundException {
		return new ResponseEntity<>(iMessagesTrayService.getDetail(idMessagesTray) , HttpStatus.ALREADY_REPORTED);	
	}

}
