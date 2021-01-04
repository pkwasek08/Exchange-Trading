package pl.project.offerSellBuyLimit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.project.execDetails.ExecDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecDetailsOfferLimit {
    private ExecDetails execDetails;
    private OfferLimitDTO offerLimitDTO;
}
