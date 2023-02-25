package shop.tigeryam.service.impl;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.tigeryam.service.IVerifyCodeService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@Slf4j
public class VerifyCodeServiceImpl implements IVerifyCodeService {
    @Autowired
    private Producer producer;

    @Override
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("verify_code", text);
        BufferedImage image = producer.createImage(text);
        log.info("code : {}", text);
        try(ServletOutputStream out = resp.getOutputStream()){
            ImageIO.write(image, "jpg", out);
        }
    }
}
