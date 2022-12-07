package carRental.reservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    	System.out.println(reservation.getBrand());
        return repository.addReservation(reservation);
    }
    
    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.DELETE)
    public int cancel(@PathVariable int reservationId) {
        return repository.deleteReservation(reservationId);
    }
    
}
