package org.aseguradora.filters;

import org.aseguradora.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        if (customer != null && customer.hasRole("ROLE_ADMIN").isPresent()) {
            return true;
        } else if (customer != null && customer.hasRole("ROLE_USER").isPresent()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no estás autorizado para ingresar a esta página!");
            response.sendRedirect(request.getContextPath());
            return false;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no estás autorizado para ingresar a esta página!");
            response.sendRedirect(request.getContextPath().concat("/login"));
            return false;
        }
    }

}