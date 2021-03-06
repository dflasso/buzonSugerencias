package ec.edu.espe.buzonESPE.services;

import java.time.LocalDate;
import java.util.Optional;

import ec.edu.espe.buzonESPE.dto.request.SuggestionDTO;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;

/**
 * Gestiona los servicios de sugerencias
 * @author Dany Lasso
 */
public interface ISuggestionService {
	
	public Optional<?> saveSuggestion(SuggestionDTO suggestionDTO);
	
	public Optional<?> likeSuggestion(SuggestionDTO suggestionDTO) throws NotFoundException;
	
	public Optional<?> consultSuggestion(SuggestionDTO suggestionDTO) throws NotFoundException;
	
	public Optional<?> reportSuggestions(LocalDate startDate,LocalDate endingDate )throws NotFoundException;
	
	public Optional<?> getAllSuggestions() throws NotFoundException;

}
