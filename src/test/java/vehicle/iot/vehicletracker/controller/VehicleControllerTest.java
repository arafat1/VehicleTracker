package vehicle.iot.vehicletracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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
import vehicle.iot.vehicletracker.entity.Vehicle;
import vehicle.iot.vehicletracker.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Integration Tests
 *
 * @author marafat
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
class VehicleControllerTest {

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

        vehicleBuilder = new Vehicle.Builder("6");
        vehicleBuilder.setMake("Honda").setModel("Turbo").setYear(2013).setRedlineRpm(6000)
                        .setMaxFuelVolume(18).setLastServiceDate("2016-05-25T17:31:25.268Z");
        vehicleRepository.save(vehicleBuilder.build());
    }

    @AfterEach
    void cleanUp() {
        vehicleRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].make", Matchers.is("Honda")));
    }

    @Test
    void findOne() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Accord")));
    }

    @Test
    void findOne404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/7"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void create() throws Exception {
        Vehicle.Builder vehicleBuilder = new Vehicle.Builder("7");
        vehicleBuilder.setMake("Honda").setModel("Civic").setYear(2010).setRedlineRpm(4000)
                .setMaxFuelVolume(12).setLastServiceDate("2016-05-25T17:31:25.268Z");

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post("/vehicles")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsBytes(vehicleBuilder.build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.vin", Matchers.notNullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Civic")));
    }

    @Test
    void create404() throws Exception {
        Vehicle.Builder vehicleBuilder = new Vehicle.Builder("5");
        vehicleBuilder.setMake("Honda").setModel("Civic").setYear(2010).setRedlineRpm(4000)
                .setMaxFuelVolume(12).setLastServiceDate("2016-05-25T17:31:25.268Z");

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicleBuilder.build())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void updateAll() throws Exception {
        Vehicle.Builder vehicleBuilder = new Vehicle.Builder("5");
        vehicleBuilder.setMake("Honda").setModel("Civic").setYear(2010).setRedlineRpm(4000)
                .setMaxFuelVolume(12).setLastServiceDate("2016-05-25T17:31:25.268Z");
        List<Vehicle> vl = new ArrayList<>();
        vl.add(vehicleBuilder.build());

        vehicleBuilder = new Vehicle.Builder("7");
        vehicleBuilder.setMake("Toyota").setModel("Camry").setYear(2009).setRedlineRpm(4500)
                .setMaxFuelVolume(14).setLastServiceDate("2012-05-25T17:31:25.268Z");
        vl.add(vehicleBuilder.build());

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsBytes(vl)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("5")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].model", Matchers.is("Camry")));
    }

    @Test
    void update() throws Exception {
        Vehicle.Builder vehicleBuilder = new Vehicle.Builder("5");
        vehicleBuilder.setMake("Honda").setModel("Civic").setYear(2010).setRedlineRpm(4000)
                .setMaxFuelVolume(12).setLastServiceDate("2016-05-25T17:31:25.268Z");

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(MockMvcRequestBuilders.put("/vehicles/5")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(mapper.writeValueAsBytes(vehicleBuilder.build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.model", Matchers.is("Civic")));

    }

    @Test
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/vehicles/5"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/vehicles/5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}