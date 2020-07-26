package ec.edu.espe.buzonESPE.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usuario de la comunidad ESPE
 * @author dany_lasso
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = { "email" })})
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUser")
	private Long idUser;
	
	@Column(name = "idUserESPE", length = 50)
	private String idUserESPE;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "lastname", length = 100, nullable = false)
	private String lastname;
	
	@Column(name = "email", length = 100, nullable = false)
	@Email
	private String email;
	
	@Column(name = "rol", length = 2, nullable = false)
	private String rol;
	
	@Column(name = "nationality", length = 100)
	private String nationality;
	
	@Column(name = "numDocument", length = 30)
	private String numDocument;
	
	@Column(name = "gender", length = 50)
	private String gender;
	
	@Column(name = "sex", length = 2)
	private String sex;
	
	@Column(name = "relationshipUniversity", length = 100)
	private String relationshipUniversity;
	
	@Column(name = "civilStatus", length = 100)
	private String civilStatus;
	
	@Column(name = "placeDateBirth", length = 100)
	private String placeDateBirth;
	
	@Column(name = "ethnicity", length = 100)
	private String ethnicity;
	
	@Column(name = "homeAddress", length = 200)
	private String homeAddress;
	
	@Column(name = "cellphone", length = 20)
	private String cellphone;
	
	@Column(name = "conventionalTelephone", length = 20)
	private String conventionalTelephone;
	
	@Column(name = "disability", length = 100)
	private String disability;
	
	@Column(name = "currentLevel", length = 100)
	private String currentLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCarrer")
	@JsonIgnore
	private Carrer carrer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProfile",nullable = false)
	@JsonIgnore
	private Profile profile;
	
	@OneToMany(mappedBy = "userInformer",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<Complaint> complaints;
	
	@OneToMany(mappedBy = "userTransmitter",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<MessagesTray> sendMessages;
	
	@OneToMany(mappedBy = "userReceiver",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<MessagesTray> receivedMessages;
	
	@OneToMany(mappedBy = "userAutor",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<Suggestion> suggestionsCreated;
	
	@OneToMany(mappedBy = "userLike",fetch = FetchType.LAZY, cascade=CascadeType.ALL )
	@JsonIgnore
	private Set<Suggestion> suggestionsLike;
	
	
}
