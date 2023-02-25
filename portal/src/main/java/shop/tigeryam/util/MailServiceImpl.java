package shop.tigeryam.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import shop.tigeryam.api.CommonResult;

@Component
@Slf4j
public class MailServiceImpl implements IMailService {
    @Autowired
    private JavaMailSender JavaMailSenderImpl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendregisterMail(String to, String subject, String content) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        JavaMailSenderImpl.send(message);
    }
}
