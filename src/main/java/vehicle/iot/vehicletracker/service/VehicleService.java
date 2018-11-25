package vehicle.iot.vehicletracker.service;

import vehicle.iot.vehicletracker.entity.Vehicle;

import java.util.List;

/**
 * A Vehicle Service for interacting with the Vehicle Repository
 *
 * @author marafat
 */
public interface VehicleService {
    List<Vehicle> findAll();
    Vehicle findOne(String vin);
    Vehicle create(Vehicle vehicle);
    List<Vehicle> updateAll(List<Vehicle> vehicle);
    Vehicle update(String vin, Vehicle vehicle);
    void delete(String vin);
}
