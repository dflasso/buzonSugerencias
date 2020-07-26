package ec.edu.espe.buzonESPE.controllers.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Complaint;
import ec.edu.espe.buzonESPE.services.IComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") // Colocar la IPs de los front en produccion mas seguridad
@RequestMapping(value = "/v1/complaint")
@Api(tags = "Denuncias")
public class ComplaintController {
	@Autowired
	private IComplaintService complaintService;
	
	@PostMapping("/save/{emailUserInformer}")
	@ApiOperation(value = "Registrar denuncia", notes = "<b>Todos los campos son obligatorios, a excepción de:</b>"
			+ "<ul><li>sendDateComplaint (Se crea internamente)</li>"
			+ "<li>idComplaint (Se crea internamente)</li>"
			+ "<li>stateComplaint (por defecto se coloca  'No procesada')</li></ul>")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Denuncia Registrada"),
			@ApiResponse(code = 404, message = "No existe el usuario"),
			@ApiResponse(code = 409, message = "Problemas al registrar la denuncia")})
	public ResponseEntity<?> saveComplaint(@Valid @RequestBody Complaint complaint, 
			@PathVariable(value="emailUserInformer") String emailUserInformer){
		return new ResponseEntity<>(complaintService.saveComplaint(complaint, emailUserInformer),HttpStatus.CREATED);
	}
	
	@PatchMapping("/change-state/{idComplaint}/{stateComplaint}")
	@ApiOperation(value = "Cambiar de Estado Denuncias, posibles estados (No Procesada - En Proceso - Archivada)")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "La Denuncia cambio de estado con exito"),
			@ApiResponse(code = 404, message = "No existe la Denuncia"),
			@ApiResponse(code = 409, message = "Problemas al registrar los cambios en la denuncia")})
	public ResponseEntity<?> changeStateComplaint(@PathVariable(value="idComplaint") Long idComplaint,@PathVariable(value="stateComplaint")  String stateComplaint) throws NotFoundException {
		return new ResponseEntity<>(complaintService.changeStateComplaint(idComplaint, stateComplaint), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/find/{emailUserInformer}/{stateComplaint}")
	@ApiOperation(value = "Obtiene listado de Denuncia según un usuario y un usuario")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Reporte de las denuncias procesado exitosamente"),
			@ApiResponse(code = 404, message = "No existen Denuncias"),
			@ApiResponse(code = 409, message = "Problemas al obtener las denuncias")})
	public ResponseEntity<?> getComplaintsByUserInformerAndState(@PathVariable(value="emailUserInformer")  String emailUserInformer,  @PathVariable(value="stateComplaint")  String stateComplaint) throws NotFoundException{
		return new ResponseEntity<>(complaintService.getComplaintsByUserInformerAndState(emailUserInformer, stateComplaint), HttpStatus.ALREADY_REPORTED);
	}
	
	@GetMapping("/get-detail/{idComplaint}")
	@ApiOperation(value = "Obtiene listado de Denuncia según un usuario y un usuario")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Reporte de las denuncias procesado exitosamente"),
			@ApiResponse(code = 404, message = "No existen Denuncias"),
			@ApiResponse(code = 409, message = "Problemas al obtener las denuncias")})
	public ResponseEntity<?> getDetailComplaint(@PathVariable(value="idComplaint") Long idComplaint) throws NotFoundException {
		return new ResponseEntity<>(complaintService.getDetailComplaint(idComplaint).get(), HttpStatus.ALREADY_REPORTED);
	}

}
