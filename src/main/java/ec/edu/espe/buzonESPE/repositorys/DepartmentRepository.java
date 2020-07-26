package ec.edu.espe.buzonESPE.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Department;

/**
 * Gestiona los departamentos de la universidad
 * @author Dany_Lasso
 */
@Repository
public interface DepartmentRepository  extends JpaRepository<Department, Long>{

}
