package vehicle.iot.vehicletracker.entity;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * An embeddable class for Reading entity
 *
 * @author marafat
 */
@Data
@AllArgsConstructor
@Embeddable
public final class Tires {
    private final int frontLeft;
    private final int frontRight;
    private final int rearLeft;
    private final int rearRight;

    public Tires() {
        this(0, 0, 0, 0);
    }
}
