package ec.edu.espe.buzonESPE.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.ICarrerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") // Colocar la IPs de los front en produccion mas seguridad
@RequestMapping(value = "/v1/carrer")
@Api(tags = "Carreras de la UFA - ESPE")
public class CarrerController {

	@Autowired
	private ICarrerService iCarrerService;

	@GetMapping("/get-by/{idDepartment}/{idModality}")
	@ApiOperation(value = "Lista las carreras por departamento y modalidad")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Carreras Encontradas"),
			@ApiResponse(code = 404, message = "No existen carreras")})
	public ResponseEntity<?> getCarrerByDepartmentAndModality(@PathVariable(value = "idDepartment") Long idDepartment,
			@PathVariable(value = "idModality") Long idModality) throws NotFoundException {
		return new ResponseEntity<>(iCarrerService.getCarrerByDepartmentAndModality(idDepartment, idModality),
				HttpStatus.ALREADY_REPORTED);
	}
}
