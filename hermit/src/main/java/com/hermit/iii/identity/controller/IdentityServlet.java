package com.hermit.iii.identity.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.hermit.iii.login.model.MemLoginService;
import com.hermit.iii.member.model.MemberVO;

public class IdentityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**** Google ****/
	private final String G_CLIENT_ID = "538877171960-djc145ihldt91ec28hajlt5m66sis16g.apps.googleusercontent.com";
	private final String G_CLIENT_SECRET = "tXgMa9kOdHwrM02EgEeOa1Zs";
	private final String G_REDIRECT_URL = "http://localhost:8081/hermit/identity.do?action=google_process_Action";
	/**** Facebook ****/
	private final String F_CLIENT_ID = "1719931494697481";
	private final String F_CLIENT_SECRET = "e510e1f84173c944fbc3254a94739510";
	private final String F_REDIRECT_URL = "http://localhost:8081/hermit/identity.do?action=facebook_process_Action";

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
				System.out.println("access_token HTTP_OK!!"); // access_token
																// HTTP_OK!!
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

		// 驗證Google登入
		if ("google_login_Action".equals(action)) {

			// 準備存放3rd party訊息的Map物件
			Map<String, String> bearerMsgMap = new HashMap<String, String>();
			// 將bearerMsgMap放入request物件內，識別字串為 "BearerMsgKey"
			session.setAttribute("BearerMsgKey", bearerMsgMap);

			String token = null;
			int expires = 0;
			String type = null;
			// Google取得access_token的URL
			URL urlObtainToken = new URL("https://accounts.google.com/o/oauth2/token");
			HttpURLConnection connectionObtainToken = (HttpURLConnection) urlObtainToken.openConnection();

			// 設定此connection使用POST
			connectionObtainToken.setRequestMethod("POST");
			connectionObtainToken.setDoOutput(true);

			// 開始傳送參數
			OutputStreamWriter writer = new OutputStreamWriter(connectionObtainToken.getOutputStream());
			writer.write("code=" + request.getParameter("code") + "&"); // 取得Google回傳的參數code
			writer.write("client_id=" + G_CLIENT_ID + "&"); // 自己的client_id
			writer.write("client_secret=" + G_CLIENT_SECRET + "&"); // 自己的client_serect
			writer.write("redirect_uri=" + G_REDIRECT_URL + "&"); // 自己的redirect_uri
			writer.write("grant_type=authorization_code");
			writer.close();

			// 認證成功
			if (connectionObtainToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// 取得Google回傳的JSON格式資料
				BufferedReader br = new BufferedReader(
						new InputStreamReader(connectionObtainToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(br);
				JSONObject jsonObject;

				try {
					jsonObject = (JSONObject) jsonTokener.nextValue();
					token = jsonObject.getString("access_token");
					expires = jsonObject.getInt("expires_in");
					type = jsonObject.getString("token_type");
				} catch (JSONException e) {
					e.printStackTrace();
				}

				System.out.println(token); // (token)
				System.out.println(expires); // 3600
				System.out.println(type); // Bearer
			} else {
				response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
				return;
			}

			URL urlUserInfo = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token);
			HttpURLConnection connAccessToken = (HttpURLConnection) urlUserInfo.openConnection();
			// 登入者驗證成功
			if (connAccessToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br2 = new BufferedReader(
						new InputStreamReader(connAccessToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(br2);
				JSONObject jsonObject;

				try {

					jsonObject = (JSONObject) jsonTokener.nextValue();
					// 姓名
					System.out.println(jsonObject.getString("name"));
					bearerMsgMap.put("memName", jsonObject.getString("name"));
					// ID
					System.out.println(jsonObject.getString("id"));
					bearerMsgMap.put("memAccount", jsonObject.getString("id"));
					bearerMsgMap.put("Identity", "google");

					// 用MemberService去驗證取得的資料是否已註冊
					MemLoginService ls = new MemLoginService();
					MemberVO vo = ls.OtherCheck(jsonObject.getString("id"), jsonObject.getString("name"));

					if (vo != null) {
						response.sendRedirect("/hermit/MemberLogin/LoginSuccess.jsp");
						return;
					} else {
						response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
						return;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
				return;
			}
			response.sendRedirect("/hermit/MemberLogin/Login.jsp");
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
				System.out.println("access_token HTTP_OK!!"); // access_token
																// HTTP_OK!!
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

		// 驗證Facebook登入
		if ("facebook_login_Action".equals(action)) {
			// 準備存放3rd party訊息的Map物件
			Map<String, String> bearerMsgMap = new HashMap<String, String>();
			// 將bearerMsgMap放入request物件內，識別字串為 "BearerMsgKey"
			session.setAttribute("BearerMsgKey", bearerMsgMap);

			String token = null;
			int expires = 0;
			String type = null;
			String email = null;

			URL urlObtainToken = new URL("https://graph.facebook.com/v2.10/oauth/access_token");
			HttpURLConnection connectionObtainToken = (HttpURLConnection) urlObtainToken.openConnection();

			connectionObtainToken.setRequestMethod("GET");
			connectionObtainToken.setDoOutput(true);

			OutputStreamWriter writer = new OutputStreamWriter(connectionObtainToken.getOutputStream());

			writer.write("code=" + request.getParameter("code") + "&");
			writer.write("client_id=" + F_CLIENT_ID + "&");
			writer.write("client_secret=" + F_CLIENT_SECRET + "&");
			writer.write("redirect_uri=" + F_REDIRECT_URL);
			writer.close();
			System.out.println(connectionObtainToken.getResponseCode());

			// 認證成功
			if (connectionObtainToken.getResponseCode() == HttpURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(
						new InputStreamReader(connectionObtainToken.getInputStream(), "UTF-8"));
				JSONTokener jsonTokener = new JSONTokener(br);
				JSONObject jsonObject;

				try {
					jsonObject = (JSONObject) jsonTokener.nextValue();
					token = jsonObject.getString("access_token");
					expires = jsonObject.getInt("expires_in");
					type = jsonObject.getString("token_type");
				} catch (JSONException e) {
					e.printStackTrace();
				}

				System.out.println(token); // (token)
				System.out.println(expires); // 5183999
				System.out.println(type); // bearer
			} else {
				response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
				return;
			}

			URL urlUserInfo = new URL(
					"https://graph.facebook.com/me?fields=id,name,picture.height(400),email&access_token=" + token);
			HttpURLConnection connAccessToken = (HttpURLConnection) urlUserInfo.openConnection();

			// 登入者驗證成功
			if (connAccessToken.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br2 = new BufferedReader(
						new InputStreamReader(connAccessToken.getInputStream(), "UTF-8"));

				JSONTokener jsonTokener = new JSONTokener(br2);
				JSONObject jsonObject;

				try {
					jsonObject = (JSONObject) jsonTokener.nextValue();
					System.out.println(jsonObject.toString());
					// Email
					email = jsonObject.getString("email");
					bearerMsgMap.put("memEmail", email);
					// 姓名
					System.out.println(jsonObject.getString("name"));
					bearerMsgMap.put("memName", jsonObject.getString("name"));
					// id
					System.out.println(jsonObject.getString("id"));
					bearerMsgMap.put("memAccount", jsonObject.getString("id"));

					bearerMsgMap.put("Identity", "facebook");

					// 用MemberService去驗證取得的資料是否已註冊
					MemLoginService ls = new MemLoginService();
					MemberVO vo = ls.OtherCheck(jsonObject.getString("id"), jsonObject.getString("name"));

					if (vo != null) {
						response.sendRedirect("/hermit/MemberLogin/LoginSuccess.jsp");
						return;
					} else {
						response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
						return;
					}
				} catch (JSONException e) {
					System.out.println("No Email");
				}
			} else {
				response.sendRedirect("/hermit/MemberLogin/LoginError.jsp");
				return;
			}
			response.sendRedirect("/hermit/MemberLogin/Login.jsp");
		}
	}
}