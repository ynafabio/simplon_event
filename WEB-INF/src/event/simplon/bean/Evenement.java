package event.simplon.bean;

import java.util.Date;

public class Evenement {

	private Integer idEvenement;
	private Date dateEvenement;
	private String libEvenement;
    private Integer idParticipant;

    private String nomParticipant;
    private String prenomParticipant;
    private String telParticipant;
    private String emailParticipant;
    
    private Date date1;
	private Date date2;
	
    
	
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Integer getIdEvenement() {
		return idEvenement;
	}
	public void setIdEvenement(Integer idEvenement) {
		this.idEvenement = idEvenement;
	}
	public Date getDateEvenement() {
		return dateEvenement;
	}
	public void setDateEvenement(Date dateEvenement) {
		this.dateEvenement = dateEvenement;
	}
	public String getLibEvenement() {
		return libEvenement;
	}
	public void setLibEvenement(String libEvenement) {
		this.libEvenement = libEvenement;
	}
	public Integer getIdParticipant() {
		return idParticipant;
	}
	public void setIdParticipant(Integer idParticipant) {
		this.idParticipant = idParticipant;
	}
	public String getNomParticipant() {
		return nomParticipant;
	}
	public void setNomParticipant(String nomParticipant) {
		this.nomParticipant = nomParticipant;
	}
	public String getPrenomParticipant() {
		return prenomParticipant;
	}
	public void setPrenomParticipant(String prenomParticipant) {
		this.prenomParticipant = prenomParticipant;
	}
	public String getTelParticipant() {
		return telParticipant;
	}
	public void setTelParticipant(String telParticipant) {
		this.telParticipant = telParticipant;
	}
	public String getEmailParticipant() {
		return emailParticipant;
	}
	public void setEmailParticipant(String emailParticipant) {
		this.emailParticipant = emailParticipant;
	}
    
    
    
	
	
}
