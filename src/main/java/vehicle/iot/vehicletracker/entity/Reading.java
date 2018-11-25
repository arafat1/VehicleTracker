package vehicle.iot.vehicletracker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;

/**
 * A data class for holding reading information.
 * Includes a builder for building a reading object
 *
 * @author marafat
 */
@EqualsAndHashCode
@ToString
@Getter
@Entity
public final class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final String vin;
    private final double latitude;
    private final double longitude;
    private final String timestamp;
    private final double fuelVolume;
    private final int speed;
    private final int engineHp;
    private final boolean checkEngineLightOn;
    private final boolean engineCoolantLow;
    private final boolean cruiseControlOn;
    private final int engineRpm;
    @Embedded
    private final Tires tires;

    public static final class Builder {
        private String vin;
        private double latitude;
        private double longitude;
        private String timestamp;
        private double fuelVolume;
        private int speed;
        private int engineHp;
        private boolean checkEngineLightOn;
        private boolean engineCoolantLow;
        private boolean cruiseControlOn;
        private int engineRpm;
        private Tires tires;

        public Builder() {}

        public Builder setVin(String vin) {
            this.vin = vin;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setFuelVolume(double fuelVolume) {
            this.fuelVolume = fuelVolume;
            return this;
        }

        public Builder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public Builder setEngineHp(int engineHp) {
            this.engineHp = engineHp;
            return this;
        }

        public Builder setCheckEngineLightOn(boolean checkEngineLightOn) {
            this.checkEngineLightOn = checkEngineLightOn;
            return this;
        }

        public Builder setEngineCoolantLow(boolean engineCoolantLow) {
            this.engineCoolantLow = engineCoolantLow;
            return this;
        }

        public Builder setCruiseControlOn(boolean cruiseControlOn) {
            this.cruiseControlOn = cruiseControlOn;
            return this;
        }

        public Builder setEngineRpm(int engineRpm) {
            this.engineRpm = engineRpm;
            return this;
        }

        public Builder setTires(Tires tires) {
            this.tires = tires;
            return this;
        }

        public Reading build() {
            return new Reading(this);
        }
    }

    private Reading(@NonNull Builder builder) {
        vin = builder.vin;
        latitude = builder.latitude;
        longitude = builder.longitude;
        timestamp = builder.timestamp;
        fuelVolume = builder.fuelVolume;
        speed = builder.speed;
        engineHp = builder.engineHp;
        checkEngineLightOn = builder.checkEngineLightOn;
        engineCoolantLow = builder.engineCoolantLow;
        cruiseControlOn = builder.cruiseControlOn;
        engineRpm = builder.engineRpm;
        tires = builder.tires;
    }

    public Reading() {
        vin = null;
        latitude = 0;
        longitude = 0;
        timestamp = null;
        fuelVolume = 0.0;
        speed = 0;
        engineHp = 0;
        checkEngineLightOn = false;
        engineCoolantLow = false;
        cruiseControlOn = false;
        engineRpm = 0;
        tires = null;
    }
}
