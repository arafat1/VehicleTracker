package vehicle.iot.vehicletracker.service;

import vehicle.iot.vehicletracker.entity.Reading;

/**
 * A Reading Service for interacting with the Reading Repository
 *
 * @author marafat
 */
public interface ReadingService {
    Reading create(Reading reading);
}
