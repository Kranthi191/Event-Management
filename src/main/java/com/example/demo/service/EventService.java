package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

import java.time.LocalDate;

@Service
public class EventService {
	 @Autowired
	    private EventRepository eventRepository;

	    public Event createEvent(Event event) {
	        return eventRepository.save(event);
	    }

	    public List<Event> getAllEvents() {
	        return eventRepository.findAll();
	    }

	   
	    public Event getEventById(Long id) {
	        return eventRepository.findById(id).orElse(null);
	    }
	    public List<Event> filterEvents(String category, String location, LocalDate date) {
	        return eventRepository.findAll().stream()
	            .filter(event -> 
	                (category == null || event.getCategory().equalsIgnoreCase(category)) && 
	                (location == null || event.getLocation().equalsIgnoreCase(location)) &&
	                (date == null || (event.getDate() != null && event.getDate().isEqual(date))) // Handle null date
	            )
	            .collect(Collectors.toList());
	    }

	    public Event updateEvent(Long id, Event updatedEvent) {
	        return eventRepository.findById(id).map(existingEvent -> {
	            existingEvent.setTitle(updatedEvent.getTitle());
	            existingEvent.setDescription(updatedEvent.getDescription());
	            existingEvent.setCategory(updatedEvent.getCategory());
	            existingEvent.setLocation(updatedEvent.getLocation());
	            existingEvent.setDate(updatedEvent.getDate());
	            existingEvent.setStartTime(updatedEvent.getStartTime());
	            existingEvent.setEndTime(updatedEvent.getEndTime());
	            return eventRepository.save(existingEvent);
	        }).orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
	    }

	    public void deleteEvent(Long id) {
	        if (!eventRepository.existsById(id)) {
	            throw new RuntimeException("Event not found with id: " + id);
	        }
	        eventRepository.deleteById(id);
	    }
	    
	   
	    
}
