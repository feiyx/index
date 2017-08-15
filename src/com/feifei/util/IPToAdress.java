package com.feifei.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IPToAdress {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String strIP = "211.88.72.153";
			URL url = new URL("http://ip.qq.com/cgi-bin/searchip?searchip1="
					+ strIP);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "GBK"));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			strIP = result.substring(result.indexOf("该IP所在地为："));
			strIP = strIP.substring(strIP.indexOf("：") + 1);
			String province = strIP.substring(6, strIP.indexOf("省"));
			String city = strIP.substring(strIP.indexOf("省") + 1,
					strIP.indexOf("市"));
			
			System.out.println(strIP+"   "+province+"   "+city);
		} catch (IOException e) {
			
		}

	}
}
