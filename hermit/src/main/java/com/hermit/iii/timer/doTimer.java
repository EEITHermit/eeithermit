package com.hermit.iii.timer;

public class doTimer extends java.util.TimerTask{
	//需繼承TimerTask，並將要執行程式寫於run()
	@Override
	public void run() {
		System.out.println("do it");
	}

}
