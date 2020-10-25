package pl.project.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
public class Deposit {
    private int id;
    private BigDecimal cash;
    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cash", nullable = true, precision = 2)
    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (id != deposit.id) return false;
        if (cash != null ? !cash.equals(deposit.cash) : deposit.cash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUserByUserId() {
        return user;
    }

    public void setUserByUserId(User userByUserId) {
        this.user = userByUserId;
    }
}
