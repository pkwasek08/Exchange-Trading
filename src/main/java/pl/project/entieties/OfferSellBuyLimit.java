package pl.project.entieties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers_Sell_Buy_Limit")
public class OfferSellBuyLimit {
    private int id;
    private Integer amount;
    private BigDecimal price;
    private BigDecimal limit;
    private String type;
    private Companie companiesByCompanyId;

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
    @Column(name = "amount", nullable = true)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "limit", nullable = true, precision = 2)
    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    @Basic
    @Column(name = "type", nullable = true, length = -1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferSellBuyLimit that = (OfferSellBuyLimit) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (limit != null ? !limit.equals(that.limit) : that.limit != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Companie getCompaniesByCompanyId() {
        return companiesByCompanyId;
    }

    public void setCompaniesByCompanyId(Companie companiesByCompanyId) {
        this.companiesByCompanyId = companiesByCompanyId;
    }
}
