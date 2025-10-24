package thomasgalbignani.BE_U2_Week3_Final_Project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private String location;
    private int maxParticipants;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Reservation> reservations;

    public Event(String title, String description, LocalDate date, String location, int maxParticipants) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

    public void setPlacesAvailable(@Min(value = 1, message = "please insert at least 1 available place!") int i) {
    }
}