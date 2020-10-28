package pl.project.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "companies_Statistics")
public class CompanieStatistics {
    private int id;
    private BigDecimal price;
    private Timestamp date;
    private Integer volume;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private BigDecimal trendValue;
    private Companie companie;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "volume", nullable = true)
    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "max_price", nullable = true, precision = 2)
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Basic
    @Column(name = "min_price", nullable = true, precision = 2)
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    @Basic
    @Column(name = "trend_value", nullable = true, precision = 2)
    public BigDecimal getTrendValue() {
        return trendValue;
    }

    public void setTrendValue(BigDecimal trendValue) {
        this.trendValue = trendValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanieStatistics that = (CompanieStatistics) o;

        if (id != that.id) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (volume != null ? !volume.equals(that.volume) : that.volume != null) return false;
        if (maxPrice != null ? !maxPrice.equals(that.maxPrice) : that.maxPrice != null) return false;
        if (minPrice != null ? !minPrice.equals(that.minPrice) : that.minPrice != null) return false;
        if (trendValue != null ? !trendValue.equals(that.trendValue) : that.trendValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (maxPrice != null ? maxPrice.hashCode() : 0);
        result = 31 * result + (minPrice != null ? minPrice.hashCode() : 0);
        result = 31 * result + (trendValue != null ? trendValue.hashCode() : 0);
        return result;
    }


    @ManyToOne
    @JoinColumn(name = "companie_id", referencedColumnName = "id")
    public Companie getCompanieByCompanieId() {
        return companie;
    }

    public void setCompanieByCompanieId(Companie companiesByCompanieId) {
        this.companie = companiesByCompanieId;
    }
}
