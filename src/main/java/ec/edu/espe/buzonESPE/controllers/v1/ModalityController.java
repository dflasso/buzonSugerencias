package ec.edu.espe.buzonESPE.controllers.v1;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.IModality;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") //Colocar la IPs de los front en produccion mas seguridad 
@RequestMapping(value = "/v1/modality-carrer")
@Api(tags =  "Modalidad Carreras")
public class ModalityController {
	
	@Autowired
	private IModality iModality;
	
	@GetMapping
	@ApiOperation(value = "Listar las modalidades de la carrera")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Listado de modalidades de estudio"), 
			@ApiResponse(code = 404, message = "No existen modalidades guardadas")		
	})
	public ResponseEntity<?> getAllModality() throws NotFoundException{
		return new ResponseEntity<>(iModality.getAllModality(), HttpStatus.ALREADY_REPORTED);
	}

}
