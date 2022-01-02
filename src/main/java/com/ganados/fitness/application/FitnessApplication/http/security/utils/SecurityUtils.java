package com.ganados.fitness.application.FitnessApplication.http.security.utils;

import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.server.HandlerHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SecurityUtils {

    public static boolean isFrameworkInternalRequest(HttpServletRequest httpServletRequest) {
        final String parameter = httpServletRequest.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameter != null
                && Stream.of(HandlerHelper.RequestType.values())
                .anyMatch(identifier -> identifier.getIdentifier().equals(parameter));
    }

    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }
}