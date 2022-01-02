package com.ganados.fitness.application.FitnessApplication.http.security.config;

import com.ganados.fitness.application.FitnessApplication.http.gui.login.Login;
import com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUiServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            final UI ui = uiInitEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent beforeEnterEvent) {
        if (!Login.class.equals(beforeEnterEvent.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            beforeEnterEvent.rerouteTo(Login.class);
        }
    }
}
