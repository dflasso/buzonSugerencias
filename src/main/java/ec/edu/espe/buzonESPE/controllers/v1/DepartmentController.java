package ec.edu.espe.buzonESPE.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.buzonESPE.services.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

/**
 * @author Dany_Lasso
 */
@RestController
@CrossOrigin(origins = "*") //Colocar la IPs de los front en produccion mas seguridad 
@RequestMapping(value = "/v1/department")
@Api(tags =  "Departamento de la UFA - ESPE")
public class DepartmentController {

	@Autowired
	private IDepartmentService iDepartmentService;
	
	@GetMapping("/all")
	@ApiOperation(value = "Obtener todos los departamentos de la UFA - ESPE")
	@ApiResponses(value = { @ApiResponse(code = 208, message = "Reporte de los departamentos completo"),
			@ApiResponse(code = 404, message = "No existen departamentos.")})
	public ResponseEntity<?> getAllDepartments() throws NotFoundException{
		return new ResponseEntity<>(iDepartmentService.getAllDepartments(), HttpStatus.ALREADY_REPORTED);
	}
}
