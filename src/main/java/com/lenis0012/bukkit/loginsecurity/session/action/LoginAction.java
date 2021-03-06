package com.lenis0012.bukkit.loginsecurity.session.action;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import com.lenis0012.bukkit.loginsecurity.session.*;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class LoginAction extends AuthAction {

    public <T> LoginAction(AuthService<T> service, T serviceProvider) {
        super(AuthActionType.LOGIN, service, serviceProvider);
    }

    @Override
    public AuthMode run(final PlayerSession session, ActionResponse response) {
        rehabPlayer(session);
        session.getProfile().setLastLogin(new Timestamp(System.currentTimeMillis()));
        session.getProfile().setIpAddress(session.getPlayer().getAddress().getAddress().toString());
        LoginSecurity.getInstance().getDatabase().save(session.getProfile());
        return AuthMode.AUTHENTICATED;
    }
}
