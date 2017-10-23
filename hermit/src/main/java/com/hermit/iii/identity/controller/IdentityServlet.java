package com.hermit.iii.identity.controller;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;

public class IdentityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**** Google ****/
	private final String G_CLIENT_ID = "538877171960-djc145ihldt91ec28hajlt5m66sis16g.apps.googleusercontent.com";
	private final String G_CLIENT_SECRET = "tXgMa9kOdHwrM02EgEeOa1Zs";
	private final String G_REDIRECT_URL = "http://localhost:8081/Hermit/identity.do?action=google_process_Action";
	/**** Facebook ****/
	private final String F_CLIENT_ID = "1719931494697481";
	private final String F_CLIENT_SECRET = "e510e1f84173c944fbc3254a94739510";
	private final String F_REDIRECT_URL = "http://localhost:8081/Hermit/identity.do?action=facebook_process_Action";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// session物件來存放共用資料。
		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		if ("google_process_Action".equals(action)) {
			// 準備存放3rd party訊息的Map物件
			Map<String, String> bearerMsgMap = new HashMap<String, String>();
			// 將bearerMsgMap放入request物件內，識別字串為 "BearerMsgKey"
			session.setAttribute("BearerMsgKey", bearerMsgMap);

			String token = null;
			int expires = 0;
			String type = null;

			URL urlToken = new URL("https://accounts.google.com/o/oauth2/token");
			HttpURLConnection connToken = (HttpURLConnection) urlToken.openConnection();

			connToken.setRequestMethod("POST");
			connToken.setDoOutput(true); // allow data transmit(default:false)

			OutputStreamWriter out = new OutputStreamWriter(connToken.getOutputStream());

			/*
			 * Sample Example:
			 * 
			 * POST /oauth2/v4/token HTTP/1.1 Host: www.googleapis.com
			 * Content-Type: application/x-www-form-urlencoded
			 * 
			 * code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7& client_id=your_client_id&
			 * client_secret=your_client_secret&
			 * redirect_uri=https://oauth2.example.com/code&
			 * grant_type=authorization_code
			 * 
			 */

			out.write("code=" + request.getParameter("code") + "&");
			out.write("client_id=" + G_CLIENT_ID + "&");
			out.write("client_secret=" + G_CLIENT_SECRET + "&");
			out.write("redirect_uri=" + G_REDIRECT_URL + "&");
			out.write("grant_type=authorization_code");
			out.close();

			System.out.println(connToken.getResponseCode()); // 200

			if (connToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("token HTTP_OK!!"); // token HTTP_OK!!
				BufferedReader reader = new BufferedReader(new InputStreamReader(connToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(reader);
				JSONObject jsonObject;

				jsonObject = (JSONObject) jsonTokener.nextValue();
				token = jsonObject.getString("access_token");
				expires = jsonObject.getInt("expires_in");
				type = jsonObject.getString("token_type");

				System.out.println(token); // (token)
				System.out.println(expires); // 3600
				System.out.println(type); // Bearer
			} else {
				response.sendRedirect("register/register_error_page.jsp");
				return;
			}

			URL urlAccessToken = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token);
			HttpURLConnection connAccessToken = (HttpURLConnection) urlAccessToken.openConnection();

			System.out.println(connAccessToken.getResponseCode()); // 200

			if (connAccessToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("access_token HTTP_OK!!"); // access_token HTTP_OK!!
				BufferedReader reader2 = new BufferedReader(
						new InputStreamReader(connAccessToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(reader2);
				JSONObject jsonObject;

				jsonObject = (JSONObject) jsonTokener.nextValue();

				System.out.println(jsonObject.getString("email")); // (email)
				bearerMsgMap.put("memEmail", jsonObject.getString("email"));
				System.out.println(jsonObject.getString("name")); // (姓名)
				bearerMsgMap.put("memName", jsonObject.getString("name"));
				System.out.println(jsonObject.getString("id")); // (id)
				bearerMsgMap.put("memAccount", jsonObject.getString("id"));
				bearerMsgMap.put("Identity", "google");
			} else {
				response.sendRedirect("register/register_error_page.jsp");
				return;
			}
			response.sendRedirect("register/register_easy_page.jsp");
		}

		if ("facebook_process_Action".equals(action)) {
			// 準備存放3rd party訊息的Map物件
			Map<String, String> bearerMsgMap = new HashMap<String, String>();
			// 將bearerMsgMap放入request物件內，識別字串為 "BearerMsgKey"
			session.setAttribute("BearerMsgKey", bearerMsgMap);

			String token = null;
			int expires = 0;
			String type = null;
			String email = null;
			
			URL urlToken = new URL("https://graph.facebook.com/v2.10/oauth/access_token");
			HttpURLConnection connToken = (HttpURLConnection) urlToken.openConnection();

			connToken.setRequestMethod("GET");
			connToken.setDoOutput(true); // allow data transmit(default:false)

			OutputStreamWriter out = new OutputStreamWriter(connToken.getOutputStream());

			out.write("code=" + request.getParameter("code") + "&");
			out.write("client_id=" + F_CLIENT_ID + "&");
			out.write("client_secret=" + F_CLIENT_SECRET + "&");
			out.write("redirect_uri=" + F_REDIRECT_URL);
			out.close();

			System.out.println(connToken.getResponseCode()); // 200

			if (connToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("token HTTP_OK!!"); // token HTTP_OK!!
				BufferedReader reader = new BufferedReader(new InputStreamReader(connToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(reader);
				JSONObject jsonObject;

				jsonObject = (JSONObject) jsonTokener.nextValue();
				token = jsonObject.getString("access_token");
				expires = jsonObject.getInt("expires_in");
				type = jsonObject.getString("token_type");

				System.out.println(token); // (token)
				System.out.println(expires); // 5183999
				System.out.println(type); // bearer
			} else {
				response.sendRedirect("register/register_error_page.jsp");
				return;
			}

			URL urlAccessToken = new URL(
					"https://graph.facebook.com/me?fields=id,name,picture.height(400),email&access_token=" + token);
			HttpURLConnection connAccessToken = (HttpURLConnection) urlAccessToken.openConnection();

			System.out.println(connAccessToken.getResponseCode()); // 200

			if (connAccessToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				System.out.println("access_token HTTP_OK!!"); // access_token HTTP_OK!!
				BufferedReader reader2 = new BufferedReader(
						new InputStreamReader(connAccessToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(reader2);
				JSONObject jsonObject;

				jsonObject = (JSONObject) jsonTokener.nextValue();

				System.out.println(jsonObject.toString()); // {"name":"(姓名)","id":"(id)","picture":{...}}
				try {
					email = jsonObject.getString("email"); // 沒傳
				} catch (JSONException e) { // JSONException
					System.out.println("NO email"); // NO email
				}
				bearerMsgMap.put("memEmail", email);

				System.out.println(jsonObject.getString("name")); // (姓名)
				bearerMsgMap.put("memName", jsonObject.getString("name"));
				System.out.println(jsonObject.getString("id")); // (id)
				bearerMsgMap.put("memAccount", jsonObject.getString("id"));
				bearerMsgMap.put("Identity", "facebook");
			} else {
				response.sendRedirect("register/register_error_page.jsp");
				return;
			}
			response.sendRedirect("register/register_easy_page.jsp");
		}
	}
}