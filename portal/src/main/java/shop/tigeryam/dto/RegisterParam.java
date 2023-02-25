package shop.tigeryam.dto;

import lombok.Data;

@Data
public class RegisterParam {
    private String username;
    private String email;
    private String password;
    private String registerCode;
}
