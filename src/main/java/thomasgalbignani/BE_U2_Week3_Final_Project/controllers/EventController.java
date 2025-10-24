package thomasgalbignani.BE_U2_Week3_Final_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Event;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewEventDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.services.EventService;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_EVENT_ORGANIZER', 'ROLE_COMMON_USER')")
    public Page<Event> getAllEvents(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return this.eventService.getEvents(page, size, sortBy);
    }

    @GetMapping("/{eventId}")
    @PreAuthorize("hasAnyAuthority('ROLE_EVENT_ORGANIZER', 'ROLE_COMMON_USER')")
    public Event findEventsById(@PathVariable UUID eventId) {
        return eventService.findById(eventId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_EVENT_ORGANIZER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Event save(@RequestBody @Validated NewEventDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.eventService.save(body);
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ROLE_EVENT_ORGANIZER')")
    public Event findByIdAndUpdate(@PathVariable UUID eventId, @RequestBody @Validated NewEventDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.eventService.findByIdAndUpdate(eventId, body);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ROLE_EVENT_ORGANIZER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID eventId) {
        this.eventService.findByIdAndDelete(eventId);
    }
}