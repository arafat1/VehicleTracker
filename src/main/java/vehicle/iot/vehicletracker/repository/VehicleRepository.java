package vehicle.iot.vehicletracker.repository;

import org.springframework.data.repository.CrudRepository;
import vehicle.iot.vehicletracker.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
