package vehicle.iot.vehicletracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vehicle.iot.vehicletracker.entity.Reading;
import vehicle.iot.vehicletracker.entity.Tires;
import vehicle.iot.vehicletracker.repository.ReadingRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Unit Tests for Reading Service
 *
 * @author marafat
 */
@ExtendWith(MockitoExtension.class)
class ReadingServiceImplTest {
    @InjectMocks
    private ReadingServiceImpl readingServiceImpl;

    @Mock
    private ReadingRepository readingRepository;

    private Reading.Builder readingBuilder;

    @BeforeEach
    void setUp() {
        readingBuilder = new Reading.Builder();
        readingBuilder.setVin("5").setLatitude(41.803194).setLongitude(-88.144406)
                .setTimestamp("2017-05-25T17:31:25.268Z").setFuelVolume(1.5).setSpeed(85).setEngineHp(240)
                .setCheckEngineLightOn(true).setEngineCoolantLow(false).setCruiseControlOn(true)
                .setEngineRpm(6300).setTires(new Tires(34, 36, 29, 34));
    }

    @Test
    void create() {
        Reading reading = readingBuilder.build();

        // given
        given(readingRepository.save(reading)).willReturn(reading);

        // when
        Reading result = readingServiceImpl.create(reading);

        // assert
        assertThat(result).isEqualToComparingFieldByField(reading);
    }
}