package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/events")
public class EventController {
	 @Autowired
	    private EventService eventService;

	    @GetMapping("/create")
	    public String showCreateEventPage() {
	        return "create-event";
	    }
	    

	    @PostMapping("/create")
	    public String createEvent(@ModelAttribute Event event, Model model) {
	        eventService.createEvent(event);
	        return "redirect:/events";
	    }
	    

	    /*@GetMapping("/{id}")
	    public String showEventDetails(@PathVariable Long id, Model model) {
	        Event event = eventService.getAllEvents().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
	        
	        model.addAttribute("event", event);
	        model.addAttribute("userId", userId); 
	        return "event_details";
	    }*/
	    @GetMapping("/{id}")
	    public String showEventDetails(@PathVariable Long id, HttpSession session, Model model) {
	        Long userId = (Long) session.getAttribute("loggedInUserId");
	        if (userId == null) {
	            return "redirect:/users/login"; // fallback if session is missing
	        }

	        Event event = eventService.getAllEvents().stream()
	            .filter(e -> e.getId().equals(id))
	            .findFirst()
	            .orElse(null);

	        model.addAttribute("event", event);
	        model.addAttribute("userId", userId); // ✅ now dynamic!
	        return "event_details";
	    }
	/*    @GetMapping
	    public String showEventList(
	            @RequestParam(required = false) String category,
	            @RequestParam(required = false) String location,
	            Model model) {

	        // Combined filtering logic
	        List<Event> events;
	        if (category == null && location == null) {
	            events = eventService.getAllEvents(); // No filters applied
	        } else {
	            events = eventService.filterEvents(category, location); // Apply filters
	        }

	        model.addAttribute("events", events);
	        return "event_list"; // Ensure this matches the template name
	    }
*/
	    @GetMapping
	    public String showEventList(
	            @RequestParam(required = false) String category,
	            @RequestParam(required = false) String location,
	            @RequestParam(required = false) String date, // New date parameter
	            Model model) {

	        List<Event> events;
	        LocalDate parsedDate = null;

	        // Parse the date if it is provided
	        if (date != null && !date.isEmpty()) {
	            try {
	                parsedDate = LocalDate.parse(date);
	            } catch (DateTimeParseException e) {
	                model.addAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD.");
	            }
	        }

	        // Apply filters
	        if (category == null && location == null && parsedDate == null) {
	            events = eventService.getAllEvents(); // No filters applied
	        } else {
	            events = eventService.filterEvents(category, location, parsedDate); // Apply filters
	        }

	        // Add a message if no events are found
	        if (events.isEmpty()) {
	            model.addAttribute("message", "No events available");
	        }

	        model.addAttribute("events", events);
	        return "event_list"; // Ensure this matches the template name
	    }
	  
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
	        Event event = eventService.updateEvent(id, updatedEvent);
	        return ResponseEntity.ok(event);
	    }
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
	        eventService.deleteEvent(id);
	        return ResponseEntity.ok("Event deleted successfully with id: " + id);
	    }
	    
	   
	   
}
