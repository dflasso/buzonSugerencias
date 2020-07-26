package ec.edu.espe.buzonESPE.repositorys;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Suggestion;
import ec.edu.espe.buzonESPE.model.User;

@Repository
public interface SuggestionRepository  extends JpaRepository<Suggestion, Long>{
	
	public Long countByUserAutor(User userAutor); 
	
	public List<Suggestion> findAllByCreationDateBetween(LocalDateTime creationDateStart,LocalDateTime creationDateEnd );
}
