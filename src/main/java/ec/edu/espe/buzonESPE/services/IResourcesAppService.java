package ec.edu.espe.buzonESPE.services;

import java.util.Optional;

import ec.edu.espe.buzonESPE.dto.request.RequestResourcesUser;
import ec.edu.espe.buzonESPE.exceptions.NotFoundException;

/**
 * Gestiona los recursos que tiene un usuario acceso
 * @author Dany_Lasso
 */
public interface IResourcesAppService {

	public Optional<?> getResourcesByUser(RequestResourcesUser requestReference) throws NotFoundException;
}
