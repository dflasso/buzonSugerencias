package ec.edu.espe.buzonESPE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.dto.request.UserRequestDTO;
import ec.edu.espe.buzonESPE.dto.response.UserResponseDTO;
import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.model.Modality;
import ec.edu.espe.buzonESPE.model.User;
import ec.edu.espe.buzonESPE.repositorys.ProfileRepository;
import ec.edu.espe.buzonESPE.repositorys.UserRepository;

/**
 * Implementa servicios relacionados a la gestion de usuarios
 * @version 1.0.0
 * @author dany_lasso
 */
@Service
@Primary
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public User registerUser(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("El correo ya se encuentra registrado con otro usuario");
		}
		user.setRol("N");
		user.setProfile(profileRepository.findByName("Normal").get());
		System.err.println(user.getProfile().getDescription());
		try {
			user = userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return user;
	}

	@Override
	public Optional<?> findResourcesOfUser(User user) {
		
		return null;
	}

	@Override
	public Optional<User> searchByEmail(String email) {
		List<User> usersByEmail =  userRepository.findByEmail(email);
		Optional<User> user = Optional.empty();
		if(usersByEmail.isEmpty() || usersByEmail.size() > 1) {
			throw new RuntimeException("No se encontro el usuario registrado o el"
					+ " correo esta registrado con dos o más usuarios diferentes	");
		}else {
			user = Optional.of(usersByEmail.get(0));
		}
		return user;
	}

	@Override
	public Optional<?> getAllInfoUser(String email) {
		User user = this.searchByEmail(email).get();
		if(user.getCarrer() != null){
			Carrer carrer = user.getCarrer();
			Department department = carrer.getDepartment();
			Modality modality = carrer.getModality();
			
			return Optional.of(new UserResponseDTO(user, carrer.getName(), department.getDescription(), modality.getName()));
		}else {
			return Optional.of(user);
		}		
	}

	@Override
	public Optional<?> saveDetailUser(UserRequestDTO userDTO) {
		User user = this.searchByEmail(userDTO.getUser().getEmail()).get();
		if(!userDTO.getCarrer().getIdCarrer().equals(Long.parseLong("-1"))) {
			user.setCarrer(userDTO.getCarrer());
		}
		user.setCellphone(userDTO.getUser().getCellphone());
		user.setCivilStatus(userDTO.getUser().getCivilStatus());
		user.setConventionalTelephone(userDTO.getUser().getConventionalTelephone());
		user.setCurrentLevel(userDTO.getUser().getCurrentLevel());
		user.setDisability(userDTO.getUser().getDisability());
		user.setEthnicity(userDTO.getUser().getEthnicity());
		user.setGender(userDTO.getUser().getGender());
		user.setHomeAddress(userDTO.getUser().getHomeAddress());
		user.setIdUserESPE(userDTO.getUser().getIdUserESPE());
		user.setNationality(userDTO.getUser().getNationality());
		user.setNumDocument(userDTO.getUser().getNumDocument());
		user.setPlaceDateBirth(userDTO.getUser().getPlaceDateBirth());
		user.setSex(userDTO.getUser().getSex());
		user.setRelationshipUniversity(userDTO.getUser().getRelationshipUniversity());
		try {
			userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		return Optional.of("Detalles de usuario actualizado.");
	}
	
}
