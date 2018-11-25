package vehicle.iot.vehicletracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vehicle.iot.vehicletracker.entity.Vehicle;
import vehicle.iot.vehicletracker.exception.BadRequestException;
import vehicle.iot.vehicletracker.exception.ResourceNotFoundException;
import vehicle.iot.vehicletracker.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;

/**
 * Unit Tests for Vehicle Service
 *
 * @author marafat
 */
@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {
    @InjectMocks
    private VehicleServiceImpl vehicleServiceImpl;

    @Mock
    private VehicleRepository vehicleRepository;

    private Vehicle.Builder vehicleBuilder;

    @BeforeEach
    void setUp() {
        vehicleBuilder = new Vehicle.Builder("5");
        vehicleBuilder.setMake("Honda");
        vehicleBuilder.setModel("Accord");
        vehicleBuilder.setYear(2015);
        vehicleBuilder.setRedlineRpm(5000);
        vehicleBuilder.setMaxFuelVolume(15);
        vehicleBuilder.setLastServiceDate("2017-05-25T17:31:25.268Z");
    }

    @Test
    void findAllTest() {
        Vehicle vehicle = vehicleBuilder.build();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);

        // given
        given(vehicleRepository.findAll()).willReturn(vehicles);

        // when
        List<Vehicle> result = vehicleServiceImpl.findAll();

        // assert
        assertThat(result).containsOnlyElementsOf(vehicles);
    }

    @Test
    void findOneTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // given
        given(vehicleRepository.findById("5")).willReturn(Optional.of(vehicle));

        // when
        Vehicle result = vehicleServiceImpl.findOne("5");

        // assert
        assertThat(result).isEqualToComparingFieldByField(vehicle);
    }

    @Test
    void findOneExceptionTest() {
        // assert
        assertThatExceptionOfType(ResourceNotFoundException.class).
                isThrownBy(() -> vehicleServiceImpl.findOne("1"));
    }

    @Test
    void createTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // given
        given(vehicleRepository.save(vehicle)).willReturn(vehicle);

        // when
        Vehicle result = vehicleServiceImpl.create(vehicle);

        // assert
        assertThat(result).isEqualToComparingFieldByField(vehicle);
    }

    @Test
    void creationExceptionTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // given
        given(vehicleRepository.findById("5")).willReturn(Optional.of(vehicle));

        // assert
        assertThatExceptionOfType(BadRequestException.class).
                isThrownBy(() -> vehicleServiceImpl.create(vehicle));
    }

    @Test
    void updateTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // given
        given(vehicleRepository.findById("5")).willReturn(Optional.of(vehicle));
        given(vehicleRepository.save(vehicle)).willReturn(vehicle);

        // when
        Vehicle result = vehicleServiceImpl.update("5", vehicle);

        // assert
        assertThat(result).isEqualToComparingFieldByField(vehicle);
    }

    @Test
    void updateExceptionTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // assert
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> vehicleServiceImpl.update("5", vehicle));
    }

    @Test
    void deleteExceptionTest() {
        Vehicle vehicle = vehicleBuilder.build();

        // assert
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> vehicleServiceImpl.delete("5"));
    }
}