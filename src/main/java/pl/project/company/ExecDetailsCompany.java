package pl.project.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.project.execDetails.ExecDetails;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecDetailsCompany {
    ExecDetails execDetails;
    List<CompanyInfoDTO> companyIdList;
}
