package carRental.reservation;

public class NotFoundException extends Exception  {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
