package com.hermit.iii.timer;

import java.sql.Timestamp;

import javax.annotation.PreDestroy;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet(value="/tomer",loadOnStartup=1)
public class Timer extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static java.util.Timer timer = null;
	
	static{
		timer = new java.util.Timer();
		doTimer dotime = new doTimer();
		//取得今天日期
		String a = (new Timestamp(System.currentTimeMillis())).toString();
		//設定為凌晨00:00
		Timestamp time = Timestamp.valueOf(a.substring(0,10)+" 00:43:00");
		//設定行程，一天執行一次dotime
		timer.scheduleAtFixedRate(dotime,time,1000*60*60*24);
	}
	//關掉伺服器後取消計時
	@PreDestroy
	public void end(){
		Timer.timer.cancel();
	}

}
