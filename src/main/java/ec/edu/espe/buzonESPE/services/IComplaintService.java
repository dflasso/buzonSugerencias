package ec.edu.espe.buzonESPE.services;

import java.time.LocalDate;
import java.util.Optional;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Complaint;

public interface IComplaintService {

	public Optional<?> saveComplaint(Complaint complaint, String emailUserInformer);
	
	public Optional<?> changeStateComplaint(Long idComplaint, String stateComplaint) throws NotFoundException;
	
	public Optional<?> getComplaintsByUserInformerAndState(String emailUserInformer, String stateComplaint) throws NotFoundException;
	
	public Optional<?> getDetailComplaint(Long idComplaint) throws NotFoundException;
	
	public Optional<?> getComplaintsByDateAndState(LocalDate startDate, LocalDate endingDate, String stateComplaint) throws NotFoundException;
}
