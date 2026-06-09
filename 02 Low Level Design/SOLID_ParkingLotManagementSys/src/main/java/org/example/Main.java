package org.example;

import org.example.model.Car;
import org.example.model.Vehicle;
import org.example.repo.ParkingRepository;
import org.example.service.ParkingLotService;
import org.example.service.PricingService;
import org.example.strategy.HourlyPricing;

public class Main {

        public static void main(String[] args) {

            ParkingRepository repository =
                    new ParkingRepository();

            PricingService pricingService =
                    new PricingService(
                            new HourlyPricing());

            ParkingLotService service =
                    new ParkingLotService(
                            repository,
                            pricingService);

            Vehicle car =
                    new Car("MH12AB1234");

            String ticket =
                    service.parkVehicle(car);

            System.out.println(
                    "Ticket Generated : " + ticket);

            double fee =
                    service.exitVehicle(ticket);

            System.out.println(
                    "Parking Fee : " + fee);
        }
}