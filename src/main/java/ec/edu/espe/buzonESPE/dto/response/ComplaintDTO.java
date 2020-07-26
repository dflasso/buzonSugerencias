package ec.edu.espe.buzonESPE.dto.response;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import ec.edu.espe.buzonESPE.model.Complaint;
import ec.edu.espe.buzonESPE.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dany Lasso
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ComplaintDTO {
	private User userDetail;
	private Complaint complaint;
	
	private String carrer;
	private String department;
	private String modality;
	
	
	
}
