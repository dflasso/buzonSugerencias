package ec.edu.espe.buzonESPE.dto.response;

import ec.edu.espe.buzonESPE.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponseDTO {
	private User user;
	private String carrer;
	private String department;
	private String modality;
}
