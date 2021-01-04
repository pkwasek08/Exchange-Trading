package pl.project.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.project.execDetails.ExecDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecDetailsUserStock {
    private ExecDetails execDetails;
    private UserStockDTO userStockDTO;
}
