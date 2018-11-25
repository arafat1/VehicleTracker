package vehicle.iot.vehicletracker.exception;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody()
public class ResourceNotFoundException extends  RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
