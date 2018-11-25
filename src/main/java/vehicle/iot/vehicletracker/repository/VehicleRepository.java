package vehicle.iot.vehicletracker.repository;

import org.springframework.data.repository.CrudRepository;
import vehicle.iot.vehicletracker.entity.Vehicle;

/**
 * A Vehicle Repository for persisting data
 * @author marafat
 */
public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
