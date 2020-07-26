package ec.edu.espe.buzonESPE.services;

import java.util.List;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Modality;

/**
 * @author dany_lasso
 */
public interface IModality {
	public List<Modality> getAllModality() throws NotFoundException;
}
