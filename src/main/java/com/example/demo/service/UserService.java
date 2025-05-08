package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Event;
import com.example.demo.model.User;

@Service
public class UserService {
	  @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private EventRepository eventRepository;

	    public User registerUser(User user) {
	        return userRepository.save(user);
	    }

	    public User loginUser(String email, String password) {
	        User user = userRepository.findByEmail(email);
	        if (user != null && user.getPassword().equals(password)) {
	            return user;
	        }
	        return null;
	    }

	    public String rsvpToEvent(Long userId, Long eventId) {
	        User user = userRepository.findById(userId).orElse(null);
	        Event event = eventRepository.findById(eventId).orElse(null);

	        if (user == null || event == null) {
	            return "User or Event not found.";
	        }

	        if (user.getRsvpEvents().contains(event)) {
	            return "You have already RSVPed to this event.";
	        }

	        user.getRsvpEvents().add(event);
	        userRepository.save(user);
	        return "RSVP successful!";
	    }

	    public int getRsvpCount(Long eventId) {
	        Event event = eventRepository.findById(eventId).orElse(null);
	        if (event != null) {
	            return event.getAttendees().size();
	        }
	        return 0;
	    }

	    public List<User> getAttendees(Long eventId) {
	        Event event = eventRepository.findById(eventId).orElse(null);
	        if (event != null) {
	            return event.getAttendees();
	        }
	        return null;
	    }
	    public User findByName(String name) {
	        return userRepository.findByName(name);
	    }
	    public User findById(Long id) {
	        return userRepository.findById(id).orElse(null);
	    }
}
