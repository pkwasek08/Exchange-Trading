package pl.project.offerSellBuy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferSellBuyDTO {
    private Long id;
    private Integer amount;
    private Float price;
    private String type;
    private Date date;
    private Integer companyId;
    private Integer userId;
    private boolean active;
}
