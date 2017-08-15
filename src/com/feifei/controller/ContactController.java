package com.feifei.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.feifei.po.Contact;
import com.feifei.service.ContactService;
import com.feifei.util.GetPlaceByIp;

public class ContactController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String checkcode = request.getParameter("checkcode");
		HttpSession session = request.getSession();
		String imageCode = session.getAttribute("checkcode").toString();
		PrintWriter out = response.getWriter();
		if (checkcode == null || !checkcode.equalsIgnoreCase(imageCode)) {
			out.print("验证码输入有误");
			return;
		};
		String cEmail = request.getParameter("Email");
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(cEmail);
	    boolean flag = matcher.matches();
		if (!flag) {
			out.print("【"+cEmail+"】亲，要输入真实的邮箱哦");
			return;
		};
		
		String cNo = UUID.randomUUID().toString();
		String cName = request.getParameter("Name");
		String cSubject = request.getParameter("Subject");
		String cMessage = request.getParameter("Message");
		String cStatus = "0";
		Contact contact = new Contact();
		contact.setcNo(cNo.replaceAll("-",	""));
		contact.setcName(cName);
		contact.setcEmail(cEmail);
		contact.setcSubject(cSubject);
		contact.setcMessage(cMessage);
		contact.setcCurrentime(new Date());
		contact.setcStatus(cStatus);
		String ip=request.getRemoteAddr();
		contact.setcIP(ip);
		
		//获取访问者ip
		GetPlaceByIp adds = new GetPlaceByIp();
		JSONObject json = null;
		try {
			json = adds.readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+ip);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String address=json.toString();
		contact.setcAdress(address);
		
		ContactService service = new ContactService();
		int i = service.insertAll(contact);
		if (i == 1) {
			out.print("O(∩_∩)O 提交成功！"+ip);
		} else {
			out.print("o(︶︿︶)o 唉！失败了！");
		}
	}
	/**
	 * 测试
	 * @param args
	 * @throws IOException
	 * @throws JSONException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		/*String ip="211.88.74.6";
		GetPlaceByIp adds = new GetPlaceByIp();
		JSONObject json = null;
		try {
			json = adds.readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip="+ip);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String adress=(String) ((JSONObject) json.get("content")).get("address");
		System.out.println(adress);*/
		
		
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher("22@8.com");
	    boolean flag = matcher.matches();
	    System.out.println(flag);
	    
		
	}
}