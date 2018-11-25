package vehicle.iot.vehicletracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vehicle.iot.vehicletracker.entity.Reading;
import vehicle.iot.vehicletracker.entity.Tires;
import vehicle.iot.vehicletracker.entity.Vehicle;
import vehicle.iot.vehicletracker.repository.VehicleRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
class ReadingControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void setUp() {
        Vehicle.Builder vehicleBuilder = new Vehicle.Builder("5");
        vehicleBuilder.setMake("Honda").setModel("Accord").setYear(2015).setRedlineRpm(5000)
                .setMaxFuelVolume(15).setLastServiceDate("2017-05-25T17:31:25.268Z");
        vehicleRepository.save(vehicleBuilder.build());
    }

    @Test
    void create() throws Exception {
        Reading.Builder readingBuilder = new Reading.Builder();
        readingBuilder.setVin("5").setLatitude(41.803194).setLongitude(-88.144406)
                .setTimestamp("2017-05-25T17:31:25.268Z").setFuelVolume(1.5).setSpeed(85).setEngineHp(240)
                .setCheckEngineLightOn(true).setEngineCoolantLow(false).setCruiseControlOn(true)
                .setEngineRpm(6300).setTires(new Tires(34, 36, 29, 34));

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post("/readings")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsBytes(readingBuilder.build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin", Matchers.is("5")));
    }
}