import com.example.Halleyx.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/send-mail")
    public String sendMail(
            @RequestParam String empId,
            @RequestParam String email,
            @RequestParam String amount,
            @RequestParam String title) {

        mailService.sendApprovalMail(empId, email, amount, title);

        return "Mail Sent Successfully!";
    }
}