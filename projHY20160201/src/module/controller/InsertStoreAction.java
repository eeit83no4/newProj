package module.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.service.GetStoreClass;
import module.service._07_StoreService;

public class InsertStoreAction extends ActionSupport implements RequestAware {
	
	private String store;
	private String storeClass;
	private String phone;
	private String attributes;	
	private String item;
	private String address;	
	private String submit;	
	private String sub;	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}	
	public String getClass1() {
		return phone;
	}
	public void setClass1(String class1) {
		this.phone = class1;
	}
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;		
	}
	
	
	public void validate() {
//		_07_StoreVO bean7 = new _07_StoreVO();
//		System.out.println(store);
//		System.out.println(storeClass.replace(" ", ""));
//		System.out.println(phone);
//		System.out.println(address);
//		
//		Integer pk7 = null;
//		String class_name=storeClass.replace(" ", "");		
//		bean7.setStore_name(store);
//		bean7.setPhone(phone);	
//		bean7.setAddress(address);
//		bean7.setFinal_update(new java.util.Date());	
//		
//		_07_StoreService storeService = new _07_StoreService(); 
//		pk7 = storeService.insertStore(bean7, class_name);
//		System.out.println(pk7);
//		
//		InsertItemService insertItemService=new InsertItemService();
//		ArrayList getAtr = insertItemService.cuttingHtmlString(attributes);
//		
//		System.out.println(item);
//		System.out.println(attributes);
//		Integer pk12= null;
//		String size  = null;
//		for(int i=0;i<getAtr.size();i=i+2){			
//			if(getAtr.get(i).equals("SIZE")){
//				size =(String) getAtr.get(i+1);
//				i=i+2;				
//			}
////		System.out.println(getAtr.get(i));
////		System.out.println(getAtr.get(i+1));
//		
//		_09_Class_FirstVO bean9=new _09_Class_FirstVO();
//		bean9.setClass1_name("飲料"); 
//		//12物品
//		_07_StoreVO storeVO=new _07_StoreVO();
//		storeVO.setStore_no(pk7);	//參考店家
//		_12_ItemVO bean12=new _12_ItemVO();
//		bean12.setStoreVO(storeVO);
//		if(pk12 == null){
//		}else{
//			bean12.setItem_no(pk12);			
//		}
//		bean12.setItem_name(item);
////		bean12.setPic(null); //照片		
//		
//		//10第二層屬性
//		_10_Class_SecondVO bean10 = new _10_Class_SecondVO();
//		bean10.setClass2_name((String)getAtr.get(i));		
//		//bean13 第三層屬性
//		String thirdName=(String) getAtr.get(i+1);	
//		
////		pk12=insertItemService.insertSecond( bean9,bean12,bean10,thirdName);  //傳入四個參數
//		if(size != null){
//			InserSizeService inserSizeService=new InserSizeService();
////			inserSizeService.inserSize(size , pk12);
//			size  = null;
//			}
//		}
//		
		
	}
	
	
	@Override
	public String execute() throws Exception {
		_07_StoreVO bean=new _07_StoreVO();
		bean.setStore_name(store);
		bean.setPhone(phone);
		bean.setAddress(address);
		bean.setFinal_update(new java.util.Date());	
		//參考員工
		_04_EmployeeVO bean4=new _04_EmployeeVO();
		bean4.setUser_id(166);
		bean.setEmployeeVO(bean4);
		
		_07_StoreVO bean7 = new _07_StoreVO();;
		if(sub==null){
		_07_StoreService storeService=new _07_StoreService();
		Integer StoreNo = storeService.insertStore(bean, storeClass.replace(" ", ""));
//		System.out.println(StoreNo);
		request.put("StoreNo", StoreNo);		
		bean7.setStore_no(StoreNo);
			}else{
			request.put("StoreNo", sub);					
			bean7.setStore_no(Integer.parseInt(sub.trim()));
			}
			
		
		GetStoreClass getStoreClass = new GetStoreClass();  
//		List<_12_ItemVO> getItemAll = getStoreClass.get07IsItemAll(bean7);
//		System.out.println(getItemAll);
		
		 Object getItemAll = getStoreClass.get07IsItemAllJson(bean7);
//		System.out.println(getItemAll);
		
		request.put("getItemAll", getItemAll);
		return "success";
	}
	
}
