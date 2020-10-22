package pl.project.entieties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposits")
public class Deposit {
    private int id;
    private BigDecimal cash;
    private Integer transactionId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "transaction_id", nullable = true)
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (id != deposit.id) return false;
        if (cash != null ? !cash.equals(deposit.cash) : deposit.cash != null) return false;
        if (transactionId != null ? !transactionId.equals(deposit.transactionId) : deposit.transactionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cash != null ? cash.hashCode() : 0);
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        return result;
    }
}
