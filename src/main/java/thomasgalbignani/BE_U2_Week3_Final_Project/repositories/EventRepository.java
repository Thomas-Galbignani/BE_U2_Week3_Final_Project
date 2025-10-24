package thomasgalbignani.BE_U2_Week3_Final_Project.repositories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Event;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findByDateAndLocation(@NotNull(message = "Date is required!") LocalDate date,
                                          @NotEmpty(message = "Location is required!")
                                          @Size(min = 3, max = 15, message = " your location must be  between 3 and 15 characters!") String location);

    Optional<Event> findByTitle(@NotEmpty(message = "the title is required!")
                                @Size(min = 3, max = 15, message = " your title must be  between 3 and 15 characters!") String title);
}