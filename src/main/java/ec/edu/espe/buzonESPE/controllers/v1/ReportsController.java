package ec.edu.espe.buzonESPE.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import ec.edu.espe.buzonESPE.exceptions.NotFoundException;
import ec.edu.espe.buzonESPE.services.IReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/report")
@Api(tags = "Reportes")
@CrossOrigin(origins = "*")
public class ReportsController {

	@Autowired
	private IReportService IReportService;

	@GetMapping(value = "/{idComplaint}/complaint", produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiOperation(value = "Generar Reporte PDF Denuncia")
	public ResponseEntity<InputStreamResource> viewReportComplaint(
			@PathVariable(value = "idComplaint") Long idComplaint) throws NotFoundException, DocumentException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=denuncia.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(IReportService.generateComplaintReportPDF(idComplaint));
	}

}
