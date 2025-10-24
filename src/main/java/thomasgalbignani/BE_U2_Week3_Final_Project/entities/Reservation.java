package thomasgalbignani.BE_U2_Week3_Final_Project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User utente;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Reservation(User user, Event event) {
        this.utente = user;
        this.event = event;
    }
}