package com.hermit.iii.wetalk.controller;

import javax.servlet.http.*;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.*;

import com.hermit.iii.member.model.*;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		sec.getUserProperties().put("username", "訪客");

		// reference
		if (((HttpSession) request.getHttpSession()).getAttribute("LoginOK") == null) {
			sec.getUserProperties().put("username", "訪客");
		} else {
			String allClassName = ((HttpSession) request.getHttpSession()).getAttribute("LoginOK").getClass()
					.toString();
			String className = allClassName.substring(allClassName.lastIndexOf(".") + 1);
			if ("MemberVO".equals(className)) {
				MemberVO loginToken = (MemberVO) ((HttpSession) request.getHttpSession()).getAttribute("LoginOK");
				sec.getUserProperties().put("username", loginToken.getMemName());
			}
		}
	}
}