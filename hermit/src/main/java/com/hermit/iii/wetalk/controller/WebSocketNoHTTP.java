package com.hermit.iii.wetalk.controller;

import java.io.*;
import java.util.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket.do", configurator = WebSocketConfigurator.class)
public class WebSocketNoHTTP {
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	private String user = "";
	List<String> recordmsg = new ArrayList<String>();
	Map<String,String> recordMap = new HashMap<String,String>();
	
	@OnOpen
	public void handleOpen(EndpointConfig endpointConfig, Session userSession) {
		System.out.println("WebSocket成功啟動");
		userSession.getUserProperties().put("username", endpointConfig.getUserProperties().get("username"));
		users.add(userSession);
		try {
			if (endpointConfig.getUserProperties().get("username").toString().indexOf("系統管理員") >= 0) {
				userSession.getBasicRemote().sendText(buildJsonData("系統訊息", "系統管理員大人連線成功!"));
			} else if (endpointConfig.getUserProperties().get("username").toString().indexOf("客服人員") >= 0) {
				userSession.getBasicRemote().sendText(buildJsonData("系統訊息", "小天使客服人員連線成功!"));
			} else {
				userSession.getBasicRemote().sendText(buildJsonData("系統訊息", "連線成功!"));
				userSession.getBasicRemote().sendText(buildJsonData("後台管理員", "請問有什麼需要服務的嗎?"));
			}
		} catch (IOException e) {
			System.out.println("IOException好吵");
		}
	}

	@OnMessage
	public void handleMessage(String message, Session userSession) {
		
		String keyWord = userSession.getUserProperties().get("username").toString().substring(0,1);
		user = userSession.getUserProperties().get("username").toString().substring(1);

		Iterator<Session> iterator = users.iterator();
		try {
			while (iterator.hasNext()) {
				iterator.next().getBasicRemote().sendText(buildJsonData(user, message));
			}
		} catch (IOException e) {
			System.out.println("IOException好吵");
		}
	}

	@OnClose
	public void handleClose(Session userSession) {
		System.out.println("WebSocket強關才叫我..");
		users.remove(userSession);
		try {
			userSession.close();
		} catch (IOException e) {
			System.out.println("IOException好吵");
		}
	}

	private String buildJsonData(String username, String message) {
		// 範例： {"message":"系統訊息:連線成功!"}
		return "{\"message\":\"" + username + ":" + message + "\"}";
	}
}