package ec.edu.espe.buzonESPE.dto.request;

import ec.edu.espe.buzonESPE.model.Suggestion;
import lombok.Data;

/**
 * @author Dany_Lasso
 */
@Data
public class SuggestionDTO {
	private Suggestion suggestion;
	private String emailUserAutor;
	private String emailUserLike;
}
