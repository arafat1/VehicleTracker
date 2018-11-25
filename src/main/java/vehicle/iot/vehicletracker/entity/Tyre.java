package vehicle.iot.vehicletracker.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public final class Tyre {
    private final int frontLeft;
    private final int frontRight;
    private final int rearLeft;
    private final int rearRight;

    public Tyre() {
        this(0, 0, 0, 0);
    }
}
