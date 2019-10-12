package com.rumroute.user;

import com.rumroute.common.FlashMessage;
import com.rumroute.model.user.User;
import com.rumroute.user.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@AllArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping(value = "login")
    public String login(Model model,  HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/spots";
        }

        try {
            Object flash = request.getSession().getAttribute("flash");
            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");
        } catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }
        model.addAttribute("user", new User());
        return "users/login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("user", new User());

        return "users/register";
    }



    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public String register(@Valid User person,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) throws Exception {

        if(!person
                .getCountry()
                .equals( "Puerto Rico")){
            person.setCredits(2);
            person.setHasRedeemed(false);
            person.setSendReminder(true);
            User user = userService.save(person);

            redirectAttributes
                    .addFlashAttribute("flash",
                            new FlashMessage("Please enter with your new Credentials",
                                    FlashMessage.Status.SUCCESS));


            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), person.getConfirmPassword());
            Authentication login = auth.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(login);

            request
                    .getSession()
                    .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());

            return "redirect:/";
        }
        else {

            person.setHasRedeemed(true);
            person.setSendReminder(false);
            User user = service.save(person);

            redirectAttributes.addFlashAttribute("flash",
                    new FlashMessage("Sorry. At this time this offer is only for tourists.",
                            FlashMessage.Status.SUCCESS));
            return "redirect:/";
        }

    }
}
