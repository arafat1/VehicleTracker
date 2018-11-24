package vehicle.iot.vehicletracker.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A data class for holding vehicle information.
 * Includes a builder for building a vehicle object
 *
 * @author marafat
 */

@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Vehicle {
    @Id
    private final String vin;
    private final String model;
    private final String make;
    private final int year;
    private final int maxRpm;
    private final int maxFuelVolume;
    private final String lastServiceDate;

    /**
     * Builder for building a vehicle object
     */
    public static class Builder {
        private String vin;
        private String model;
        private String make;
        private int year;
        private int maxRpm;
        private int maxFuelVolume;
        private String lastServiceDate;

        public Builder(String vin) {
            this.vin = vin;
        }


        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setMaxRpm(int maxRpm) {
            this.maxRpm = maxRpm;
            return this;
        }

        public Builder setMaxFuelVolume(int maxFuelVolume) {
            this.maxFuelVolume = maxFuelVolume;
            return this;
        }

        public Builder setLastServiceDate(String lastServiceDate) {
            this.lastServiceDate = lastServiceDate;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }

    private Vehicle(Builder builder) {
        this.vin = builder.vin;
        this.model = builder.model;
        this.make = builder.make;
        this.year = builder.year;
        this.maxRpm = builder.maxRpm;
        this.maxFuelVolume = builder.maxFuelVolume;
        this.lastServiceDate = builder.lastServiceDate;
    }

    public Vehicle() {
        this.vin = null;
        this.model = null;
        this.make = null;
        this.year = 0;
        this.maxRpm = 0;
        this.maxFuelVolume = 0;
        this.lastServiceDate = null;
    }
}
