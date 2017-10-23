package com.hermit.iii.util;

import java.io.*;
import java.net.*;

public class SendBySMS {
	private HttpURLConnection sms_gw = null;
	private final int codeLength = 6;
	private final int complexRange = 7;
	private String authcode = "";

	public SendBySMS() {
	}

	private void SendSMS(String mobile) { // 電話
		try {
			// 設定變數
			StringBuffer MSGData = new StringBuffer();

			// 設定參數
			String username = "gary553202"; // 帳號
			String password = "gary50230"; // 密碼
			String message = "您好! 你的hermit租屋網驗證碼：" + authcode; // 簡訊內容

			MSGData.append("username=" + username);
			MSGData.append("&password=" + password);
			MSGData.append("&mobile=" + mobile);
			MSGData.append("&message=");
			MSGData.append(UrlEncode(message.toString().getBytes("big5")));
			SendToGW(MSGData.toString());
		} catch (Exception e) {
			System.out.println("程式錯誤!");
		}
	}

	// 傳送至 TwSMS API server
	private boolean SendToGW(String post) {
		try {
			URL url = new URL("http://api.twsms.com/smsSend.php");
			sms_gw = (HttpURLConnection) url.openConnection();
			sms_gw.setDoInput(true);
			sms_gw.setDoOutput(true);
			sms_gw.setUseCaches(false);
			sms_gw.setRequestMethod("POST");

			DataOutputStream dos = new DataOutputStream(sms_gw.getOutputStream());
			dos.writeBytes(post);
			dos.flush();
			dos.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(sms_gw.getInputStream()));
			String strResponse = "";
			String readLine;
			while ((readLine = br.readLine()) != null) {
				strResponse += readLine;
			}
			br.close();
			System.out.println("回傳碼:" + strResponse);
			return true;
		} catch (Exception e) {
			System.out.println("無法連接 TwSMS API Server!");
			return false;
		}
	}

	// UrlEncode Function
	private String UrlEncode(byte[] src) {
		byte[] ASCIIMAP = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
		int pivot = 0;
		byte[] data = new byte[src.length * 3];
		for (int i = 0; i < src.length; i++) {
			if (src[i] == 0) {
				data[pivot++] = 37;
				data[pivot++] = 48;
				data[pivot++] = 48;
			}

			else if (src[i] < 0) {
				data[pivot++] = 37;
				data[pivot++] = ASCIIMAP[(src[i] >> 4) & 0x0f];
				data[pivot++] = ASCIIMAP[src[i] & 0x0f];
			} else {
				char cc = (char) src[i];

				if (Character.isLetterOrDigit(cc)) {
					data[pivot++] = src[i];
				} else if (cc == ' ') {
					data[pivot++] = 43;
				} else if (cc == '.' || cc == '-' || cc == '*' || cc == '_') {
					data[pivot++] = src[i];
				} else {
					data[pivot++] = 37;
					data[pivot++] = ASCIIMAP[(src[i] >> 4) & 0x0f];
					data[pivot++] = ASCIIMAP[src[i] & 0x0f];
				}
			}
		}
		if (pivot > 0)
			return new String(data, 0, pivot);
		return "";
	}

	private String AuthenticationEncode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codeLength; i++) {
			int z = (int) ((Math.random() * complexRange) % 3);

			if (z == 1) { // 放數字
				sb.append((char) ((Math.random() * 10) + (int) '0'));
			} else if (z == 2) { // 放大寫英文
				sb.append((char) (((Math.random() * 26) + (int) 'A')));
			} else {// 放小寫英文
				sb.append(((char) ((Math.random() * 26) + (int) 'a')));
			}
		}
		return sb.toString();
	}

	public String Process(String mobile) {
		authcode = AuthenticationEncode();
		System.out.println(authcode);
		SendSMS(mobile);
		return authcode;
	}

	public static void main(String[] args) {
		new SendBySMS().Process("0905553202");
	}
}