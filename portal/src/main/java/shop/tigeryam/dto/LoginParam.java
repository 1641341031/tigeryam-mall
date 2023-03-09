package shop.tigeryam.dto;

import lombok.Data;

@Data
public class LoginParam {
    private String username;
    private String password;
    private String code;
}
