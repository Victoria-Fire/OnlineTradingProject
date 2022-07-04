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
@Table(name = "seller")
@Entity
public class Seller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_seller")
    private String nameSeller;

    @Column(name = "surname_seller")
    private String surnameSeller;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Set<Lot> lots = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id) && Objects.equals(nameSeller, seller.nameSeller) && Objects.equals(surnameSeller, seller.surnameSeller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSeller, surnameSeller);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", nameSeller='" + nameSeller + '\'' +
                ", surnameSeller='" + surnameSeller + '\'' +
                '}';
    }
}