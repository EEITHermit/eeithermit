package com.hermit.iii.wetalk.controller;

import javax.servlet.http.*;
import javax.websocket.*;
import javax.websocket.server.*;

import com.hermit.iii.emp.model.*;
import com.hermit.iii.member.model.*;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		sec.getUserProperties().put("username", "M" + "訪客");

		if (((HttpSession) request.getHttpSession()).getAttribute("LoginOK") != null) {
			String allClassName = ((HttpSession) request.getHttpSession()).getAttribute("LoginOK").getClass()
					.toString();
			String className = allClassName.substring(allClassName.lastIndexOf(".") + 1);
			if ("MemberVO".equals(className)) {
				MemberVO loginToken = (MemberVO) ((HttpSession) request.getHttpSession()).getAttribute("LoginOK");
				sec.getUserProperties().put("username", "M" + loginToken.getMemName());
			} else {
				sec.getUserProperties().put("username", "M" + "訪客");
			}
		} else if (((HttpSession) request.getHttpSession()).getAttribute("empLoginOK") != null) {
			String allClassName = ((HttpSession) request.getHttpSession()).getAttribute("empLoginOK").getClass()
					.toString();
			String className = allClassName.substring(allClassName.lastIndexOf(".") + 1);
			if ("EmpVO".equals(className)) {
				EmpVO loginToken = (EmpVO) ((HttpSession) request.getHttpSession()).getAttribute("empLoginOK");
				sec.getUserProperties().put("username",
						"E" + loginToken.getPostVO().getPostName() + loginToken.getEmpNO());
			} else {
				sec.getUserProperties().put("username", "E" + "訪客");
			}
		} else {
			sec.getUserProperties().put("username", "M" + "訪客");
		}
	}
}