package vehicle.iot.vehicletracker.service;

import vehicle.iot.vehicletracker.entity.Vehicle;

import java.util.List;

/**
 * A Service for interacting with the repository
 * @author marafat
 */
public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findOne(String vin);
    Vehicle create(Vehicle vehicle);
    //Vehicle[] create(Vehicle[] vehicle);
    Vehicle update(String vin, Vehicle vehicle);
    //Vehicle[] update(String vin, Vehicle[] vehicle);
    void delete(String vin);
}
