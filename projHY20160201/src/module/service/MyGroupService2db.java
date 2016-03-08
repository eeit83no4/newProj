package module.service;

import java.util.List;

import module.dao._04_EmployeeDAO;
import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;
import module.model._04_EmployeeVO;
import module.model._16_Group_RecordVO;
import module.model._18_Order_DetailVO;
import module.util.HibernateUtil;

public class MyGroupService2db {
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
				MyGroupService2db mg = new MyGroupService2db();
				
//				mg.updateGroupStatus_success(2);
//				mg.updateGroupEndDate(4);
//				mg.updateForFail(3, "ssssss");
//				mg.updatePayStatus(1,"y");
				mg.updatePayStatusByUser(1,1,"n");
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
	
	_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
//	_16_Group_RecordVO grvo = new _16_Group_RecordVO();
	
	public void updateGroupStatus_success(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setStatus("成功");
		
		grdao.update(a);
		
	}
	
	public void updateGroupStatus_failed(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setStatus("失敗");
		
		grdao.update(a);
		
	}
	
	public void updateGroupEndDate(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setEnd_date(new java.util.Date());
		
		grdao.update(a);
		
	}
	
	/*-------------修改團購狀態、失敗原因-----------------------------------------------------------------*/
	public void updateForFail(Integer group_no,String failure){
		_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
		_16_Group_RecordVO vo = grdao.findById(group_no);
		vo.setStatus("失敗");
		vo.setFailure(failure);
		grdao.update(vo);
	}
	
	/*-------------修改付款狀態(明細列表)-----------------------------------------------------------------*/
	public void updatePayStatus(Integer detail_no,String pay_status){
		_18_Order_DetailDAO oddao = new _18_Order_DetailDAO();
		_18_Order_DetailVO odvo = oddao.findById(detail_no);
		odvo.setPay_status(pay_status);
		oddao.update(odvo);		
	}
	
	/*-------------修改付款狀態(按人統計用)-----------------------------------------------------------------*/
	public void updatePayStatusByUser(Integer group_no,Integer user_id,String pay_status){		
		_17_Group_UserDAO gudao = new _17_Group_UserDAO();
		_18_Order_DetailDAO oddao = new _18_Order_DetailDAO();
		//先從group_user表抓出group_user_no
		Integer group_user_no = gudao.getGroupUserNo(group_no, user_id).get(0).getGroup_user_no(); 																		
		//再從order_detail表抓出detail_no	
		List<_18_Order_DetailVO> vos= oddao.getDetailNo(group_user_no);		
		for(_18_Order_DetailVO vo:vos){
			Integer detail_no = vo.getDetail_no();
			_18_Order_DetailVO odvo = oddao.findById(detail_no);
			odvo.setPay_status(pay_status);
			oddao.update(odvo);	
		}		
	}
	
	

}
