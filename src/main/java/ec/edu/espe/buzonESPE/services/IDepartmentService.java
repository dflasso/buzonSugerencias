package ec.edu.espe.buzonESPE.services;

import java.util.Optional;

import javassist.NotFoundException;

/**
 * @author Dany_Lasso
 */
public interface IDepartmentService {

	public Optional<?> getAllDepartments() throws NotFoundException ;
}
