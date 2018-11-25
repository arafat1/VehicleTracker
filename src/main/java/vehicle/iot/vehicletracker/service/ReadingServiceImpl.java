package vehicle.iot.vehicletracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vehicle.iot.vehicletracker.entity.Reading;
import vehicle.iot.vehicletracker.repository.ReadingRepository;

/**
 * An implementation of Reading Service Interface
 *
 * @author marafat
 */
@Service
public class ReadingServiceImpl implements ReadingService {
    private ReadingRepository readingRepository;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Transactional
    @Override
    public Reading create(Reading reading) {
        return readingRepository.save(reading);
    }
}
