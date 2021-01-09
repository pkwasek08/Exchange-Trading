package pl.project.offerSellBuyLimit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.project.execDetails.ExecDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecDetailsOfferLimit {
    private ExecDetails execDetails;
    private OfferLimitDTO offerLimitDTO;
    private List<OfferLimitDTO> offerLimitDTOList;

    public ExecDetailsOfferLimit(ExecDetails execDetails, OfferLimitDTO offerLimitDTO) {
        this.execDetails = execDetails;
        this.offerLimitDTO = offerLimitDTO;
    }

    public ExecDetailsOfferLimit(ExecDetails execDetails, List<OfferLimitDTO> offerLimitDTOList) {
        this.execDetails = execDetails;
        this.offerLimitDTOList = offerLimitDTOList;
    }
}
