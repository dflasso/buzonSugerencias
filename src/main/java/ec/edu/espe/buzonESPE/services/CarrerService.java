package ec.edu.espe.buzonESPE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.model.Modality;
import ec.edu.espe.buzonESPE.repositorys.CarrerRepository;

/**
 * Implementa la gestion de Carreras de la UFA - ESPE
 * @author Dany_Lasso
 */
@Service
@Primary
public class CarrerService implements ICarrerService {
	
	@Autowired
	private CarrerRepository carrerRepository;

	@Override
	public Optional<?> getCarrerByDepartmentAndModality(Long idDepartment, Long idModality) throws NotFoundException {
		Modality modality = new Modality();
		modality.setIdModality(idModality);
		Department department = new Department();
		department.setIdDepartment(idDepartment);
		
		List<Carrer> carrers = carrerRepository.findByDepartmentAndModality(department,modality);
		if(carrers.isEmpty()) {
			throw new NotFoundException(Carrer.class, "idDepartment", idDepartment.toString(), 
					"idModality", idModality.toString());
		}
		
		return Optional.of(carrers);
	}

}
