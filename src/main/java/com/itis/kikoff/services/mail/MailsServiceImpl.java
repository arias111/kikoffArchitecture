package com.itis.kikoff.services.mail;

import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.models.enums.State;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MailsServiceImpl implements MailsService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository usersRepository;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private final Template confirmMailTemplate;

    public MailsServiceImpl() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),
                        "/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try {
            this.confirmMailTemplate = configuration.getTemplate("mail/confirm_mail.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void sendEmailForConfirm(String email, String code) {
        String mailText = getEmailText(code);
        MimeMessagePreparator messagePreparator = getEmail(email, mailText);
        javaMailSender.send(messagePreparator);
    }

    @Override
    public Boolean isConfirmed(String code) {
        Optional<User> user = usersRepository.findByConfirmCode(code);
        if (user.isPresent()) {
            user.get().setState(State.CONFIRMED);
            usersRepository.save(user.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    private MimeMessagePreparator getEmail(String email, String mailText) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(mailFrom);
            messageHelper.setTo(email);
            messageHelper.setSubject("Регистрация");
            messageHelper.setText(mailText, true);
        };
    }

    private String getEmailText(String code) {
        // подготовили данные для шаблона
        Map<String, String> attributes = new HashMap<>();
        attributes.put("confirm_code", code);

        StringWriter writer = new StringWriter();
        try {
            // записали в StringWriter текст сообщения
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        // получили текст сообщения из шаблона
        return writer.toString();
    }
}
