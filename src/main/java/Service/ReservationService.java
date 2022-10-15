package Service;

import Model.Reservation;
import Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {

        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationFound = reservationRepository.getReservation(reservation.getIdReservation());
            if (reservationFound.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!= null){
            Optional<Reservation> reservationFound = reservationRepository.getReservation(reservation.getIdReservation());
            if(!reservationFound.isEmpty()){
                if(reservation.getStartDate()!= null){
                    reservationFound.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!= null){
                    reservationFound.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                return reservationRepository.save(reservationFound.get());
            }
        }
        return reservation;
    }

    public boolean deleteReservation(int reservationId){
        Boolean result = getReservation(reservationId).map(reservationToDelete ->{
            reservationRepository.delete(reservationToDelete);
            return true;
        }).orElse(false);
        return result;
    }
}
