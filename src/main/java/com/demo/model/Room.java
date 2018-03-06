package com.demo.model;

import com.demo.domain.Category;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double price;

    @Column(name = "hotel_id")
    private Long hotelId;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Booking> bookings = new ArrayList<>();

    public Room() {
    }

    public Room(Integer number, Category category, Double price, Long hotelId) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.hotelId = hotelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(number, room.number) &&
                category == room.category &&
                Objects.equals(price, room.price) &&
                Objects.equals(hotelId, room.hotelId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, number, category, price, hotelId);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", price=" + price +
                ", hotelId=" + hotelId +
                '}';
    }
}
