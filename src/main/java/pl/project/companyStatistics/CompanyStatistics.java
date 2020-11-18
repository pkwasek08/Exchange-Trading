package pl.project.companyStatistics;

import pl.project.company.Company;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "companies_Statistics")
public class CompanyStatistics {
    private int id;
    private Float price;
    private Date date;
    private Integer volume;
    private Float maxPrice;
    private Float minPrice;
    private Float trendValue;
    private Company company;

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
    @Column(name = "price", nullable = true, precision = 2)
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Basic
    @Column(name = "min_price", nullable = true, precision = 2)
    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    @Basic
    @Column(name = "trend_value", nullable = true, precision = 2)
    public Float getTrendValue() {
        return trendValue;
    }

    public void setTrendValue(Float trendValue) {
        this.trendValue = trendValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyStatistics that = (CompanyStatistics) o;

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
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CompanyStatistics{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", volume=" + volume +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", trendValue=" + trendValue +
                ", company=" + company +
                '}';
    }
}
