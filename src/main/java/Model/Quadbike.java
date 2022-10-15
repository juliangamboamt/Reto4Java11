package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
public class Quadbike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String brand;
    @Column(name="years")
    private Integer year; //Número entero de 4 digitos
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("quadbikes")
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy= "quadbike")
    @JsonIgnoreProperties({"quadbike", "client"})
    public List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy= "quadbike")
    @JsonIgnoreProperties({"quadbike", "messages"})
    public List<Reservation> reservations;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
