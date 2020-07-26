package ec.edu.espe.buzonESPE.controllers.v1;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.dto.request.RequestResourcesUser;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.ResourcesAppService;
import io.swagger.annotations.Api;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") //Colocar la IPs de los front en produccion mas seguridad 
@RequestMapping(value = "/v1/resources-user")
@Api(tags =  "Recursos del Usuario")
public class ResourcesAppController {
	
	@Autowired
	private ResourcesAppService resourcesService;
	
	@PostMapping
	public ResponseEntity<?> getResourcesByUser(@Valid @RequestBody RequestResourcesUser requestReference) throws NotFoundException{
		return new ResponseEntity<>(resourcesService.getResourcesByUser(requestReference).get(), HttpStatus.ALREADY_REPORTED );
	}

}
