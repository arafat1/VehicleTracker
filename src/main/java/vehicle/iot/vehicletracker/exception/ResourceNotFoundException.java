package vehicle.iot.vehicletracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
