package exceptions;

public class BookingTimeInPastException extends RuntimeException {
    public BookingTimeInPastException(String message) {
        super(message);
    }
}
