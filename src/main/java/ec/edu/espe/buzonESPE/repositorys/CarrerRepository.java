package ec.edu.espe.buzonESPE.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.model.Modality;

@Repository
public interface CarrerRepository  extends JpaRepository<Carrer, Long>{

	List<Carrer> findByDepartmentAndModality(Department department, Modality modality);
	
}
