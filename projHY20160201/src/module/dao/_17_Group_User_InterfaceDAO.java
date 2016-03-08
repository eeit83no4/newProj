package module.dao;

import java.util.List;
import java.util.Map;

import module.model._17_Group_UserVO;
import net.sf.json.JSONObject;

public interface _17_Group_User_InterfaceDAO {
	
	public void insert(_17_Group_UserVO group_UserVO);
	public void update(_17_Group_UserVO group_UserVO);
	public void delete(Integer group_user_no);
	public _17_Group_UserVO findById(Integer group_user_no);
	public List<_17_Group_UserVO> getAll();
	public List<_17_Group_UserVO> findByGroupUserId(Integer group_user_id);
	public Long[] countamount(Integer group_user_id);
	JSONObject getUserIdNo(int group_no);
	public List<_17_Group_UserVO> findByGroupUserId2(Integer group_user_id, Integer group_no);
	public List<_17_Group_UserVO> getGroupUserNo(Integer group_no, Integer group_user_id);


}
