package com.example.clinicapp.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:bookAppointment")
                .marshal().json()   // Java object → JSON
                .to("kafka:appointment-booked?brokers=localhost:9092")
                .log("Appointment sent to Kafka: ${body}");
    }
}
