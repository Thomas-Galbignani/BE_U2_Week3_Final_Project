package thomasgalbignani.BE_U2_Week3_Final_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Reservation;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewReservationDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public Reservation saveReservation(@RequestBody @Validated NewReservationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return reservationService.save(body);
        }
    }
}
