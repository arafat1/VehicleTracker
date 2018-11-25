package vehicle.iot.vehicletracker.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * A data class for holding vehicle information.
 * Includes a builder for building a vehicle object
 *
 * @author marafat
 */

@EqualsAndHashCode
@ToString
@Getter
@Entity
public final class Vehicle {
    @Id
    private final String vin;
    private final String model;
    private final String make;
    private final int year;
    private final int redlineRmp;
    private final int maxFuelVolume;
    private final String lastServiceDate;
    @OneToMany
    @JoinColumn(name = "vin", referencedColumnName = "vin")
    private Set<Reading> readings;

    public Set<Reading> getReading() {
        return readings;
    }

    /**
     * Builder for building a vehicle object
     */
    public static final class Builder {
        private final String vin;
        private String model;
        private String make;
        private int year;
        private int redlineRpm;
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

        public Builder setRedlineRpm(int redlineRpm) {
            this.redlineRpm = redlineRpm;
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

    private Vehicle(@NotNull Builder builder) {
        this.vin = builder.vin;
        this.model = builder.model;
        this.make = builder.make;
        this.year = builder.year;
        this.redlineRmp = builder.redlineRpm;
        this.maxFuelVolume = builder.maxFuelVolume;
        this.lastServiceDate = builder.lastServiceDate;
    }

    public Vehicle() {
        this.vin = null;
        this.model = null;
        this.make = null;
        this.year = 0;
        this.redlineRmp = 0;
        this.maxFuelVolume = 0;
        this.lastServiceDate = null;
    }
}
