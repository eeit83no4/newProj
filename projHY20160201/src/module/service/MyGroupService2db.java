package module.service;

import module.dao._16_Group_RecordDAO;
import module.model._16_Group_RecordVO;
import module.util.HibernateUtil;

public class MyGroupService2db {
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
				MyGroupService2db mg = new MyGroupService2db();
				
//				mg.updateGroupStatus_success(2);
//				mg.updateGroupEndDate(4);
//				mg.updateForFail(3, "ssssss");
		
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
		System.out.println("failure"+failure);
		vo.setFailure(failure);
		grdao.update(vo);
	}

}
