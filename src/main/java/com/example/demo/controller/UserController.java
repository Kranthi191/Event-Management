package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/users")
public class UserController {
	  @Autowired
	    private UserService userService;

	    @GetMapping("/register")
	    public String showRegisterPage() {
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute User user, Model model) {
	        userService.registerUser(user);
	        model.addAttribute("message", "Registration successful! Please log in.");
	        return "login";
	    }

	    @GetMapping("/login")
	    public String showLoginPage() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
	        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
	        if (loggedInUser != null) {
	        	session.setAttribute("loggedInUserId", loggedInUser.getId()); // ✅ store userId in session
	            model.addAttribute("user", loggedInUser);
	            return "redirect:/events";
	        } else {
	            model.addAttribute("error", "Invalid email or password.");
	            return "login";
	        }
	    }

	    @PostMapping("/{userId}/rsvp/{eventId}")
	    @ResponseBody
	    public String rsvpToEvent(@PathVariable Long userId, @PathVariable Long eventId) {
	        return userService.rsvpToEvent(userId, eventId);
	    }

	    @GetMapping("/rsvp-count/{eventId}")
	    @ResponseBody
	    public int getRsvpCount(@PathVariable Long eventId) {
	        return userService.getRsvpCount(eventId);
	    }

	    @GetMapping("/attendees/{eventId}")
	    @ResponseBody
	    public String getAttendees(@PathVariable Long eventId) {
	        return userService.getAttendees(eventId).toString();
	    }
	    
	    @GetMapping("/profile")
	    public String userProfile(HttpSession session, Model model) {
	        Long userId = (Long) session.getAttribute("loggedInUserId");
	        if (userId == null) {
	            return "redirect:/users/login";
	        }

	        User user = userService.findById(userId);
	        if (user != null) {
	            model.addAttribute("rsvpEvents", user.getRsvpEvents());
	            model.addAttribute("userName", user.getName());
	            return "user_profile"; 
	        } else {
	            return "redirect:/error?message=User not found";
	        }
	    }

}
