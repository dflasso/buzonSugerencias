package ec.edu.espe.buzonESPE.services;

import org.springframework.core.io.InputStreamResource;

import com.lowagie.text.DocumentException;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;

/**
 * @author Dany_Lasso
 */
public interface IReportService {
	
	public InputStreamResource generateComplaintReportPDF(Long idComplaint) throws NotFoundException, DocumentException;


}
