package vehicle.iot.vehicletracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vehicle.iot.vehicletracker.entity.Reading;
import vehicle.iot.vehicletracker.repository.ReadingRepository;

@RestController
@RequestMapping(value = "/readings")
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
