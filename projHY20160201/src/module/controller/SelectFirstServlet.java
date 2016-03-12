package module.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.GetAttributes;
import net.sf.json.JSONObject;

@WebServlet("/SelectFirstServlet.select")
public class SelectFirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		Integer itemNo =Integer.parseInt(req.getParameter("item_no").trim());
//		System.out.println(itemNo);
//		System.out.println("ccccccccccccccccccccccccccc");

		GetAttributes att=new GetAttributes();
		String first = att.get_09_ClassNO(itemNo);
		
		Map<String,String> data=new HashMap<>(); 
		data.put("first", first);
		JSONObject json=JSONObject.fromObject(data);
	
		 resp.getWriter().print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}