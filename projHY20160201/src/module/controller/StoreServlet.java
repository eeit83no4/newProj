package module.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.service.StoreService;
import net.sf.json.JSONObject;

@WebServlet("/StoreServlet.select")
public class StoreServlet extends HttpServlet {
	private StoreService storeService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			resp.setContentType("application/json; charset=utf-8");
			StoreService dao = new StoreService();
			//轉換資料//接收資料jsp傳來的值
			String keyword = req.getParameter("keyword"); 
			String mystoreneme = req.getParameter("mystoreneme");
			HttpSession servletSession=req.getSession();
			_04_EmployeeVO xx = (_04_EmployeeVO)servletSession.getAttribute("LoginOK");
			//須抓取使用者登入的id 僅test用
			String user_id=null;
			String user_name=null;
			try {
				user_id = String.valueOf(xx.getUser_id());
//				user_name = String.valueOf(xx.getC_name());
			} catch (Exception e) {				
				System.out.println("StoreServlet轉型錯誤:"+e.toString());
			}			
/*---------------------------------------------------------------------------------------------------------------------------*/
			//驗證資料//判斷jsp傳來的值搜尋店家
			if (keyword.trim().length() == 0 && mystoreneme.trim().length() == 0) {
				//呼叫model 呼叫StoreService執行DAO的方法
				JSONObject jo= new JSONObject(); //newJSON物件
				//把list資料放進JSON物件 例:{list:[店家1,店家2,店家3],mylists:[店家2,店家3]}
				List<_07_StoreVO> lists = dao.getStoreName(keyword);
				List<_07_StoreVO> mylists = dao.getAllMystoreName(user_id);
//				for (_07_StoreVO a:mylists){
//					a.getStore_no();
//					a.getStore_name();
//				jo.put(a.getStore_no(), a.getStore_name());	
//				}
				//根據model執行結果顯示view
				jo.put("list", lists.toArray());//json 物件 {key:[value1,value2]}
				jo.put("mylists", mylists.toArray());			
				PrintWriter out = resp.getWriter();
				out.print(jo.toString());
			} 
			else if(keyword.trim().length() != 0 && mystoreneme.trim().length() == 0){
				JSONObject jo= new JSONObject();
				List<_07_StoreVO> lists = dao.getStoreName(keyword);
				List<_07_StoreVO> mylists = dao.getAllMystoreName(user_id);
				jo.put("list", lists.toArray());
				jo.put("mylists", mylists.toArray());			
				PrintWriter out = resp.getWriter();
				out.print(jo.toString());
			}
			else if(keyword.trim().length() == 0 && mystoreneme.trim().length() != 0){
				JSONObject jo= new JSONObject();
				List<_07_StoreVO> lists = dao.getStoreName(keyword);
				List<_07_StoreVO> mylists = dao.getMystoreName(user_id, mystoreneme);
				jo.put("list", lists.toArray());
				jo.put("mylists", mylists.toArray());			
				PrintWriter out = resp.getWriter();
				out.print(jo.toString());
			}
			else{
				JSONObject jo= new JSONObject();
				List<_07_StoreVO> lists = dao.getStoreName(keyword);
				List<_07_StoreVO> mylists = dao.getMystoreName(user_id, mystoreneme);
				jo.put("list", lists.toArray());
				jo.put("mylists", mylists.toArray());			
				PrintWriter out = resp.getWriter();
				out.print(jo.toString());
			}
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		} catch (Exception e) {
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
//			e.printStackTrace();
//		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
