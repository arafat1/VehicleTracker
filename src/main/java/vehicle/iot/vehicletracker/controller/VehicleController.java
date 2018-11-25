package vehicle.iot.vehicletracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vehicle.iot.vehicletracker.entity.Vehicle;
import vehicle.iot.vehicletracker.service.VehicleService;

import java.util.List;

/**
 * A REST Controller for Vehicles
 *
 * @author marafat
 */
@RestController
@RequestMapping(value = "vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="{vin}")
    public Vehicle findOne(@PathVariable String vin) {
        return vehicleService.findOne(vin);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle>  updateAll(@RequestBody List<Vehicle> vehicles) {
        return vehicleService.updateAll(vehicles);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{vin}")
    public Vehicle update(@PathVariable String vin, @RequestBody Vehicle vehicle) {
        return vehicleService.update(vin, vehicle);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{vin}")
    public void delete(@PathVariable String vin) {
        vehicleService.delete(vin);
    }
}
