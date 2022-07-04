package by.academy.it.repository.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_history")
@Entity
public class OrderHistory implements Serializable {

    @Id
    @GenericGenerator(name = "one-one",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "lotInOrderHistory"))
    @GeneratedValue(generator = "one-one")
    @Column(name = "lot_id")
    private Integer id;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Lot lotInOrderHistory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistory that = (OrderHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(dateOfPurchase, that.dateOfPurchase) && Objects.equals(currentPrice, that.currentPrice) && Objects.equals(buyer, that.buyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfPurchase, currentPrice, buyer);
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "id=" + id +
                ", dateOfPurchase=" + dateOfPurchase +
                ", currentPrice=" + currentPrice +
                ", buyer=" + buyer +
                '}';
    }
}

