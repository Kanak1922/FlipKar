package exceptions;

public class InvalidBookingDurationException extends RuntimeException {
    public InvalidBookingDurationException(String message) {
        super(message);
    }
}
