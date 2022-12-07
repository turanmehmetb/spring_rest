package carRental.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReservationRepository {

	private List<Reservation> reservations = new ArrayList<>();

	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public Reservation getReservation(int reservationId) {
		return reservations.stream().filter(res -> res.getReservationId() == reservationId).findAny().orElse(null);
	}

	public int addReservation(Reservation reservation) {
		int id = 0;
		if(reservations.size() != 0) id = reservations.get(reservations.size()-1).getReservationId()+1;
		reservation.setReservationId(id);
		reservations.add(reservation);

		return id;
	}

	public int deleteReservation(int reservationId) {
		Reservation reservation = reservations.stream().filter(res -> res.getReservationId() == reservationId).findAny().orElse(null);
		if(reservation != null) reservations.remove(reservation);
		else return -1;

		return reservationId;
	}
}
