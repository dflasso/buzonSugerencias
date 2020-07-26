package ec.edu.espe.buzonESPE.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Modality;

@Repository
public interface ModalityRepository  extends JpaRepository<Modality,Long >{

}
