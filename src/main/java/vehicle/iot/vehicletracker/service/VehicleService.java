package vehicle.iot.vehicletracker.service;

import vehicle.iot.vehicletracker.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findOne();
    Vehicle create(Vehicle vehicle);
    Vehicle update(String vin, Vehicle vehicle);
    Vehicle delete(String vin, Vehicle vehicle);
}
