package module.controller.userOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.dao._16_Group_RecordDAO;
import module.service.attempGroupService;

public class showItemsAction extends ActionSupport implements RequestAware {
	
	private static final long serialVersionUID = 1L;
	private attempGroupService att=new attempGroupService();
	private String groupno;
	
	public String getGroupno() {
		return groupno;
	}
	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
		
	}


	@Override
	public String execute() throws Exception {
	
		
		
		int group_user_id=1;
		int group_no=Integer.parseInt(groupno.trim());//團購編號
		
		Map<String,String> notice=new HashMap<>();
		//---------------------找出group_user_no
		Integer group_user_no=att.getGroupUserNo(group_no, group_user_id);
		request.put("group_user_no", group_user_no);
		//-----------------找出該團購目前的訂購人數------------------
		Integer users=att.findUserByGroup(group_no);
		String usersString=String.valueOf(users);
		notice.put("usersString", usersString);
				
		//------------------找出該團購目前的累積金額------------------
		Double amount=att.findAmountByGroup(group_no);
		String amountString=(String.valueOf(amount)).split("\\.")[0];
		notice.put("amountString", amountString);
				
		//------------------找出該團購的公告事項------------------
		String ann=att.findAnnByGroup(group_no);
		notice.put("ann", ann);
		//團購的店家名稱
		_16_Group_RecordDAO _16dao=new _16_Group_RecordDAO();
		String storename=_16dao.findById(group_no).getStoreVO().getStore_name();
		notice.put("store", storename);
		//----------------找出該商品的照片本機路徑------------------------
		Map<Integer, String> picmap=att.findItemPicByGroup(group_no);
		request.put("itempic", picmap);
		//------------找出物品的第二層屬性-------------------
		List<Map<Integer, Set<String>>> class2List=new ArrayList<>();
		Map<Integer, Set<String>> class2=att.find2nds(group_no);
		class2List.add(class2);
		request.put("class2Lists", class2List);
		//----------------找出該團購的商品------------------------
		List<Map<Integer,String>> itemnames=att.findItemsByGroup(group_no);
		request.put("itemnames", itemnames);
		//--------------------------------------------------
//		Map<String, Set<Integer>> priceNo=att.findPricenoByGroup(group_no);
//		request.put("priceNo", priceNo);
		//----------------找出該團購的商品(純編號)------------------------
		Set<Integer> itemnos=att.findItemsNoByGroup(group_no);
		request.put("itemnos", itemnos);
		//------------找出該物品的第三層屬性-------------------
		Map<Integer, Map<String, Set<String>>> class3=att.find3nds(group_no);		
		List<Map<Integer, Map<String, Set<String>>>> class3List=new ArrayList<>();		
		class3List.add(class3);
		request.put("class3Lists", class3List);
		//---------------找出該團購的所有商品size,price--------------------
		Map<Integer,Set<String>> sizeprice=att.findSizePricesbyGroup(group_no);
		List<Map<Integer,Set<String>>> sizepriceList=new ArrayList<>();
		sizepriceList.add(sizeprice);
		request.put("sizepriceLists", sizepriceList);
		
		
				
		
		
		request.put("notice", notice);
		

		
		
		
		return Action.SUCCESS;
	}
	
	
	
	

}
