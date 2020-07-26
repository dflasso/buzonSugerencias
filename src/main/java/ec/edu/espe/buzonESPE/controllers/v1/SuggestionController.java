package ec.edu.espe.buzonESPE.controllers.v1;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import ec.edu.espe.buzonESPE.dto.request.SuggestionDTO;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.ISuggestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") // Colocar la IPs de los front en produccion mas seguridad
@RequestMapping(value = "/v1/suggestion")
@Api(tags = "Sugerencias")
public class SuggestionController {

	@Autowired
	private ISuggestionService iSuggestionService;

	@GetMapping
	@ApiOperation(value = "Lista las sugerencias existentes")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Sugerencia Reistrada"),
			@ApiResponse(code = 404, message = "No existen Sugerencias registradas")})
	public ResponseEntity<?> getAllSugestions() throws NotFoundException {
		return new ResponseEntity<>(iSuggestionService.getAllSuggestions(), HttpStatus.ALREADY_REPORTED);
	}

	@PostMapping("/get-detail")
	@ApiOperation(value = "Detalles de una sugerencia", notes = "<b>Solo necesita el id de la Sugerencia </b> Ejemplo:</br>"
			+ "{ 'suggestion': { 'idSugestion': 1} }")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Sugerencia Encontrada"),
			@ApiResponse(code = 404, message = "No existen la Sugerencia")})
	public ResponseEntity<?> consultSuggestion(@Valid @RequestBody SuggestionDTO suggestionDTO)
			throws NotFoundException {
		return new ResponseEntity<>(iSuggestionService.consultSuggestion(suggestionDTO), HttpStatus.ALREADY_REPORTED);
	}

	@PostMapping("/save")
	@ApiOperation(value = "Registrar Sugerencia", notes = "<b>Obligatorio</b> <ul><li> emailUserAutor </li> <li> emailUserLike (por lo menos declarar  emailUserLike:''  ) </li><li> suggestion.description </li>"
			+ "<li> suggestion.topic.idTopic </li> </ul>, <b>Nota: </b> El usuario autor o el usuario que da Like ya debe estar registrado.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sugerencia Reistrada"),
			@ApiResponse(code = 409, message = "Descripción, Id del Tema (idTopic) o el Email del autor son nulos, o ocurrio un error en el proceso de registro")})
	public ResponseEntity<?> saveSuggestion(@Valid @RequestBody SuggestionDTO suggestionDTO) {
		return new ResponseEntity<>(iSuggestionService.saveSuggestion(suggestionDTO), HttpStatus.CREATED);
	}

	@PutMapping("/put-like")
	@ApiOperation(value = "Poner un Like a una sugerencia")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Sugerencia Encontrada"),
			@ApiResponse(code = 404, message = "No existe la sugerencia"),
			@ApiResponse(code = 409, message = "Problemas con el usuario")})
	public ResponseEntity<?> setLikeSuggestion(@Valid @RequestBody SuggestionDTO suggestionDTO)
			throws NotFoundException {
		return new ResponseEntity<>(iSuggestionService.likeSuggestion(suggestionDTO), HttpStatus.ACCEPTED);
	}

	@GetMapping("/get-report/{startDate}/{endingDate}")
	@ApiOperation(value = "Poner un Like a una sugerencia", notes = "<b>Sugerencia:</b> <ul><li><b>Fecha Inicio:</b> 2020-07-22 00:00:00 (El usuario ingresa la fecha y se adjunta las cero horas)</li>"
			+ "<li><b>Fecha de Fin:</b>2020-07-25 23:59:59 (El usuario ingresa la fecha y se adjunta las última hora del día) </li></ul>")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Sugerencia Encontrada"),
			@ApiResponse(code = 404, message = "No existen sugerencias en el rango de fecha")})
	public ResponseEntity<?> reportSuggestions(
	@PathVariable(value="startDate") @DateTimeFormat(pattern="yyyy-MM-dd kk:mm:ss") LocalDateTime startDate,
	@PathVariable(value = "endingDate") @DateTimeFormat(pattern="yyyy-MM-dd kk:mm:ss") LocalDateTime endingDate) throws NotFoundException {
		return new ResponseEntity<>(iSuggestionService.reportSuggestions(startDate, endingDate),
				HttpStatus.ALREADY_REPORTED);
	}
}
