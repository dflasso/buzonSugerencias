package ec.edu.espe.buzonESPE.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.ITopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") //Colocar la IPs de los front en produccion mas seguridad 
@RequestMapping(value = "/v1/topic-suggestion")
@Api(tags =  "Temas de Sugerencia")
public class TopicController {
	
	@Autowired
	private ITopicService itopicService;
	
	@GetMapping
	@ApiOperation(value = "Lista de Temas de Sugerencias")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Lista de Temas de Sugerencias"), 
			@ApiResponse(code = 404, message = "No existen Temas de Sugerencias guardadas")		
	})
	public ResponseEntity<?> getAllTopics() throws NotFoundException{
		return new ResponseEntity<>(itopicService.getAllTopic(),HttpStatus.ALREADY_REPORTED);
	}

}
