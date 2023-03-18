package di.learning.clean.ma.ci.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingCompanyPayload {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Long adminId;
}
