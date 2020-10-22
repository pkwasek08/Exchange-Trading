package pl.project.entieties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {
    private int id;
    private Timestamp date;
    private OfferSellBuy offerSellBuyByOfferSellBuyId;
    private OfferSellBuyLimit offerSellBuyLimitByOfferSellBuyLimitId;

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
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "offer_sell_buy_id", referencedColumnName = "id")
    public OfferSellBuy getOfferSellBuyByOfferSellBuyId() {
        return offerSellBuyByOfferSellBuyId;
    }

    public void setOfferSellBuyByOfferSellBuyId(OfferSellBuy offerSellBuyByOfferSellBuyId) {
        this.offerSellBuyByOfferSellBuyId = offerSellBuyByOfferSellBuyId;
    }

    @ManyToOne
    @JoinColumn(name = "offer_sell_buy_limit_id", referencedColumnName = "id")
    public OfferSellBuyLimit getOfferSellBuyLimitByOfferSellBuyLimitId() {
        return offerSellBuyLimitByOfferSellBuyLimitId;
    }

    public void setOfferSellBuyLimitByOfferSellBuyLimitId(OfferSellBuyLimit offerSellBuyLimitByOfferSellBuyLimitId) {
        this.offerSellBuyLimitByOfferSellBuyLimitId = offerSellBuyLimitByOfferSellBuyLimitId;
    }
}
