package ec.edu.espe.buzonESPE.services;

import java.util.Optional;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;


public interface ICarrerService {
	
	public Optional<?> getCarrerByDepartmentAndModality(Long idDepartment, Long idModality) throws NotFoundException;

}
