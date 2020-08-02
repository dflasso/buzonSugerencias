package ec.edu.espe.buzonESPE.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.dto.request.RequestResourcesUser;
import ec.edu.espe.buzonESPE.dto.response.ResponseResourcerUser;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Profile;
import ec.edu.espe.buzonESPE.model.Resource;
import ec.edu.espe.buzonESPE.model.User;
import ec.edu.espe.buzonESPE.repositorys.ResourceRepository;
import ec.edu.espe.buzonESPE.repositorys.UserRepository;

/**
 * Implementación de la gestion de recursos de la aplicacion
 * 
 * @version 1.0.0
 * @author Dany Lasso
 */
@Service
@Primary
public class ResourcesAppService implements IResourcesAppService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IUserService iUserservice;

	
	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public Optional<?> getResourcesByUser(RequestResourcesUser requestReference) throws NotFoundException {
		
		List<User> users = userRepository.findByEmail(requestReference.getEmail());
		User user = this.buildUser(requestReference);
		
		if (users.isEmpty()) {
			user = iUserservice.registerUser(user);
		} else if (users.size() > 1) {
			throw new RuntimeException("El email esta registrado en dos usuarios");
		} else if(users.size() ==  1) {
			user = users.get(0);
		}
		
		ResponseResourcerUser response = new ResponseResourcerUser(this.foundResources(user.getProfile()), user);

		return Optional.of(response);
	}
	

	/**
	 * Construye un usuario en base a la solicitud
	 * @param requestReference
	 * @return User
	 */
	private User buildUser(RequestResourcesUser requestReference) {
		User user = new User();
		user.setEmail(requestReference.getEmail());
		user.setName(requestReference.getName());
		user.setLastname(requestReference.getLastname());
		user.setRol("N");

		return user;
	}
	
	/**
	 * Busca los recursos del perfil
	 * @param profile
	 * @return
	 * @throws NotFoundException
	 */
	public List<Resource> foundResources(Profile profile) throws NotFoundException{
		 List<Resource> resources = resourceRepository.findByProfile(profile);
		 if(resources.isEmpty()) {
			 throw new NotFoundException(Resource.class, "Profile", profile.getName());
		 }
		 return resources;
	}

}
