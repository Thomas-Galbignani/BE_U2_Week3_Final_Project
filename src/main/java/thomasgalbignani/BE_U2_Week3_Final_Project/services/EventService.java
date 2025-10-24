package thomasgalbignani.BE_U2_Week3_Final_Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.Event;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.AlreadyExistException;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.NotFoundException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewEventDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.repositories.EventRepository;

import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getEvents(int page, int size, String sortBy) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.eventRepository.findAll(pageable);
    }

    public Event save(NewEventDTO body) {
        this.eventRepository.findByDateAndLocation(body.date(), body.location())
                .ifPresent(event -> {
                    throw new AlreadyExistException("There is another event in " + body.location() + " at " + body.date());
                });
        this.eventRepository.findByTitle(body.title())
                .ifPresent(event -> {
                    throw new AlreadyExistException("There is another event called " + body.title());
                });

        Event newEvent = new Event(body.title(), body.description(), body.date(), body.location(), body.placesAvailable());
        return this.eventRepository.save(newEvent);
    }

    public Event findById(UUID uuid) {
        return this.eventRepository.findById(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public Event findByIdAndUpdate(UUID id, NewEventDTO body) {
        Event found = this.findById(id);
        found.setTitle(body.title());
        found.setDescription(body.description());
        found.setDate(body.date());
        found.setLocation(body.location());
        found.setPlacesAvailable(body.placesAvailable());
        return this.eventRepository.save(found);
    }

    public void findByIdAndDelete(UUID uuid) {
        Event found = this.findById(uuid);
        this.eventRepository.delete(found);
    }
}