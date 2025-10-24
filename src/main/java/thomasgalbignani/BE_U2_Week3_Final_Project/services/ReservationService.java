package thomasgalbignani.BE_U2_Week3_Final_Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Event;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Reservation;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.User;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewReservationDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.repositories.EventRepository;
import thomasgalbignani.BE_U2_Week3_Final_Project.repositories.ReservationRepository;
import thomasgalbignani.BE_U2_Week3_Final_Project.repositories.UserRepository;

import java.time.LocalDate;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public Reservation save(NewReservationDTO body) {
        User user = userRepository.findById(body.userId()).orElseThrow(() -> new BadRequestException("User with id " + body.userId() + " not found!"));
        Event event = eventRepository.findById(body.eventId()).orElseThrow(() -> new BadRequestException("Event with id " + body.eventId() + " not found!"));

        if (event.getReservations().size() >= event.getMaxParticipants()) {
            throw new BadRequestException("The event is full!");
        }

        Reservation newReservation = new Reservation();
        newReservation.setReservationDate(LocalDate.now());
        newReservation.setUser(user);
        newReservation.setEvent(event);

        return reservationRepository.save(newReservation);
    }
}
