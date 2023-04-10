package shop.tigeryam.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.tigeryam.admin.service.IVerifyCodeService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@Slf4j
public class VerifyCodeController {
    @Autowired
    private IVerifyCodeService verifyCodeService;

    @GetMapping("/verify-code")
    public void VerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
       verifyCodeService.getVerifyCode(resp, session);
    }
}