package com.feifei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.feifei.util.AddressUtils;
import com.feifei.util.GetPlaceByIp;

public class IPController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String ip=request.getRemoteAddr();
		//String ip="211.88.73.236";
		
		GetPlaceByIp adds = new GetPlaceByIp();
		JSONObject json = null;
		try {
			json = adds.readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+ip);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String address="中国";
		try {
			address = (String) ((JSONObject) json.get("content")).get("address");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		System.out.println(address);
		//System.out.println("address="+address);
		PrintWriter out=response.getWriter();
		out.print(address);
	}
}
