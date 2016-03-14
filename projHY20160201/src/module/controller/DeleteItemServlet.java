package module.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._12_ItemDAO;
import module.dao._12_Item_InterfaceDAO;
import module.model._12_ItemVO;
import module.service.DeleteItemService;
import module.service.GetAttributes;
import net.sf.json.JSONObject;

@WebServlet("/SelectItemServlet.delete")
public class DeleteItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		Integer itemNo =Integer.parseInt(req.getParameter("itemId").trim());
//		System.out.println(itemNo);
		_12_Item_InterfaceDAO _12DAO=new _12_ItemDAO();
		_12_ItemVO bean12 = _12DAO.findById(itemNo);
		DeleteItemService deleteItemService = new DeleteItemService();  
		deleteItemService.deleteItem(bean12);
		System.out.println("刪除成功"+itemNo);
		
//		GetAttributes att=new GetAttributes();
//		 JSONObject json=JSONObject.fromObject(att.find3nds(itemNo));
//		 System.out.println(json);
	
//		 resp.getWriter().print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}