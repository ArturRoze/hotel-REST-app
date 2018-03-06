package com.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "additional_option")
public class AdditionalOption {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private double price;

    @Column(name = "booking_id")
    private Long bookingId;

    public AdditionalOption() {
    }

    public AdditionalOption(String name, double price, Long bookingId) {
        this.name = name;
        this.price = price;
        this.bookingId = bookingId;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalOption that = (AdditionalOption) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(bookingId, that.bookingId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, bookingId);
    }

    @Override
    public String toString() {
        return "AdditionalOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", bookingId=" + bookingId +
                '}';
    }
}
