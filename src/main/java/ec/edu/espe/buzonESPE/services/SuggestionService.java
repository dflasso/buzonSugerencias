package ec.edu.espe.buzonESPE.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.dto.request.SuggestionDTO;
import ec.edu.espe.buzonESPE.dto.response.AllInfoSugestion;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Suggestion;
import ec.edu.espe.buzonESPE.model.User;
import ec.edu.espe.buzonESPE.repositorys.SuggestionRepository;
import ec.edu.espe.buzonESPE.repositorys.TopicRepository;

@Service
@Primary
public class SuggestionService implements ISuggestionService{
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private SuggestionRepository suggestionRepository;
	
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Optional<?> saveSuggestion(SuggestionDTO suggestionDTO) {
		if(suggestionDTO.getSuggestion().getDescription().isEmpty()) {
			throw new RuntimeException("Debe enviar la descripción de la Sugerencia.");
		}
		
		if(suggestionDTO.getEmailUserAutor().isEmpty()) {
			throw new RuntimeException("Debe enviar el email del autor.");
		}
		
		if(suggestionDTO.getSuggestion().getTopic() == null || !topicRepository.existsById(suggestionDTO.getSuggestion().getTopic().getIdTopic())){
			throw new RuntimeException("Debe enviar el tema de la sugerencia.");
		}
		
		User userAutor = iUserService.searchByEmail(suggestionDTO.getEmailUserAutor()).get();
		User userLike = suggestionDTO.getEmailUserLike().isEmpty()? userAutor :  iUserService.searchByEmail(suggestionDTO.getEmailUserLike()).get();
		
		Suggestion suggestion = suggestionDTO.getSuggestion();
		suggestion.setCreationDate(LocalDateTime.now());
		suggestion.setUserAutor(userAutor);
		suggestion.setUserLike(userLike);
		
		try {
			suggestionRepository.save(suggestion);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return Optional.of("Sugerencia registrada");
	}
	
	@Override
	public Optional<?> likeSuggestion(SuggestionDTO suggestionDTO) throws NotFoundException {
		Optional<Suggestion> suggestionOptional =  suggestionRepository.findById(suggestionDTO.getSuggestion().getIdSugestion());
		if(!suggestionOptional.isPresent()) {
			throw new NotFoundException(Suggestion.class, "Sugerencia", suggestionDTO.getSuggestion().getDescription());
		}
		Suggestion suggestionOld = suggestionOptional.get();
		Suggestion suggestionNew = new Suggestion();
		
		User userLike = iUserService.searchByEmail(suggestionDTO.getEmailUserLike()).get();
			
		suggestionNew.setCreationDate(suggestionOld.getCreationDate());
		suggestionNew.setDescription(suggestionOld.getDescription());
		suggestionNew.setTopic(suggestionOld.getTopic());
		suggestionNew.setUserAutor(suggestionOld.getUserAutor());
		suggestionNew.setUserLike(userLike);
		
		try {
			suggestionRepository.save(suggestionNew);
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		
		return Optional.of("Like registrado a la sugerencia " + suggestionNew.getDescription());
	}

	@Override
	public Optional<?> consultSuggestion(SuggestionDTO suggestionDTO) throws NotFoundException {
				
		Optional<Suggestion> suggestionOptional = suggestionRepository.findById(suggestionDTO.getSuggestion().getIdSugestion());
		
		if(!suggestionOptional.isPresent()) {
			throw new NotFoundException(Suggestion.class, "Sugerencia" , suggestionDTO.getSuggestion().getDescription());
		}
		
		AllInfoSugestion infoSuggestion = new AllInfoSugestion();
		infoSuggestion.setSuggestion(suggestionOptional.get());
		User user = suggestionOptional.get().getUserAutor();
		infoSuggestion.setAutorname(user.getName());
		infoSuggestion.setAutorlastname(user.getLastname());
		infoSuggestion.setEmailAutor(user.getEmail());
		infoSuggestion.setNumLikes(suggestionRepository.countByUserAutor(user));
		
		return Optional.of(infoSuggestion);
	}

	@Override
	public Optional<?> reportSuggestions(LocalDateTime startDate, LocalDateTime endingDate) throws NotFoundException{
		List<Suggestion> suggestions = suggestionRepository.findAllByCreationDateBetween(startDate, endingDate);
		if(suggestions.isEmpty()) {
			throw new NotFoundException(Suggestion.class, "Fecha de inicio", startDate.toString(), "Fecha Final", endingDate.toString());
		}
		return Optional.of(suggestions);
	}

	@Override
	public Optional<?> getAllSuggestions() throws NotFoundException {
		List<Suggestion> suggestions = suggestionRepository.findAll();
		if(suggestions.isEmpty()) {
			throw new NotFoundException(Suggestion.class);
		}
		return Optional.of(suggestions);
	}
	
	

}
