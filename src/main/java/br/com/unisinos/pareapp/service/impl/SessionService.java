package br.com.unisinos.pareapp.service.impl;

import br.com.unisinos.pareapp.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public static final String CONNECTED_USER = "user";

    public Object getAttribute(String name) {
        return getSession().getAttribute(name);
    }

    public void setAttribute(String name, Object value) {
        getSession().setAttribute(name, value);
    }

    public HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public User getLoggedUser() {
        return (User) getAttribute(CONNECTED_USER);
    }

    public void setLoggedUser(User user) {
        setAttribute(CONNECTED_USER, user);
    }
}
