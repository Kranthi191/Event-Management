package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name; // New field for the user's name
    
    private String role; // New field for the user's role (admin, host, attendee)

    private String email;
    private String password;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Event> hostedEvents;

    @ManyToMany
    @JoinTable(
        name = "user_rsvp",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    
    private List<Event> rsvpEvents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getHostedEvents() {
        return hostedEvents;
    }

    public void setHostedEvents(List<Event> hostedEvents) {
        this.hostedEvents = hostedEvents;
    }

    public List<Event> getRsvpEvents() {
        return rsvpEvents;
    }

    public void setRsvpEvents(List<Event> rsvpEvents) {
        this.rsvpEvents = rsvpEvents;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", role=" + role + ", email=" + email + ", password=" + password
                + ", hostedEvents=" + hostedEvents + ", rsvpEvents=" + rsvpEvents + "]";
    }
}