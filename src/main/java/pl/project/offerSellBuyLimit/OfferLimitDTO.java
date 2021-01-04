package pl.project.offerSellBuyLimit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OfferLimitDTO {
    Float price;
    Long amount;
    Float purchase;
    int id;
    String type;
    Float limit;
    Date date;
    Integer companyId;
    Integer userId;
    boolean active;

    public OfferLimitDTO(Float price, Long amount) {
        this.price = price;
        this.amount = amount;
        this.purchase = price * amount;
    }

    public OfferLimitDTO(Float price, Long amount, int id, String type, Float limit,
                         Date date, Integer companyId, Integer userId, boolean active) {
        this.price = price;
        this.amount = amount;
        this.id = id;
        this.type = type;
        this.limit = limit;
        this.date = date;
        this.companyId = companyId;
        this.userId = userId;
        this.active = active;
    }
}
