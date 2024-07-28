package main;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TRanVanLoi_21030831_25");
	}

}
