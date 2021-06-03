package com.example.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", schema="public")
@ToString(exclude = {"orderItems"})
@NamedQueries({
        @NamedQuery(name = "Order.findAllByConsumerId", query = "SELECT a FROM Order a WHERE a.consumer.id = :id"),
        @NamedQuery(name = "Order.findAllByProductId", query = "SELECT a.order FROM OrderItem a WHERE a.product.id= :id")
})

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "number")
    private Integer number;
    @OneToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
    @Column(name = "localDateTime")
    private LocalDateTime localDateTime;
    @Column(name = "isDeleted")
    private boolean isDeleted;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

}
