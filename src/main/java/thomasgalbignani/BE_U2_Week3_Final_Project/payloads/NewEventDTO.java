package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewEventDTO(@NotEmpty(message = "the title is required!")
                          @Size(min = 3, max = 15, message = " your title must be  between 3 and 15 characters!")
                          String title,
                          @NotEmpty(message = "the descritpion is required!")
                          @Size(min = 3, max = 50, message = " your description must be  between 3 and 50 characters!")
                          String description,
                          @NotNull(message = "Date is required!")
                          LocalDate date,
                          @NotEmpty(message = "Location is required!")
                          @Size(min = 3, max = 15, message = " your location must be  between 3 and 15 characters!")
                          String location,
                          @Min(value = 1, message = "please insert at least 1 available place!")
                          int placesAvailable) {

}