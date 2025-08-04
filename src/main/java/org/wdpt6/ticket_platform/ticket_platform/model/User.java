package org.wdpt6.ticket_platform.ticket_platform.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username cannot be null, empty or blank")
    private String username;

    @NotBlank(message = "Password cannot be null, empty or blank")
    private String password;

    private boolean notAvailable;   

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
       name = "role_user",
       joinColumns = @JoinColumn (name = "role_id"),
       inverseJoinColumns = @JoinColumn (name = "user_id"))
    private Set<Role> roles;  
   
    @OneToMany (mappedBy = "user")
    private List<Note> notes;

    @OneToMany (mappedBy = "user")
    private List<Ticket> tickets;

    public User (){

        this.notAvailable = false;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     public boolean isNotAvailable() {
        return notAvailable;
    }

    public void setNotAvailable(boolean notAvailable) {
        this.notAvailable = notAvailable;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }   
    

}
