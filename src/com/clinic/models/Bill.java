package com.clinic.models;

import java.util.List;

public class Bill {

    private String id;
    private Appointment appointment;
    private List<Medicine> medicinesPurchased;
    private double totalAmount;

    public Bill(String id, Appointment appointment, List<Medicine> medicinesPurchased) {
        this.id = id;
        this.appointment = appointment;
        this.medicinesPurchased = medicinesPurchased;
        this.totalAmount = 0; // will be calculated by generateBill()
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Medicine> getMedicinesPurchased() {
        return medicinesPurchased;
    }

    public void setMedicinesPurchased(List<Medicine> medicinesPurchased) {
        this.medicinesPurchased = medicinesPurchased;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString(){
        return "Bill ID: " + id +
                "\nAppointment ID: " + appointment.getId() +
                "\nPatient Name: " + appointment.getPatient().getName() +
                "\nPatient Contact: " + appointment.getPatient().getContact() +
                "\nAppointment Day: " + appointment.getDayOfAppointment() +
                "\nAppointment Mode: " + appointment.getModeOfAppointment() +
                "\nMedicines Details: " + medicinesPurchased +
                "\nTotal Amount: " + totalAmount;
    }

    // Generate Bill
    public double generateBill(){

        // Check the appointment's mode - pick the right fee from the Doctor (via appointment.getDoctor(),
        // since Appointment already holds a Doctor reference)
        double consultationFee;

        if (appointment.getModeOfAppointment().equals("Online")){
            consultationFee = appointment.getDoctor().getConsultationFeeOnline();
        }else {
            consultationFee = appointment.getDoctor().getConsultationFeeOffline();
        }

        // Loop through medicinesPurchased and sum up all prices(for-each pattern)
        double medicineTotal = 0;

        for (Medicine medicine : medicinesPurchased){
            medicineTotal += medicine.getPrice();
        }

        // Add both together, store it in totalAmount, and return it
        this.totalAmount = consultationFee + medicineTotal;
        return this.totalAmount;
    }
}
