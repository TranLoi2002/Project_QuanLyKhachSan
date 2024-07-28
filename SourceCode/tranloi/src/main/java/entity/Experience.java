package entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="experiences")
public class Experience {
	@Id
	private String company_name;
	private String description;
	private LocalDate from_date;
	private LocalDate to_date;
	@Id
	@ManyToOne
	@JoinColumn(name="position_id")
	private Position position;
	
	@Id
	@ManyToOne
	@JoinColumn(name="candidate_id")
	private Candidate candidate;

	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experience(String company_name, String description, LocalDate from_date, LocalDate to_date,
			Position position, Candidate candidate) {
		super();
		this.company_name = company_name;
		this.description = description;
		this.from_date = from_date;
		this.to_date = to_date;
		this.position = position;
		this.candidate = candidate;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getFrom_date() {
		return from_date;
	}

	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}

	public LocalDate getTo_date() {
		return to_date;
	}

	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
}
