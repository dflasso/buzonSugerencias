package ec.edu.espe.buzonESPE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Modality;
import ec.edu.espe.buzonESPE.repositorys.ModalityRepository;

@Service
@Primary
public class ModalityService implements IModality{
	
	@Autowired
	private ModalityRepository modalityRepository;
	

	@Override
	public List<Modality> getAllModality() throws NotFoundException {
		List<Modality> modalitys = modalityRepository.findAll();
		if(modalitys.isEmpty()) {
			throw new NotFoundException(Modality.class, "Modality", "there isn't register");
		}
		return modalitys;
	}

}
