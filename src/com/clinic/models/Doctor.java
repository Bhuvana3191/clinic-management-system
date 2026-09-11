package com.clinic.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private String name;
    private double consultationFeeOnline;
    private double consultationFeeOffline;
    private List<Availability> availableSlots;
    private List<Appointment> bookedAppointments = new ArrayList<>();

    public Doctor(String name, double consultationFeeOnline, double consultationFeeOffline, List<Availability> availableSlots) {
        this.name = name;
        this.consultationFeeOnline = consultationFeeOnline;
        this.consultationFeeOffline = consultationFeeOffline;
        this.availableSlots = availableSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConsultationFeeOnline() {
        return consultationFeeOnline;
    }

    public void setConsultationFeeOnline(double consultationFeeOnline) {
        this.consultationFeeOnline = consultationFeeOnline;
    }

    public double getConsultationFeeOffline() {
        return consultationFeeOffline;
    }

    public void setConsultationFeeOffline(double consultationFeeOffline) {
        this.consultationFeeOffline = consultationFeeOffline;
    }

    public List<Availability> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<Availability> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<Appointment> getBookedAppointments() {
        return bookedAppointments;
    }

    @Override
    public String toString(){
        return "Doctor Name: " + name +
                "\nOnline Consultation Fee: " + consultationFeeOnline +
                "\nOffline Consultation Fee: " + consultationFeeOffline +
                "\nAvailable Slots: " + availableSlots +
                "\nBooked Appointments: " + bookedAppointments;
    }

    public void addAvailability(String day, String startTime, String endTime){
        this.availableSlots.add(new Availability(day,startTime,endTime));
    }

    public void setDefaultWeekdayAvailability(String startTime, String endTime){
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        for (String day : weekdays){
            addAvailability(day,startTime,endTime);
        }
    }

    public boolean isSlotAvailable(String day,String time){
        for (Availability slot : availableSlots){
            if (day.equals(slot.getDay()) &&
                    time.compareTo(slot.getStartTime()) >= 0
                    && time.compareTo(slot.getEndTime()) <= 0){
                return true;
            }
        }
        return false;
    }

    public boolean isSlotBooked(String day, String time){
        for (Appointment bookedslot : bookedAppointments){
            if (day.equals(bookedslot.getDayOfAppointment()) && time.equals(bookedslot.getAppointmentSlot())){
                return true;
            }
        }
        return false;
    }

    public void addBookedAppointment(Appointment appointment){
        bookedAppointments.add(appointment);
    }
}
