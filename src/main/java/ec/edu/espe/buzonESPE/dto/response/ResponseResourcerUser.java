package ec.edu.espe.buzonESPE.dto.response;

import java.util.List;

import ec.edu.espe.buzonESPE.model.Resource;
import ec.edu.espe.buzonESPE.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResourcerUser {
	
	private List<Resource> resources;
	private User user;

}
