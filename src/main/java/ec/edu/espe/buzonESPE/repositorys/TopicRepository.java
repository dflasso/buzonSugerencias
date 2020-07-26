package ec.edu.espe.buzonESPE.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.espe.buzonESPE.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long>{

}
