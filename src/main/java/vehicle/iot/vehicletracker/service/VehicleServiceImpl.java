package vehicle.iot.vehicletracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehicle.iot.vehicletracker.entity.Vehicle;
import vehicle.iot.vehicletracker.exception.BadRequestException;
import vehicle.iot.vehicletracker.exception.ResourceNotFoundException;
import vehicle.iot.vehicletracker.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = (List<Vehicle>) vehicleRepository.findAll();
        if (vehicles == null)
            new ArrayList<>();
        return vehicles;
    }

    @Override
    public Vehicle findOne(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if (!vehicle.isPresent())
            throw new ResourceNotFoundException("Vehicle with vin "+vin+" does not exist");
        return vehicle.get();
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        if (vehicleRepository.findById(vehicle.getVin()).isPresent())
            throw new BadRequestException("Vehicle with vin "+vehicle.getVin()+" already exists");
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(String vin, Vehicle vehicle) {
        if (!vehicleRepository.findById(vin).isPresent())
            throw new ResourceNotFoundException("Vehicle with vin "+vin+" does not exist");
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String vin) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vin);
        if (!vehicle.isPresent())
            throw new ResourceNotFoundException("Vehicle with vin "+vin+" does not exist");
         vehicleRepository.delete(vehicle.get());
    }
}
