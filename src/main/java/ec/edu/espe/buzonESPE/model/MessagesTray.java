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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

/**
 * @author Dany_lasso
 */
@Entity
@Table(name = "messagesTray")
@Data
public class MessagesTray {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMessagesTray")
	private Long idMessagesTray;
	
	@Column(name = "message", length = 500,nullable = false)
	private String message;
	
	@Column(name = "stateMessage", length = 100, nullable = false)
	private String stateMessage; //No Leido - Leido
	
	@Column(name = "userNameTransmitter", length = 200)
	private String userNameTransmitter; 
	
	@Column(name = "userNameReceiver", length = 200)
	private String userNameReceiver; 
	
	@Column(name = "sendMessage", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd kk:mm:ss")
	private LocalDateTime sendMessage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserTransmitter",nullable = false, referencedColumnName = "idUser")
	@JsonIgnore
	private User userTransmitter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUserReceiver", nullable = false, referencedColumnName = "idUser")
	@JsonIgnore
	private User userReceiver;
}
