package com.feifei.util;

import javax.servlet.http.HttpServletRequest;

public class SysUtils {
	public static ThreadLocal<HttpServletRequest>reqLocal=new ThreadLocal<HttpServletRequest>();
	 
	

}
