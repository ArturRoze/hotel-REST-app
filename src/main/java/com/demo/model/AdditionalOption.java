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

    @Column(name = "room_id")
    private Long roomId;

    public AdditionalOption() {
    }

    public AdditionalOption(String name, double price) {
        this.name = name;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalOption that = (AdditionalOption) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "AdditionalOption{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
