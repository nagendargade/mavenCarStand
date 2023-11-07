package carstand.in.zorocars.registration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/registration")
public class RegistrationController {
	@Autowired
    private  RegistrationService registrationService;

    @PostMapping(path="/regi")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path="/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}
