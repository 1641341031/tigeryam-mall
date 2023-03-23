package shop.tigeryam.admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


/**
 * 用户注册参数
 */
@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String icon;
    @Email
    private String email;
    private String nickname;
    private String note;
}
