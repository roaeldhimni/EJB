package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    @ManagedProperty(value = "#{adminController}")
    private AdminController adminController;

    private String username;
    private String password;

    public String login() {
        // Check username and password against the database
        if (adminController.authenticate(username, password)) {
            // Redirect to the desired page upon successful login
            redirectTo("/faces/web/employe/List.xhtml");
            return null; // Navigation will be handled by redirectTo method
        } else {
            // Authentication failed, show error message
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Échec de la connexion", "Vérifiez vos identifiants"));
            return null;
        }
    }

    public void logout() {
        // Implement your logout logic here
        // This method might be useful for clearing session data or performing other logout actions
    }

    private void redirectTo(String path) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + path);
        } catch (IOException e) {
            // Handle the exception (e.g., log it)
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}
