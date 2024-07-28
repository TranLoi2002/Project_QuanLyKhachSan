package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="candidates")
public class Candidate {
	
	@Id
	private String candidate_id;
	private String description;
	private String email;
	private String full_name;
	private String gender;
	private String phone;
	private int year_of_birth;
	
	@ManyToOne
	@JoinColumn(name="position_id")
	private Position position;

	public String getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return full_name;
	}

	public void setFullname(String fullname) {
		this.full_name = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getYear_of_birth() {
		return year_of_birth;
	}

	public void setYear_of_birth(int year_of_birth) {
		this.year_of_birth = year_of_birth;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Candidate(String candidate_id, String description, String email, String full_name, String gender,
			String phone, int year_of_birth, Position position) {
		super();
		this.candidate_id = candidate_id;
		this.description = description;
		this.email = email;
		this.full_name = full_name;
		this.gender = gender;
		this.phone = phone;
		this.year_of_birth = year_of_birth;
		this.position = position;
	}

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
