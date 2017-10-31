package com.hermit.iii.wetalk.controller;

import javax.servlet.http.*;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		sec.getUserProperties().put("username", "訪客");
		
		// reference
		if (((HttpSession) request.getHttpSession()).getAttribute("loginOK") == null) {
			sec.getUserProperties().put("username", "訪客");
		} else{
			sec.getUserProperties().put("username",
					((HttpSession) request.getHttpSession()).getAttribute("loginOK").toString());
		}
	}
}