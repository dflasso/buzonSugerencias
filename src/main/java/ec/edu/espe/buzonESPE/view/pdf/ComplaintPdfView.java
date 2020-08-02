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
		
		if(model.isEmpty()) {
			throw new RuntimeException("No se enviaron datos de la denuncia.");
		}
		
		Document document = new Document(PageSize.A4, 15, 15, 20, 10);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);

		document.open();
		document.add(this.buildHeader());
		document.add(this.buildInfoStudent(model));
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
		User user = (User) model.get("User");
		if(user == null) {
			throw new RuntimeException("No se enviaron datos del usuario denunciante.");
		}
		
		PdfPTable infoStuden = new PdfPTable(1);
		infoStuden.setWidthPercentage(100f);
		
		PdfPCell titleDatosIdentificacion = new PdfPCell(new Phrase("1.- DATOS DE IDENTIFICACIÓN"));
		titleDatosIdentificacion.setPadding(0f);
		titleDatosIdentificacion.setBorder(0);
		infoStuden.addCell(titleDatosIdentificacion);
		
		PdfPCell titleUserCompleteName = new PdfPCell(new Phrase("APELLIDOS Y NOMBRES: " + user.getLastname() + " " + user.getName() ));
		titleUserCompleteName.setPadding(0f);
		titleUserCompleteName.setBorderWidthTop(1f);
		titleUserCompleteName.setBorderWidthLeft(0);
		titleUserCompleteName.setBorderWidthRight(0);
		titleUserCompleteName.setBorderWidthBottom(0);
		infoStuden.addCell(titleUserCompleteName);
		
		PdfPCell nationalityUser = new PdfPCell(new Phrase("NACIONALIDAD: " + user.getNationality()));
		nationalityUser.setPadding(0f);
		nationalityUser.setBorder(0);
		infoStuden.addCell(nationalityUser);
		
		PdfPCell genderUser = new PdfPCell(new Phrase("GENERO: " + user.getGender()));
		genderUser.setPadding(0f);
		genderUser.setBorder(0);
		infoStuden.addCell(genderUser);
		
		PdfPCell sexUser = new PdfPCell(new Phrase("SEXO: " + user.getSex()));
		sexUser.setPadding(0f);
		sexUser.setBorder(0);
		infoStuden.addCell(sexUser);
		
		PdfPCell relationshipUser = new PdfPCell(new Phrase("RELACIÓN CON LA UNIVERSIDAD: " + user.getRelationshipUniversity()));
		relationshipUser.setPadding(0f);
		relationshipUser.setBorder(0);
		infoStuden.addCell(relationshipUser);
		
		PdfPCell statusCivilUser = new PdfPCell(new Phrase("ESTADO CIVIL: " + user.getCivilStatus()));
		statusCivilUser.setPadding(0f);
		statusCivilUser.setBorder(0);
		infoStuden.addCell(statusCivilUser);
		
		PdfPCell placeDateBirthUser = new PdfPCell(new Phrase("LUGAR Y FECHA DE NACIMIENTO: " + user.getPlaceDateBirth()));
		placeDateBirthUser.setPadding(0f);
		placeDateBirthUser.setBorder(0);
		infoStuden.addCell(placeDateBirthUser);
		
		PdfPCell etnicityUser = new PdfPCell(new Phrase("ETNIA A LA QUE PERTENECE: " + user.getPlaceDateBirth()));
		etnicityUser.setPadding(0f);
		etnicityUser.setBorder(0);
		infoStuden.addCell(etnicityUser);
		
		PdfPCell directionUser = new PdfPCell(new Phrase("DIRECCIÓN DOMICILIARIA: " + user.getHomeAddress()));
		directionUser.setPadding(0f);
		directionUser.setBorder(0);
		infoStuden.addCell(directionUser);
		
		PdfPCell cellphoneUser = new PdfPCell(new Phrase("CELULAR: " + user.getCellphone() ));
		cellphoneUser.setPadding(0f);
		cellphoneUser.setBorder(0);
		infoStuden.addCell(cellphoneUser);
		
		PdfPCell conventionalPhoneUser = new PdfPCell(new Phrase("TELÉFONO CONVENCIONAL: " + user.getConventionalTelephone() ));
		conventionalPhoneUser.setPadding(0f);
		conventionalPhoneUser.setBorder(0);
		infoStuden.addCell(conventionalPhoneUser);
		
		PdfPCell discapacidad = new PdfPCell(new Phrase("REGISTRA DISCAPACIDAD: " + user.getDisability() ));
		discapacidad.setPadding(0f);
		discapacidad.setBorder(0);
		infoStuden.addCell(discapacidad);
		
		PdfPCell carrer = new PdfPCell(new Phrase("CARRERA: " + user.getDisability() ));
		carrer.setPadding(0f);
		carrer.setBorder(0);
		infoStuden.addCell(carrer);
		
		return infoStuden;
	}
}
