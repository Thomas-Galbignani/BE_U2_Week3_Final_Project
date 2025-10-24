package thomasgalbignani.BE_U2_Week3_Final_Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Reservation;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
