package app.models;

import java.sql.Date;

public class Order {
	private int id;
	private int petId;
	private long ownerID;
	private long vetId;
	private String medicineName;
	private Date dateRegister;
	
	public Order() {
		this.dateRegister = new Date(System.currentTimeMillis());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public long getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public long getVetId() {
		return vetId;
	}

	public void setVetId(long vetId) {
		this.vetId = vetId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
}
