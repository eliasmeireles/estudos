package com.example.trabalhofinal.component.logout;

import java.io.IOException;

import com.example.trabalhofinal.authority.UsuarioAuthority;
import com.example.trabalhofinal.util.SceneUtil;

public class LogoutController implements TabLogout.LogoutDelegate {

	private final TabLogout tabLogout;

	public LogoutController() {
		this.tabLogout = new TabLogout(this);
	}

	public TabLogout getTabLogout() {
		return tabLogout;
	}

	@Override public void logout() {
		UsuarioAuthority.logout();
		try {
			SceneUtil.setScene("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
