package app.models;

import java.util.Date;

public class HistoryClinical {
	private Date date;
	private long veterinarioId;
	private String reasonConsult;
	private String symptomatology;
	private String diagnosis;
	private String procedure;
	private String medicament;
	private String medicationDosage;
	private String orderID;
	private String vaccinationHistory;
	private String drugAllergy;
	private String detailProcedure;
	private String orderCancellation;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getVeterinarioId() {
		return veterinarioId;
	}

	public void setVeterinarioId(long veterinarioId) {
		this.veterinarioId = veterinarioId;
	}

	public String getReasonConsult() {
		return reasonConsult;
	}

	public void setReasonConsult(String reasonConsult) {
		this.reasonConsult = reasonConsult;
	}

	public String getSymptomatology() {
		return symptomatology;
	}

	public void setSymptomatology(String symptomatology) {
		this.symptomatology = symptomatology;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}

	public String getMedicationDosage() {
		return medicationDosage;
	}

	public void setMedicationDosage(String medicationDosage) {
		this.medicationDosage = medicationDosage;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getVaccinationHistory() {
		return vaccinationHistory;
	}

	public void setVaccinationHistory(String vaccinationHistory) {
		this.vaccinationHistory = vaccinationHistory;
	}

	public String getDrugAllergy() {
		return drugAllergy;
	}

	public void setDrugAllergy(String drugAllergy) {
		this.drugAllergy = drugAllergy;
	}

	public String getDetailProcedure() {
		return detailProcedure;
	}

	public void setDetailProcedure(String detailProcedure) {
		this.detailProcedure = detailProcedure;
	}

	public String getOrderCancellation() {
		return orderCancellation;
	}

	public void setOrderCancellation(String orderCancellation) {
		this.orderCancellation = orderCancellation;
	}
}