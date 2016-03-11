package module.controller.group;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;
import module.model._04_EmployeeVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.service.attempGroupService;

/**
 * Servlet implementation class updateAfterDeleteServlet
 */
@WebServlet("/module.controller.group/updateAfterDeleteServlet.delete")
public class updateAfterDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static _16_Group_RecordDAO _16grDAO=new _16_Group_RecordDAO();
	private static _17_Group_UserDAO _17guDAO=new _17_Group_UserDAO();
	private static _18_Order_DetailDAO _18detailDAO=new _18_Order_DetailDAO();   
    private attempGroupService att=new attempGroupService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		_04_EmployeeVO mb=(_04_EmployeeVO)session.getAttribute("LoginOK");
		int groupNo=Integer.parseInt(request.getParameter("groupNo"));//團購編號
		int groupUserNo=att.getGroupUserNo(groupNo, mb.getUser_id());
		_17_Group_UserVO user=_17guDAO.findById(groupUserNo);
		double originAmout=0.0;
		double amount=0;
		double amountAfter=0;		
		Set<_18_Order_DetailVO> userorder=user.getOrder_Details();		
		//--------------新增order_tail----------------
		for(_18_Order_DetailVO a:userorder){

			originAmout=originAmout+(a.getQuantity()*a.getOriginal_oprice());
			amount=amount+(a.getQuantity()*a.getOprice());
			amountAfter=amountAfter+(a.getQuantity()*a.getOprice_after());
		}
		//--------------更新GroupUser金額-----------------	
		System.out.println("user="+user);
		user.setOriginal_user_amount(originAmout);
		user.setUser_amount(amount);
		user.setUser_amount_after(amountAfter);
		user.setOrder_time(new java.util.Date());
		_17guDAO.update(user);
		//----------------更新GroupRecord金額-------------
		_16_Group_RecordDAO groupDao=new _16_Group_RecordDAO();
		_16_Group_RecordVO groupVO=groupDao.findById(groupNo);	
		Double user_amount=0.0;
		Double user_amount_after=0.0;
		for(_17_Group_UserVO a:groupVO.getGroup_Users()){
			user_amount=user_amount+a.getUser_amount();
			user_amount_after=user_amount_after+a.getUser_amount_after();
		}	
		groupVO.setGroup_amount(user_amount);
		groupVO.setGroup_amount_after(user_amount_after);		
		groupDao.update(groupVO);		
		
		//
		
		response.sendRedirect("/projHY20160201/MyGroup/group_detail.controller?xxx="+groupNo);
		


	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
