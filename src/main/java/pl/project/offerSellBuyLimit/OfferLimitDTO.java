package pl.project.offerSellBuyLimit;
import lombok.Data;

@Data
public class OfferLimitDTO {
    Float price;
    Long amount;
    Float purchase;

    public OfferLimitDTO(Float price, Long amount) {
        this.price = price;
        this.amount = amount;
        this.purchase = price * amount;
    }

}
