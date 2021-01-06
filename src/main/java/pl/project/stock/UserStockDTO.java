package pl.project.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserStockDTO {
    String companyName;
    String industry;
    Float actualPrice;
    int amount;
    Float purchase;
    Float profit;
    Float trend;

    public UserStockDTO(String companyName, String industry, int amount) {
        this.companyName = companyName;
        this.industry = industry;
        this.amount = amount;
        this.profit = 0f;
    }

    public void initPurchase(){
        purchase = amount * actualPrice;
    }
}
