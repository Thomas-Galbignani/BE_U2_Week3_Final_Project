package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;

import java.util.UUID;

public record NewReservationDTO(UUID eventId, UUID userId) {
}
