package ec.edu.espe.buzonESPE.services;

import java.util.Optional;

import ec.edu.espe.buzonESPE.dto.request.UserRequestDTO;
import ec.edu.espe.buzonESPE.model.User;

/**
 * Gestiona los servicios relacionados a un usuario
 * @author Dany Lasso
 */
public interface IUserService {
	
	public User registerUser(User user);
	
	public Optional<?> findResourcesOfUser(User user);
	
	public Optional<User> searchByEmail(String email);
	
	public Optional<?> getAllInfoUser(String email);
	
	public Optional<?> saveDetailUser(UserRequestDTO userDTO);

}
