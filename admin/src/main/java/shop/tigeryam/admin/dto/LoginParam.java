package shop.tigeryam.admin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String code;
}
