package ec.edu.espe.buzonESPE.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.view.pdf.ComplaintPdfView;

/**
 * @version 1.0.0
 * @author Dany_Lasso
 */
@Service
@Primary
public class ReportService implements IReportService{
	
	@Autowired
	private IComplaintService IComplaintService;
	
	@Autowired
	private ComplaintPdfView complaintPdfView;

	@Override
	public InputStreamResource generateComplaintReportPDF(Long idComplaint) throws NotFoundException, DocumentException {
		return complaintPdfView.buildPdfDocument((Map<String, Object>) IComplaintService.getDetailComplaint(idComplaint).get());
	}

}
