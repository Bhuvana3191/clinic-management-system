package com.clinic;

import com.clinic.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ClinicApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ===== Patient Setup (User Input) =====
        System.out.print("Enter Patient ID: ");
        String patientId = sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String patientName = sc.nextLine();

        System.out.print("Enter Patient Age: ");
        int patientAge = sc.nextInt();
        sc.nextLine(); // clear leftover newline

        System.out.print("Enter Patient Contact: ");
        String patientContact = sc.nextLine();

        Patient patient = new Patient(patientId, patientName, patientAge, patientContact);
        Patient patient2 = new Patient("P002", "YYY", 45, "+91 8840987123"); // For Testing Purpose
        System.out.println(patient);

        // ===== Doctor Setup =====
        Doctor doctor = new Doctor("Dr.Smith", 300, 500, new ArrayList<>());
        doctor.setDefaultWeekdayAvailability("09:00", "13:00");
        System.out.println(doctor);

        // ===== Demo: isSlotAvailable() standalone checks =====
        System.out.println("Is Monday 10:00 available? " + doctor.isSlotAvailable("Monday", "10:00")); // Expected as True
        System.out.println("Is Sunday 12:00 available? " + doctor.isSlotAvailable("Sunday", "12:00")); // Expected as False because Clinic is closed on the day/time
        System.out.println("Is Wednesday 16:00 available? " + doctor.isSlotAvailable("Wednesday", "16:00")); // Expected as False because Clinic's working hours is outside

        // ===== User's Real Appointment Booking =====
        System.out.print("Enter Appointment Day (e.g., Monday): ");
        String day = sc.nextLine();

        System.out.print("Enter Appointment Time (HH:mm): ");
        String time = sc.nextLine();

        System.out.print("Enter Mode (Online/Offline): ");
        String mode = sc.nextLine();

        Appointment userAppointment = Appointment.bookAppointment(patient, doctor, day, time, mode);
        System.out.println("Your Appointment: " + userAppointment);

        // ===== Demo: Conflict, Rejection & Auto-Suggestion Scenarios =====
        Appointment result1 = Appointment.bookAppointment(patient, doctor, "Monday", "10:00", "Online");
        System.out.println("Result 1: " + result1);

        Appointment result2 = Appointment.bookAppointment(patient, doctor, "Sunday", "12:00", "Offline");
        System.out.println("Result 2: " + result2);

        Appointment result3 = Appointment.bookAppointment(patient, doctor, "Monday", "10:00", "Online");
        System.out.println("Result 3: " + result3);

        Appointment result4 = Appointment.bookAppointment(patient2, doctor, "Monday", "10:00", "Online");
        System.out.println("Result 4: " + result4);

        Appointment result5 = Appointment.bookAppointment(patient2, doctor, "Tuesday", "09:30", "Offline");
        System.out.println("Result 5: " + result5);

        String nextSlot = Appointment.suggestNextAvailableSlot(doctor, "Monday", "10:00");
        System.out.println("Suggested next slot: " + nextSlot);

        // ===== Demo: Cancel Appointment =====
        Appointment.cancelAppointment(doctor, "A002");
        System.out.println(doctor.getBookedAppointments());

        Appointment.cancelAppointment(doctor, "A999");
        System.out.println(doctor.getBookedAppointments());

        // ===== Doctor's Updated Schedule (after all bookings & cancellations) =====
        System.out.println("Doctor's Full Details After Bookings: " + doctor);

        // ===== Pharmacy & Billing =====
        List<Medicine> pharmacyStock = Medicine.createDefaultPharmacyStock();
        System.out.println("Pharmacy Stock: " + pharmacyStock);

        Medicine paracetamol = pharmacyStock.get(0);
        Medicine coughSyrup = pharmacyStock.get(1);

        List<Medicine> medicines = List.of(paracetamol, coughSyrup);

        Bill bill1 = new Bill("B001", result1, medicines);
        bill1.generateBill();
        System.out.println(bill1);
    }
}
