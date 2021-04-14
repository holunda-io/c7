package io.holunda.example.camunda.autologin.web;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.webapp.impl.security.auth.AuthenticationService;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/public")
public class UserCreationServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationServlet.class);
    private final IdentityService identityService;
    private final AuthenticationService authenticationService = new AuthenticationService();

    public UserCreationServlet(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping(value = "/create-user", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView createAndLogin(@Valid @ModelAttribute UserCreationServlet.NewUserModel newUserModel, HttpSession session) {

        String userId = newUserModel.userId.trim();

        try {
            if (identityService.createUserQuery().userId(userId).count() == 0) {
                LOGGER.info("User created: '" + userId + "'");
                final User user = identityService.newUser(userId);
                user.setFirstName(newUserModel.firstName);
                user.setLastName(newUserModel.lastName);
                identityService.saveUser(user);
            }
            LOGGER.info("Cleared authentication");
            Authentications.clearCurrent();
            Authentications authentications = new Authentications();
            authentications.addAuthentication(authenticationService.createAuthenticate("default", userId));
            Authentications.updateSession(session, authentications);
            LOGGER.info("User '" + userId + "' is now logged in.");
            // redirect to welcome
            return new ModelAndView("redirect:/app/welcome/default/");
        } catch (Exception e) {
            LOGGER.error("Error logging you in", e);
            return new ModelAndView("/index.html");
        }
    }

    /**
     * Spring request model.
     */
    public static class NewUserModel {

        @NotNull
        @NotEmpty
        private String userId;
        private String lastName;
        private String firstName;

        public NewUserModel(String userId, String lastName, String firstName) {
            this.userId = userId;
            this.lastName = lastName;
            this.firstName = firstName;
        }

        public NewUserModel() {
        }

        public String getUserId() {
            return userId;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }


}
