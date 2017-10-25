package com.hermit.iii.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;

public class ConvertToBase64 {
	
	private Base64.Encoder encoder ;
	
	public ConvertToBase64(){
		encoder = Base64.getEncoder();
	}
	public String encode(Part part){
		String strBase64 = null;
		String type = part.getContentType();
		if(type == null){
		}else{
			try {
				InputStream in = part.getInputStream();
				byte[] b = new byte[(int)part.getSize()];
				in.read(b,0,(int)part.getSize());
				strBase64 = "data:"+ type +";base64,"+encoder.encodeToString(b);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strBase64;
	}
	
	public String encode(String MIMEType ,InputStream in){
		String strBase64 = null;
		if(MIMEType == null){
		}else{
			try {
				byte[] b = new byte[in.available()];
				in.read(b,0,in.available());
				strBase64 = "data:"+ MIMEType +";base64,"+encoder.encodeToString(b);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strBase64;
	}
	

}
