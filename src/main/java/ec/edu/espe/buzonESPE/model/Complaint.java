package ec.edu.espe.buzonESPE.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

/**
 * @author Dany_lasso
 */
@Entity
@Table(name = "complaint")
@Data
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idComplaint")
	private Long idComplaint;
	
	@Column(name = "description", length = 100,nullable = false)
	private String description;
	
	@Column(name = "type", length = 100,nullable = false)
	private String type; //Fisica - Psicológica - Sexual - Acoso - Discriminación de genero - Otra 
	
	@Column(name = "effects", length = 100,nullable = false)
	private String effects;
	
	@Column(name = "relationshipAggresor", length = 100,nullable = false)
	private String relationshipAggresor;
	
	@Column(name = "frequencyAgresion", length = 100)
	private String frequencyAgresion;
	
	@Column(name = "dateAgresion", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateAgresion;
	
	@Column(name = "sendDateComplaint", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime sendDateComplaint;
	
	@Column(name = "placeAgresion", length = 100)
	private String placeAgresion;
	
	@Column(name = "departmentAgresion", length = 100)
	private String departmentAgresion;
	
	@Column(name = "stateComplaint", length = 100)
	private String stateComplaint; //No procesada - En Proceso - Archivada
	
	@Column(name = "existEvidence")
	private boolean existEvidence;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserInformer",nullable = true, referencedColumnName = "idUser")
	@JsonIgnore
	private User userInformer;
}
