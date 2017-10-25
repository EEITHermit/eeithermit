package com.hermit.iii.login.model;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.hermit.iii.member.model.MemberVO;

public class EmailUtils {

	private static final String FROM = "eeithermit@gmail.com";

	// 發送重設密碼連結的郵件
	public static void sendResetPasswordEmail(MemberVO vo) {

		Session session = getSession();
		MimeMessage message = new MimeMessage(session);

		try {
			message.setSubject("找回您的密碼");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO, new InternetAddress(vo.getMemEmail()));
			message.setContent("要重新設定您的密碼，請點選下面的連結:<br/><a href='" + GeneratePwdLinkUtils.generateResetPwdLink(vo)
					+ "'>點擊重新設置新密碼</a>", "text/html;charset=UTF-8");
			// 發送email
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 發送會員帳號資訊連結
	public static void sendAccountInformationEmail(MemberVO vo) {

		Session session = getSession();
		MimeMessage message = new MimeMessage(session);

		try {
			message.setSubject("找回您的帳號資訊");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO, new InternetAddress(vo.getMemEmail()));
			message.setContent("此為您的帳號資訊:<br/>" + vo.getMemAccount(), "text/html;charset=UTF-8");
			// 發送email
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(props, new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, "eeit9704");
			}
		});
		return session;
	}
}
