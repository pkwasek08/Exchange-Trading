package pl.project.company;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "companies")
public class Company {
    private int id;
    private String industry;
    private BigDecimal revenue;
    private BigDecimal capital;
    private String name;

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
    @Column(name = "industry", nullable = true, length = -1)
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Basic
    @Column(name = "revenue", nullable = true, precision = 1)
    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    @Basic
    @Column(name = "capital", nullable = true, precision = 1)
    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (id != company.id) return false;
        if (industry != null ? !industry.equals(company.industry) : company.industry != null) return false;
        if (revenue != null ? !revenue.equals(company.revenue) : company.revenue != null) return false;
        if (capital != null ? !capital.equals(company.capital) : company.capital != null) return false;
        if (name != null ? !name.equals(company.name) : company.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (revenue != null ? revenue.hashCode() : 0);
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", industry='" + industry + '\'' +
                ", revenue=" + revenue +
                ", capital=" + capital +
                ", name='" + name + '\'' +
                '}';
    }
}
