package com.treehouseapp.dto;

import com.treehouseapp.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewOrderDTO implements Serializable {
    private Long quantity;
    private OrderStatus status;
    private String name;
    private String image;
    private Double price;
    private LocalDateTime orderDate;
    private LocalDateTime deliveredDate;
    private String description;
    private Double amount;

    @Override
    public String toString() {
        return "ViewOrderDTO{" +
                "quantity=" + quantity +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", deliveredDate=" + deliveredDate +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}