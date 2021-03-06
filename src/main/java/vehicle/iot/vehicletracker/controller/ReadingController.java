package vehicle.iot.vehicletracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vehicle.iot.vehicletracker.entity.Reading;
import vehicle.iot.vehicletracker.service.ReadingService;

/**
 * A REST Controller for Readings
 *
 * @author marafat
 */
@RestController
@RequestMapping(value = "/readings")
public class ReadingController {
    private ReadingService readingService;

    @Autowired
    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Reading create(@RequestBody Reading reading) {
        return readingService.create(reading);
    }
}
