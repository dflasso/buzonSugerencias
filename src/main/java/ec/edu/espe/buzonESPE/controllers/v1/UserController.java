package ec.edu.espe.buzonESPE.controllers.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.dto.request.UserRequestDTO;
import ec.edu.espe.buzonESPE.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") //Colocar la IPs de los front en produccion mas seguridad 
@RequestMapping(value = "/v1/user")
@Api(tags =  "Usuario de la Comunidad ESPE")
public class UserController {
	@Autowired
	private IUserService iUserService;
	
	@GetMapping("/{email}")
	@ApiOperation(value = "Obtener toda la información del usuario")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Reporte del usuario completo"),
			@ApiResponse(code = 404, message = "No existen el usuario")})
	public ResponseEntity<?> getAllInfoUser(@PathVariable(value="email") String email){
		return new ResponseEntity<>(iUserService.getAllInfoUser(email), HttpStatus.ALREADY_REPORTED);
	}
	
	@PostMapping("/save-datail")
	@ApiOperation(value = "Registrar detalles del usuario", notes = "<b>Es obligatorio enviar: (correo, nombre, apellido) estos datos ya lo proporciona Google</b>"
			+ "</br><p>Los otros campos son opcionales <b>para la base de datos</b></p></br>"
			+ "<p>En el formulario poner que campos obligatorios depende del front-end </p>")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Detalles del usuario registrado."),
			@ApiResponse(code = 404, message = "No existen el usuario")})
	public ResponseEntity<?> saveDetailUser(@Valid @RequestBody UserRequestDTO userDTO){
		return new ResponseEntity<>(iUserService.saveDetailUser(userDTO), HttpStatus.ACCEPTED);
	}
}
