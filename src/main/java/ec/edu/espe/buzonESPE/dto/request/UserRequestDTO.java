package ec.edu.espe.buzonESPE.dto.request;

import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
	
	private User user;
	private Carrer carrer;
}
