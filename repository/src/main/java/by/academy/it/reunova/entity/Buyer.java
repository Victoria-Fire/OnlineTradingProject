package by.academy.it.reunova.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyer")
@Entity
public class Buyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_buyer")
    private String nameBuyer;

    @Column(name = "surname_buyer")
    private String surnameBuyer;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private Set<OrderHistory> orderHistories = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(id, buyer.id) && Objects.equals(nameBuyer, buyer.nameBuyer) && Objects.equals(surnameBuyer, buyer.surnameBuyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameBuyer, surnameBuyer);
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", nameBuyer='" + nameBuyer + '\'' +
                ", surnameBuyer='" + surnameBuyer + '\'' +
                '}';
    }
}

