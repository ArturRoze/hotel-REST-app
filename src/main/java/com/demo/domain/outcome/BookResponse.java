package com.demo.domain.outcome;

import com.demo.domain.Category;
import com.demo.model.AdditionalOption;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookResponse {

    private Long userId;

    private Long roomId;

    private Long bookingId;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private Category category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp endDate;

    private List<AdditionalOption> additionalOptions;

    public BookResponse() {
    }

    public BookResponse(Long userId, Long roomId, Long bookingId, Double totalPrice, Category category, Timestamp startDate, Timestamp endDate) {
        this.userId = userId;
        this.roomId = roomId;
        this.bookingId = bookingId;
        this.totalPrice = totalPrice;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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
        BookResponse that = (BookResponse) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roomId, that.roomId) &&
                Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                category == that.category &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(additionalOptions, that.additionalOptions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, roomId, bookingId, totalPrice, category, startDate, endDate, additionalOptions);
    }

    @Override
    public String toString() {
        return "BookResponse{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                ", bookingId=" + bookingId +
                ", totalPrice=" + totalPrice +
                ", category=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", additionalOptions=" + additionalOptions +
                '}';
    }
}
