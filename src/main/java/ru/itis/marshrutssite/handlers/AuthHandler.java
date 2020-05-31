package ru.itis.marshrutssite.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;
import ru.itis.marshrutssite.dto.UserDto;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.services.UsersService;

import java.util.Map;
import java.util.Optional;

@Component
public class AuthHandler implements HandshakeInterceptor {


    @Autowired
    private UsersService usersService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        String mail = WebUtils.getCookie(request.getServletRequest(), "email").getValue();
        if (mail == null) {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
        Optional<User> optionalUser = usersService.check(mail);
        if (optionalUser.isPresent()) {
            map.put("user", optionalUser.get());
            logger.info("authchat user found:  " + optionalUser.get().getEmail());
            return true;
        } else {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }

    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}