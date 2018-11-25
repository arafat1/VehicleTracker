package vehicle.iot.vehicletracker.repository;

import org.springframework.data.repository.CrudRepository;
import vehicle.iot.vehicletracker.entity.Reading;

/**
 * A Reading Repository for persisting data
 *
 * @author marafat
 */
public interface ReadingRepository extends CrudRepository<Reading, Integer> {
}
