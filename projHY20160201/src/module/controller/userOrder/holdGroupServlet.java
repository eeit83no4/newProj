package module.controller.userOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._07_StoreDAO;
import module.model._07_StoreVO;
import module.service.attempGroupService;
@WebServlet("/userOrder/holdGroupServlet.controller")
public class holdGroupServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private _07_StoreDAO _07dao=new _07_StoreDAO();
	private attempGroupService att=new attempGroupService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String sotreNo=null;
		try {
			sotreNo = request.getParameter("storeNo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sotreNo!=null){//如果從首頁找最新店家
			_07_StoreVO singleStore=_07dao.findById(Integer.parseInt(sotreNo.trim()));
			request.setAttribute("singleStore", singleStore);//從首頁來
		}
			List<_07_StoreVO> allStores=att.getAllStores();//不是從首頁
			request.setAttribute("allStores", allStores);//不是從首頁
		
			
				
		List<_07_StoreVO> allStoresTiemSorted=att.getAllStoresTiemSorted();
		Map<Integer, Set<String[]>> allSotreitems=att.getItemByAllStore();
		Map<Integer,String> allStoreClass=att.findStoreClass();
		
		request.setAttribute("allStoresTiemSorted", allStoresTiemSorted);
		request.setAttribute("allSotreitems", allSotreitems);
		request.setAttribute("allStoreClass", allStoreClass);
		request.getRequestDispatcher("/holdGroup/holdGroup.jsp").forward(request, response);

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
	
	
}
