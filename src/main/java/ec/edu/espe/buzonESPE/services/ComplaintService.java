package ec.edu.espe.buzonESPE.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.Complaint;
import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.model.Modality;
import ec.edu.espe.buzonESPE.model.User;
import ec.edu.espe.buzonESPE.repositorys.ComplaintRepository;

/**
 * @author Dany_Lasso
 */
@Service
@Primary
public class ComplaintService implements IComplaintService{
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private ComplaintRepository complaintRepository;
	

	@Override
	public Optional<?> saveComplaint(Complaint complaint, String emailUserInformer) {
		User userInformer = iUserService.searchByEmail(emailUserInformer).get();
		complaint.setSendDateComplaint(LocalDateTime.now());
		complaint.setUserInformer(userInformer);
		complaint.setStateComplaint("No procesada");
		try {
			complaintRepository.save(complaint);
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		return Optional.of("Denuncia registrada.");
	}

	@Override
	public Optional<?> changeStateComplaint(Long idComplaint, String stateComplaint) throws NotFoundException {
		Optional<Complaint> complaintOptional = complaintRepository.findById(idComplaint);
		if(!complaintOptional.isPresent()) {
			throw new NotFoundException(Complaint.class, "idComplaint", idComplaint.toString());
		}
		complaintOptional.get().setStateComplaint(stateComplaint);
		try {
			complaintRepository.save(complaintOptional.get());
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
		return Optional.of("La denuncia actualizo el estado a " + stateComplaint);
	}

	@Override
	public Optional<?> getComplaintsByUserInformerAndState(String emailUserInformer, String stateComplaint) throws NotFoundException {
		User userInformer = iUserService.searchByEmail(emailUserInformer).get();
		List<Complaint> complaints = complaintRepository.findByUserInformerAndStateComplaint(userInformer, stateComplaint);
		if(complaints.isEmpty()) {
			throw new NotFoundException(Complaint.class, "emailUserInformer", emailUserInformer, "stateComplaint", stateComplaint );
		}
		return  Optional.of(complaints);
	}

	@Override
	public Optional<?> getDetailComplaint(Long idComplaint) throws NotFoundException {
		Optional<Complaint> complaintOptional = complaintRepository.findById(idComplaint);
		if(!complaintOptional.isPresent()) {
			throw new NotFoundException(Complaint.class, "idComplaint", idComplaint.toString());
		}
		User userInformer = complaintOptional.get().getUserInformer();
		Map<String, Object> detailComplaint = new HashMap<String, Object>();
		detailComplaint.put("complaint", complaintOptional.get());
		detailComplaint.put("user", userInformer);
		
		if(userInformer.getCarrer() != null) {
			Carrer carrer = userInformer.getCarrer();
			Department department = carrer.getDepartment();
			Modality modality = carrer.getModality();
			
			detailComplaint.put("carrer", carrer.getName());
			detailComplaint.put("department", department.getName() + " - " + department.getDescription());
			detailComplaint.put("modality", modality.getName());
		}
		
		return Optional.of(detailComplaint);
	}

	@Override
	public Optional<?> getComplaintsByDateAndState(LocalDate startDate, LocalDate endingDate, String stateComplaint)
			throws NotFoundException {
		List<Complaint> complaints = complaintRepository.findByStateComplaintAndSendDateComplaintBetween(stateComplaint, startDate.atStartOfDay(), endingDate.atStartOfDay());
		if(complaints.isEmpty()) {
			throw new NotFoundException(Complaint.class, "startDate", startDate, "endingDate", endingDate, "stateComplaint", stateComplaint);
		}
		return Optional.of(complaints);
	}
	
	
}