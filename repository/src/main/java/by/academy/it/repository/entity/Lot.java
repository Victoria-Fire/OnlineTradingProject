package by.academy.it.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lot")
@Entity
public class Lot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id", unique = true)
    private Integer id;

    @Column(name = "name_lot")
    private String nameLot;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_price")
    private BigDecimal startPrice;

    @Column(name = "end_price")
    private BigDecimal endPrice;

    @Column(name = "status")
    private Boolean status;

    @OneToOne(mappedBy = "lotInOrderHistory",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private OrderHistory orderHistory;

    @ManyToMany(mappedBy = "lots")
    private List<Buyer> buyers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id) && Objects.equals(nameLot, lot.nameLot) && Objects.equals(description, lot.description) && Objects.equals(startDate, lot.startDate) && Objects.equals(endDate, lot.endDate) && Objects.equals(startPrice, lot.startPrice) && Objects.equals(endPrice, lot.endPrice) && Objects.equals(status, lot.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameLot, description, startDate, endDate, startPrice, endPrice, status);
    }

    @Override
    public String toString() {
        return "Lot{" +
                "id=" + id +
                ", nameLot='" + nameLot + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                ", status=" + status +
                '}';
    }
}

