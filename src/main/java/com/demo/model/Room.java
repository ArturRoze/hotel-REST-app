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

    private Timestamp startBookingDate;

    private Timestamp endBookingDate;

    private Double price;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<AdditionalOption> additionalOptions = new ArrayList<>();

    public Room() {
    }

    public Room(Integer number, Category category, Double price) {
        this.number = number;
        this.category = category;
        this.price = price;
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

    public Timestamp getStartBookingDate() {
        return startBookingDate;
    }

    public void setStartBookingDate(Timestamp startBookingDate) {
        this.startBookingDate = startBookingDate;
    }

    public Timestamp getEndBookingDate() {
        return endBookingDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setEndBookingDate(Timestamp endBookingDate) {
        this.endBookingDate = endBookingDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AdditionalOption> getAdditionalOptions() {
        return additionalOptions;
    }

    public void setAdditionalOptions(List<AdditionalOption> additionalOptions) {
        this.additionalOptions = additionalOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(number, room.number) &&
                category == room.category &&
                Objects.equals(startBookingDate, room.startBookingDate) &&
                Objects.equals(endBookingDate, room.endBookingDate) &&
                Objects.equals(price, room.price) &&
                Objects.equals(userId, room.userId) &&
                Objects.equals(hotelId, room.hotelId) &&
                Objects.equals(additionalOptions, room.additionalOptions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, number, category, startBookingDate, endBookingDate, price, userId, hotelId, additionalOptions);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", category=" + category +
                ", startBookingDate=" + startBookingDate +
                ", endBookingDate=" + endBookingDate +
                ", price=" + price +
                ", userId=" + userId +
                ", hotelId=" + hotelId +
                ", additionalOptions=" + additionalOptions +
                '}';
    }
}
