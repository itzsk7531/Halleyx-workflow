package com.example.Halleyx.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendApprovalMail(String empId, String toEmail, String amount, String title) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Expense Approval Notification");

        message.setText(
                "Dear " + empId + ",\n\n" +

                        "We are pleased to inform you that your expense request has been approved.\n\n" +

                        "Expense Details:\n" +
                        "Category : " + title + "\n" +
                        "Amount   : Rs " + amount + "\n\n" +

                        "The approved amount will be credited to your account within 24 hours.\n\n" +

                        "If you have any questions, please feel free to contact the finance team.\n\n" +

                        "Best regards,\n" +
                        "ExpenseFlow Team"
        );

        mailSender.send(message);
    }
}