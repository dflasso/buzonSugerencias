package ec.edu.espe.buzonESPE.model;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

/**
 * Sugerencia
 * 
 * @author dany_lasso
 */
@Entity
@Table(name = "suggestion")
@Data
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSugestion")
	private Long idSugestion;

	@Column(name = "description", length = 100)
	@NotNull(message = "La descrición de la Sugerencia es necesaria")
	private String description;

	@Column(name = "creationDate", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime creationDate;
	
	@ManyToOne()
	@JoinColumn(name = "idTopic",nullable = false)
	private Topic topic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserAutor",nullable = false, referencedColumnName = "idUser")
	@JsonIgnore
	private User userAutor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserLike", referencedColumnName = "idUser")
	@JsonIgnore
	private User userLike;

}
