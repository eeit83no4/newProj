package module.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.GetAttributes;
import net.sf.json.JSONObject;

@WebServlet("/SelectItemServlet.select")
public class SelectItemAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		Integer itemNo =Integer.parseInt(req.getParameter("item_no"));
		System.out.println(itemNo);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		GetAttributes att=new GetAttributes();

		 JSONObject json=JSONObject.fromObject(att.find3nds(itemNo));
		 System.out.println(json);
	
		 req.getRequestDispatcher("/InsertStore/upStore3.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}