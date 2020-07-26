package ec.edu.espe.buzonESPE.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Modalidad de la carrera 
 * @author dany_lasso
 */
@Entity
@Table(name = "modality")
@Data
public class Modality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idModality")
	private Long idModality;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@Column(name = "name", length = 100,nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "modality",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<Carrer> carres;

}
