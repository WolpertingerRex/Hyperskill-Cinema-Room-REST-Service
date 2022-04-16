package cinema;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException(String cause) {
        super(cause);
    }

}

@ResponseStatus()
class WrongPasswordException extends RuntimeException {
    public WrongPasswordException (String cause) {
        super(cause);
    }

}
