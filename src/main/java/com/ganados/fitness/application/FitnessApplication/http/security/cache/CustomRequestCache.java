package com.ganados.fitness.application.FitnessApplication.http.security.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityUtils;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

public class CustomRequestCache extends HttpSessionRequestCache {

    @Override
    public void saveRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (!SecurityUtils.isFrameworkInternalRequest(httpServletRequest)) {
            super.saveRequest(httpServletRequest, httpServletResponse);
        }
    }
}