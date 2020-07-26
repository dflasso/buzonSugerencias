package ec.edu.espe.buzonESPE.services;

import java.util.List;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Topic;

/**
 * Serivios relacionados a Temas de Sugerencias
 * @author Dany_Lasso
 */
public interface ITopicService {
	
	public List<Topic> getAllTopic() throws NotFoundException ;
}
