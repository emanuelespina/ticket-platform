package org.wdpt6.ticket_platform.ticket_platform.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be null, blank or empty")
    @Size(min = 5, message = "Name must have at least 5 characters")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    private String name;

    @Lob
    @NotBlank(message = "Description must not be null, blank or empty") 
    @Size(min = 5, message = "Name must have at least 5 characters")   
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public enum TicketStatus {
        DA_FARE,
        IN_CORSO,
        COMPLETATO
    }

    @OneToMany( mappedBy = "ticket")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    @NotNull(message = "Operator is mandatory")
    private User user;   

    @ManyToMany
    @JoinTable(
        name = "ticket_category",
        joinColumns = @JoinColumn (name = "ticket_id"),
        inverseJoinColumns = @JoinColumn (name = "category_id")
    )
    private Set<Category> categories;

    public Ticket (){
        this.status = TicketStatus.DA_FARE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }    
    
    
}
