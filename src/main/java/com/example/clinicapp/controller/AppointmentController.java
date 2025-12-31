package com.example.clinicapp.controller;

import com.example.clinicapp.model.Appointment;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final ProducerTemplate producerTemplate;

    public AppointmentController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/book")
    public String bookAppointment(@RequestBody Appointment appointment) {

        // Send appointment to Apache Camel
        producerTemplate.sendBody("direct:bookAppointment", appointment);

        return "Appointment booked successfully for "
                + appointment.getPatientName()
                + " with Doctor "
                + appointment.getDoctorName()
                + " on "
                + appointment.getAppointmentDate();
    }
}



