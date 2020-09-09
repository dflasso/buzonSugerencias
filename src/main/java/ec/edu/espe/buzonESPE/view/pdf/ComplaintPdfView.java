package ec.edu.espe.buzonESPE.view.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ec.edu.espe.buzonESPE.model.Carrer;
import ec.edu.espe.buzonESPE.model.Complaint;
import ec.edu.espe.buzonESPE.model.Department;
import ec.edu.espe.buzonESPE.model.Modality;
import ec.edu.espe.buzonESPE.model.User;

@Component("complaint/report")
public class ComplaintPdfView {

	public InputStreamResource buildPdfDocument(Map<String, Object> model) throws DocumentException {

		if (model.isEmpty()) {
			throw new RuntimeException("No se enviaron datos de la denuncia.");
		}

		Document document = new Document(PageSize.A4, 15, 15, 20, 10);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);

		document.open();
		document.add(this.buildHeader());
		document.add(this.buildInfoStudent(model));
		document.add(this.buildInfoAggresion(model));
		document.close();

		return new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

	}

	private PdfPTable buildHeader() throws DocumentException {
		PdfPTable encabezado = new PdfPTable(3);
		float[] anchosColumnas = new float[] { 1.15f, 1.85f, 1.15f };
		encabezado.setWidths(anchosColumnas);
		encabezado.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell imgEspe = new PdfPCell();
		imgEspe.setPadding(0f);
		imgEspe.setBorder(0);
		encabezado.addCell(imgEspe);

		PdfPTable tableTitles = new PdfPTable(1);
		tableTitles.getDefaultCell().setBorder(0);
		PdfPCell titleVicerectorado = new PdfPCell(new Phrase("VICERRECTORADO DE DOCENCIA"));
		titleVicerectorado.setBorder(0);
		titleVicerectorado.setPadding(0f);
		titleVicerectorado.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableTitles.addCell(titleVicerectorado);
		PdfPCell titleUBE = new PdfPCell(new Phrase("UNIDAD DE BIENESTAR ESTUDIANTIL"));
		titleUBE.setBorder(0);
		titleUBE.setPadding(0f);
		titleUBE.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableTitles.addCell(titleUBE);

		PdfPCell cellTitles = new PdfPCell(tableTitles);
		cellTitles.setBorder(0);
		encabezado.addCell(cellTitles);

		PdfPCell imgUBE = new PdfPCell();
		imgUBE.setPadding(0f);
		imgUBE.setBorder(0);
		encabezado.addCell(imgUBE);

		PdfPTable header = new PdfPTable(1);
		header.setWidthPercentage(100);
		header.setHorizontalAlignment(Element.ALIGN_CENTER);
		header.setSpacingAfter(20);
		header.addCell(encabezado);

		PdfPCell nameForm = new PdfPCell(
				new Phrase("FORMULARIO DE RECEPCIÓN DE DENUNCIA DE ACOSO, DISCRIMINACIÓN Y VIOLENCIA"));
		nameForm.setHorizontalAlignment(Element.ALIGN_CENTER);
		nameForm.setBorder(0);
		header.addCell(nameForm);
		PdfPCell descriptionForm = new PdfPCell(
				new Phrase("BASADA EN GÉNERO Y ORIENTACIÓN SEXUAL EN LA UNIVERDIDAD DE LAS FUERZAS ARMADAS"));
		descriptionForm.setHorizontalAlignment(Element.ALIGN_CENTER);
		descriptionForm.setBorder(0);
		header.addCell(descriptionForm);

		return header;
	}

	private PdfPTable buildInfoStudent(Map<String, Object> model) {
		User user = (User) model.get("user");
		if (user == null) {
			throw new RuntimeException("No se enviaron datos del usuario denunciante.");
		}

		PdfPTable dataUserTab = new PdfPTable(1);
		dataUserTab.setWidthPercentage(100f);

		PdfPCell titleDatosIdentificacion = new PdfPCell(new Phrase("1.- DATOS DE IDENTIFICACIÓN"));
		titleDatosIdentificacion.setPadding(10f);
		titleDatosIdentificacion.setBottom(10f);
		titleDatosIdentificacion.setBorderWidthTop(1f);
		titleDatosIdentificacion.setBorderWidthLeft(1f);
		titleDatosIdentificacion.setBorderWidthRight(1f);
		titleDatosIdentificacion.setBorderWidthBottom(1f);
		dataUserTab.addCell(titleDatosIdentificacion);

		PdfPTable infoStuden = new PdfPTable(2);
		infoStuden.setWidthPercentage(100f);

		PdfPCell titleUserCompleteName = new PdfPCell(new Phrase("APELLIDOS Y NOMBRES: "));
		titleUserCompleteName.setPadding(2f);
		titleUserCompleteName.setBorder(0);
		infoStuden.addCell(titleUserCompleteName);

		titleUserCompleteName = new PdfPCell(new Phrase(user.getLastname() + " " + user.getName()));
		titleUserCompleteName.setPadding(2f);
		titleUserCompleteName.setBorder(0);
		infoStuden.addCell(titleUserCompleteName);

		PdfPCell nationalityUser = new PdfPCell(new Phrase("NACIONALIDAD: "));
		nationalityUser.setPadding(2f);
		nationalityUser.setBorder(0);
		infoStuden.addCell(nationalityUser);

		nationalityUser = new PdfPCell(new Phrase(user.getNationality()));
		nationalityUser.setPadding(2f);
		nationalityUser.setBorder(0);
		infoStuden.addCell(nationalityUser);

		PdfPCell genderUser = new PdfPCell(new Phrase("GENERO: " ));
		genderUser.setPadding(2f);
		genderUser.setBorder(0);
		infoStuden.addCell(genderUser);
		
		genderUser = new PdfPCell(new Phrase(user.getGender()));
		genderUser.setPadding(2f);
		genderUser.setBorder(0);
		infoStuden.addCell(genderUser);

		PdfPCell sexUser = new PdfPCell(new Phrase("SEXO: "));
		sexUser.setPadding(2f);
		sexUser.setBorder(0);
		infoStuden.addCell(sexUser);

		sexUser = new PdfPCell(new Phrase(user.getSex()));
		sexUser.setPadding(2f);
		sexUser.setBorder(0);
		infoStuden.addCell(sexUser);

		PdfPCell relationshipUser = new PdfPCell(new Phrase("RELACIÓN CON LA UNIVERSIDAD: "));
		relationshipUser.setPadding(2f);
		relationshipUser.setBorder(0);
		infoStuden.addCell(relationshipUser);

		relationshipUser = new PdfPCell(new Phrase(user.getRelationshipUniversity()));
		relationshipUser.setPadding(2f);
		relationshipUser.setBorder(0);
		infoStuden.addCell(relationshipUser);

		PdfPCell statusCivilUser = new PdfPCell(new Phrase("ESTADO CIVIL: "));
		statusCivilUser.setPadding(2f);
		statusCivilUser.setBorder(0);
		infoStuden.addCell(statusCivilUser);

		statusCivilUser = new PdfPCell(new Phrase(user.getCivilStatus()));
		statusCivilUser.setPadding(2f);
		statusCivilUser.setBorder(0);
		infoStuden.addCell(statusCivilUser);

		PdfPCell placeDateBirthUser = new PdfPCell(new Phrase("LUGAR Y FECHA DE NACIMIENTO: "));
		placeDateBirthUser.setPadding(2f);
		placeDateBirthUser.setBorder(0);
		infoStuden.addCell(placeDateBirthUser);

		placeDateBirthUser = new PdfPCell(new Phrase(user.getPlaceDateBirth()));
		placeDateBirthUser.setPadding(2f);
		placeDateBirthUser.setBorder(0);
		infoStuden.addCell(placeDateBirthUser);

		PdfPCell etnicityUser = new PdfPCell(new Phrase("ETNIA A LA QUE PERTENECE: "));
		etnicityUser.setPadding(0f);
		etnicityUser.setBorder(0);
		infoStuden.addCell(etnicityUser);

		etnicityUser = new PdfPCell(new Phrase(user.getPlaceDateBirth()));
		etnicityUser.setPadding(0f);
		etnicityUser.setBorder(0);
		infoStuden.addCell(etnicityUser);

		PdfPCell directionUser = new PdfPCell(new Phrase("DIRECCIÓN DOMICILIARIA: "));
		directionUser.setPadding(2f);
		directionUser.setBorder(0);
		infoStuden.addCell(directionUser);

		directionUser = new PdfPCell(new Phrase(user.getHomeAddress()));
		directionUser.setPadding(2f);
		directionUser.setBorder(0);
		infoStuden.addCell(directionUser);

		PdfPCell cellphoneUser = new PdfPCell(new Phrase("CELULAR: "));
		cellphoneUser.setPadding(2f);
		cellphoneUser.setBorder(0);
		infoStuden.addCell(cellphoneUser);

		cellphoneUser = new PdfPCell(new Phrase(user.getCellphone()));
		cellphoneUser.setPadding(2f);
		cellphoneUser.setBorder(0);
		infoStuden.addCell(cellphoneUser);

		PdfPCell conventionalPhoneUser = new PdfPCell(new Phrase("TELÉFONO CONVENCIONAL: "));
		conventionalPhoneUser.setPadding(2f);
		conventionalPhoneUser.setBorder(0);
		infoStuden.addCell(conventionalPhoneUser);

		conventionalPhoneUser = new PdfPCell(new Phrase(user.getConventionalTelephone()));
		conventionalPhoneUser.setPadding(2f);
		conventionalPhoneUser.setBorder(0);
		infoStuden.addCell(conventionalPhoneUser);

		PdfPCell discapacidad = new PdfPCell(new Phrase("REGISTRA DISCAPACIDAD: "));
		discapacidad.setPadding(5f);
		discapacidad.setBorder(0);
		infoStuden.addCell(discapacidad);

		discapacidad = new PdfPCell(new Phrase(user.getDisability()));
		discapacidad.setPadding(5f);
		discapacidad.setBorder(0);
		infoStuden.addCell(discapacidad);

		if (user.getCarrer() != null) {
			PdfPCell carrer = new PdfPCell(new Phrase("CARRERA: " ));
			carrer.setPadding(2f);
			carrer.setBorder(0);
			infoStuden.addCell(carrer);

			carrer = new PdfPCell(new Phrase(user.getCarrer().getName()));
			carrer.setPadding(2f);
			carrer.setBorder(0);
			infoStuden.addCell(carrer);
		}

		PdfPCell cellInfoStuden = new PdfPCell(infoStuden);
		cellInfoStuden.setBorder(0);
		dataUserTab.addCell(cellInfoStuden);
		dataUserTab.setSpacingAfter(3f);
		return dataUserTab;
	}

	private PdfPTable buildInfoAggresion(Map<String, Object> model) {
		Complaint complaint = (Complaint) model.get("complaint");
		if (complaint == null) {
			throw new RuntimeException("No se enviaron datos de la denuncia.");
		}
		
		PdfPTable tableComplaint = new PdfPTable(1);
		tableComplaint.setWidthPercentage(100f);

		PdfPCell titleDatosIdentificacion = new PdfPCell(new Phrase("2.- DATOS DE LA AGRESIÓN"));
		titleDatosIdentificacion.setPadding(10f);
		titleDatosIdentificacion.setBottom(10f);
		titleDatosIdentificacion.setBorderWidthTop(1f);
		titleDatosIdentificacion.setBorderWidthLeft(1f);
		titleDatosIdentificacion.setBorderWidthRight(1f);
		titleDatosIdentificacion.setBorderWidthBottom(1f);
		tableComplaint.addCell(titleDatosIdentificacion);

		PdfPTable infoComplaint = new PdfPTable(2);
		infoComplaint.setWidthPercentage(100f);
		
		PdfPCell cell = new PdfPCell(new Phrase("TIPO DE AGRESIÓN: "));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase(complaint.getType()));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		PdfPCell cellInfoComplaint = new PdfPCell(infoComplaint);
		cellInfoComplaint.setBorder(0);
		cellInfoComplaint.setPaddingBottom(5f);
		tableComplaint.addCell(cellInfoComplaint);
		
		
		infoComplaint = new PdfPTable(1);
		infoComplaint.setWidthPercentage(100f);
		
		cell = new PdfPCell(new Phrase("Descripción: " + complaint.getDescription()));
		cell.setPaddingBottom(10f);
		cell.setBorderWidth(0f);
		infoComplaint.addCell(cell);
		
		
		
		cell = new PdfPCell(new Phrase("Efectos: " + complaint.getEffects()));
		cell.setPaddingBottom(10f);
		cell.setBorderWidth(0f);
		infoComplaint.addCell(cell);
	
		
		cellInfoComplaint = new PdfPCell(infoComplaint);
		cellInfoComplaint.setBorder(0);
		tableComplaint.addCell(cellInfoComplaint);
		
		infoComplaint = new PdfPTable(2);
		infoComplaint.setWidthPercentage(100f);
		
		cell = new PdfPCell(new Phrase("Frecuencia de la agresión: " ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase( complaint.getFrequencyAgresion() ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Fecha de la Agresión: " ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase( complaint.getDateAgresion().toString() ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Lugar  de la Agresión:"));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);

		cell = new PdfPCell(new Phrase(complaint.getPlaceAgresion() ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Relación del Agresor con la Universidad: "  ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase( complaint.getRelationshipAggresor() ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Departamento al que pertenece el Agresor: " ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase(complaint.getDepartmentAgresion() ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		String evidence = 	complaint.isExistEvidence()? "Si" : "No"; 
		
		cell = new PdfPCell(new Phrase("Tiene alguna evidencia que acrédite la agresión: "  ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cell = new PdfPCell(new Phrase( evidence ));
		cell.setPadding(2f);
		cell.setBorder(0);
		infoComplaint.addCell(cell);
		
		cellInfoComplaint = new PdfPCell(infoComplaint);
		cellInfoComplaint.setBorder(0);
		tableComplaint.addCell(cellInfoComplaint);
		
		
		return tableComplaint;
	}
}
