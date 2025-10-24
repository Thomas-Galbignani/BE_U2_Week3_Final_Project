package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;


import java.time.LocalDateTime;

public record GeneralErrorDTO(String message, LocalDateTime timeStamp) {
}
