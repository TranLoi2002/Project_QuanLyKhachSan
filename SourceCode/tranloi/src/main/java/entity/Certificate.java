package entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="certificates")
public class Certificate {
	
	@Id
	private String certificate_id;
	private LocalDate issue_date;
	private String name;
	private String organization;
	
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	public String getCertificate_id() {
		return certificate_id;
	}

	public void setCertificate_id(String certificate_id) {
		this.certificate_id = certificate_id;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Certificate(String certificate_id, LocalDate issue_date, String name, String organization,
			Candidate candidate) {
		super();
		this.certificate_id = certificate_id;
		this.issue_date = issue_date;
		this.name = name;
		this.organization = organization;
		this.candidate = candidate;
	}

	public Certificate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
