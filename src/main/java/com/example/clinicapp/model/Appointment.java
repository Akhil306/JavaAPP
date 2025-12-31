package com.example.clinicapp.model;

public class Appointment {

    private String patientName;
    private String problem;
    private int patientAge;
    private String doctorName;
    private String appointmentDate;

    // Getters and Setters
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
    public int getPatientAge() {
        return patientAge;
    }
    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}

