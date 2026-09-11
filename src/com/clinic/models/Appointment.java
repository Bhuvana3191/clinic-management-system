package com.clinic.models;

import java.util.List;

public class Appointment {

    private String id;
    private Doctor doctor;
    private Patient patient;
    private String dayOfAppointment;
    private String appointmentSlot;
    private String modeOfAppointment;
    private String statusOfAppointment;

    public static int appointmentCounter = 1;

    public Appointment(String id, Doctor doctor, Patient patient,
                       String dayOfAppointment, String appointmentSlot, String modeOfAppointment,
                       String statusOfAppointment) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.dayOfAppointment = dayOfAppointment;
        this.appointmentSlot = appointmentSlot;
        this.modeOfAppointment = modeOfAppointment;
        this.statusOfAppointment = statusOfAppointment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDayOfAppointment() {
        return dayOfAppointment;
    }

    public void setDayOfAppointment(String dayOfAppointment) {
        this.dayOfAppointment = dayOfAppointment;
    }

    public String getAppointmentSlot() {
        return appointmentSlot;
    }

    public void setAppointmentSlot(String appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
    }

    public String getModeOfAppointment() {
        return modeOfAppointment;
    }

    public void setModeOfAppointment(String modeOfAppointment) {
        this.modeOfAppointment = modeOfAppointment;
    }

    public String getStatusOfAppointment() {
        return statusOfAppointment;
    }

    public void setStatusOfAppointment(String statusOfAppointment) {
        this.statusOfAppointment = statusOfAppointment;
    }

    @Override
    public String toString(){
        return "Appointment ID: " + id +
                "\nDoctor: " + doctor.getName() +
                "\nPatient ID: " + patient.getId() +
                "\nPatient Name: " + patient.getName() +
                "\nPatient Age: " + patient.getAge() +
                "\nPatient Contact: " + patient.getContact() +
                "\nDay: " + dayOfAppointment +
                "\nMode: " + modeOfAppointment +
                "\nStatus: " + statusOfAppointment;
    }

    public  static Appointment bookAppointment(Patient patient,Doctor doctor,String day,String time,String mode){

        // Appointment day and time not falls under working hours
        boolean withinWorkingHours = doctor.isSlotAvailable(day,time);

        if (!withinWorkingHours){
            System.out.println("Doctor not available at this day/time");
            return null;
        }

        // Appointment day and time falls already booked hours
        boolean alreadyBooked = doctor.isSlotBooked(day,time);

        String finalTime = time;
        String status = "Confirmed";

        if (alreadyBooked){
            String suggestedTime = suggestNextAvailableSlot(doctor,day,time);

            if (suggestedTime == null){
                System.out.println("No available slot left for " + day);
                return null;
            }

            finalTime = suggestedTime;
            status = "Auto-Confirmed";
            System.out.println("Slot " + time + " was taken Auto-booking next available slot " + suggestedTime);
        }

        // Create and register the appointment since both checks passed
        String newId = "A00" + appointmentCounter;
        appointmentCounter++;

        Appointment newAppointment = new Appointment (newId,doctor,patient,day,finalTime,mode,status);
        doctor.addBookedAppointment(newAppointment);

        return newAppointment;
    }

    // Suggest Appointment for next slot if the patient's asked slot is already booked
    public static String suggestNextAvailableSlot(Doctor doctor, String day, String time){

        // Step 1: Convert 'time' into hours and minutes as numbers
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        while (true){
            // Add 15 minutes
            minute += 15;

            // Roll over to the next hour if minutes reach or exceed 60
            if (minute >= 60){
                minute -= 60;
                hour += 1;
            }

            // Stop condition: if time goes past the end of the day (24:00)
            if (hour >= 24){
                return null;
            }

            // Format the new time back into "HH:mm" style
            String newTime = String.format("%02d:%02d",hour,minute);

            // Check if the slot is available and NOT booked
            if (doctor.isSlotAvailable(day, newTime) && (!doctor.isSlotBooked(day, newTime))){
                return newTime;
            }
        }
    }

    // Cancel Appointment if slot is not available
    public static boolean cancelAppointment(Doctor doctor, String AppointmentID){
        List<Appointment> appointments = doctor.getBookedAppointments();

        for (Appointment appt: appointments){
            if (appt.getId().equals(AppointmentID)){
                appointments.remove(appt);
                System.out.println("Appointment " + AppointmentID + " cancelled.");
                return true;
            }
        }

        System.out.println("Appointment " + AppointmentID + " not found.");
        return false;
    }
}
