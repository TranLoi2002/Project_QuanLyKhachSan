package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="positions")
public class Position {
	@Id
	private String position_id;
	private String description;
	private String name;
	private int number;
	private Double salary;
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Position(String position_id, String description, String name, int number, Double salary) {
		super();
		this.position_id = position_id;
		this.description = description;
		this.name = name;
		this.number = number;
		this.salary = salary;
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
