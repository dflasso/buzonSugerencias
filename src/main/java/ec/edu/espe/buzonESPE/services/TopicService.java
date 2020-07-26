package ec.edu.espe.buzonESPE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Topic;
import ec.edu.espe.buzonESPE.repositorys.TopicRepository;

/**
 * Implementación principal del serivio relacionado a Temas de Sugerencias
 * @author Dany_Lasso
 */
@Service
@Primary
public class TopicService implements ITopicService{
	
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public List<Topic> getAllTopic() throws NotFoundException {
		List<Topic> topics = topicRepository.findAll();
		if(topics.isEmpty()) {
			throw new NotFoundException(Topic.class, "Topics", "there isn't register");
		}
		return topics;
	}

}
