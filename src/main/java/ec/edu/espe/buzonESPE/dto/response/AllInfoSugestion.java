package ec.edu.espe.buzonESPE.dto.response;


import ec.edu.espe.buzonESPE.model.Suggestion;
import lombok.Data;

@Data
public class AllInfoSugestion {
	private Suggestion suggestion;
	private String Autorname;
	private String Autorlastname;
	private String emailAutor;
	private Long numLikes;
}
