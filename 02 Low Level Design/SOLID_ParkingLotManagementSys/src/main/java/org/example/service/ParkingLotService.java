package org.example.service;


import org.example.model.Ticket;
import org.example.model.Vehicle;
import org.example.repo.ParkingRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class ParkingLotService {

    private ParkingRepository repository;

    private PricingService pricingService;

    public ParkingLotService(
            ParkingRepository repository,
            PricingService pricingService) {

        this.repository = repository;
        this.pricingService = pricingService;
    }

    public String parkVehicle(Vehicle vehicle) {

        String ticketId =
                UUID.randomUUID().toString();

        Ticket ticket =
                new Ticket(
                        ticketId,
                        vehicle,
                        LocalDateTime.now());

        repository.save(ticket);

        return ticketId;
    }

    public double exitVehicle(String ticketId) {

        Ticket ticket =
                repository.find(ticketId);

        long hours =
                ChronoUnit.HOURS.between(
                        ticket.getEntryTime(),
                        LocalDateTime.now());

        double fee =
                pricingService.calculateFee(hours);

        repository.remove(ticketId);

        return fee;
    }
}
