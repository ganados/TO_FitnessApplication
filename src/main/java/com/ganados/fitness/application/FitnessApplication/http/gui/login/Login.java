package com.ganados.fitness.application.FitnessApplication.http.gui.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("login")
public class Login extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm loginForm;

    public Login() {
        setAlignItems(Alignment.CENTER);

        addClassName("login-view");
        setSizeFull();
        this.loginForm = new LoginForm();
        this.loginForm.setAction("login");
        add(loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}