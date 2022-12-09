package carRental.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationRepository repository;
	
    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public List<Reservation> list() {
        return repository.getReservations();
    }
    
    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
    public Reservation get(@PathVariable int reservationId) {
        return repository.getReservation(reservationId);
    }
    
    @RequestMapping(value = "/reservation", method = RequestMethod.POST)
    public int add(@RequestBody Reservation reservation) {
        return repository.addReservation(reservation);
    }
    
    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.DELETE)
    public int cancel(@PathVariable int reservationId) throws NotFoundException {
    	int deletedId = repository.deleteReservation(reservationId);
    	if(deletedId == -1) throw new NotFoundException("Reservation not found!");
        return deletedId;
    }
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleReservationNotFoundException(NotFoundException exception){
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
