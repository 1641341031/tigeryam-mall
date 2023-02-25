package shop.tigeryam.dto;

import lombok.Data;

@Data
public class ForgetPwdUpdatePwdParam {
    private String newPassword;
    private String mailAddress;
    private String username;
    private String verifyCode;
}
