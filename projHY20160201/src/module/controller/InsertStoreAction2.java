package module.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._12_ItemVO;
import module.service.GetStoreClass;
import module.service.GetStoreDataService;
import module.service.UpdateStoreService;
import module.service._07_StoreService;

public class InsertStoreAction2 extends ActionSupport implements RequestAware {
	
	private String store;
	private String storeClass;
	private String phone;
	private String address;	
	private String sub;			
	private Map<String, String> data;	
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
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
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(store);
		System.out.println(storeClass);
		System.out.println(phone);
		System.out.println(address);
		System.out.println(sub);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
	}
	
	private GetStoreDataService getStoreDataService=new GetStoreDataService();
	@Override
	public String execute() throws Exception {
		//取得資料
		Integer stroeNo = Integer.parseInt(sub);
//		_07_StoreVO bean=new _07_StoreVO();
//		bean.setStore_no(stroeNo);
//		bean.setStore_name(store);
//		bean.setPhone(phone);
//		bean.setAddress(address);
//		bean.setFinal_update(new java.util.Date());
		
		
		UpdateStoreService updateStoreService = new UpdateStoreService();
		
		//刪除關聯
		_07_StoreVO bean7 = new _07_StoreVO();			
		bean7.setStore_no(stroeNo);
		updateStoreService.updateStore(bean7);
		//重新建立關聯
		//砍字串
		String getClass = updateStoreService.cuttingStoreString(storeClass);	
		//存資料
		bean7.setStore_name(store);
		bean7.setPhone(phone);
		bean7.setAddress(address);
		bean7.setFinal_update(new java.util.Date());
		//呼叫建立方法
		updateStoreService.inserStoreClass(bean7,getClass);
			 return "success";
	}
	
}
