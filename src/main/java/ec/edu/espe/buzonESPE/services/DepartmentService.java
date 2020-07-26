package ec.edu.espe.buzonESPE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.repositorys.DepartmentRepository;
import javassist.NotFoundException;

/**
 * Implementa la gestion de departamentos de UFA - ESPE
 * @version 1.0.0
 * @author Dany_Lasso
 */
@Service
@Primary
public class DepartmentService implements IDepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Optional<?> getAllDepartments() throws NotFoundException {
		List<Department> departments = departmentRepository.findAll();
		if(departments.isEmpty()) {
			throw new NotFoundException("No existen departamentos registrados");
		}
		return Optional.of(departments);
	}

}
