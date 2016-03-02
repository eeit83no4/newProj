<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="google-signin-client_id" content="695260937486-cc1ssubhsjtetadl71vqaf2mhsekmqsj.apps.googleusercontent.com">
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<script>
	  function onSuccess(googleUser) {//登入google成功之後執行的動作
	    $.getJSON("/projHY20160201/login/checkEmailAction.action",function(data){		  
	    	 var i=0;
	         var user;
	    	 $.each(data,function(idx,bean){				  
				  if(googleUser.getBasicProfile().getEmail()==bean){//比對使用者的email使否存在我們的資料庫	
						i=1;
				  		user=bean;
				  }		  
			 });
	    	 if(i==1){//有存在就導入下一個頁面
	    		 var email=user;
	    		 $.ajax({
						"method":"GET",
						"url":'<c:url value="/login/loginAction.action"/>',
						"data":{email},											
						"success":function(){
							location.href='/projHY20160201/welcome.jsp'
						}
					});	
// 				 location.href='/projHY20160201/login/loginAction.action?email='+user ;
	    	 }else if(i==0){//不存在就強制登出	    		 
	    		 var auth2 = gapi.auth2.getAuthInstance();
	    		 auth2.disconnect();
	    		 alert('你不是本公司的員工');	    		
	    		 return false;
	    	 }
		});
	  }
	  function onFailure(error) {//登入google失敗之後執行的動作
	    console.log(error);
	  }
	  function renderButton() {//設計登入按鈕的樣式
	    gapi.signin2.render('my-signin2', {
	      'scope': 'https://www.googleapis.com/auth/plus.login',
	      'width': 80,
	      'height': 20,
	      'longtitle': false,
	      'theme': 'dark',
	      'onsuccess': onSuccess,
	      'onfailure': onFailure
	    });
	  }
</script>


</head>
<body>
 <div id="my-signin2"></div><br/>
 <a href="welcome.jsp">welcome</a><br/>
 <a href="page3.jsp">page3</a><br/>
 <a href="StorePage/SelectStoreName.jsp">store</a><br/>
 <a href="MyGroup/index.jsp">MyGroupIndex</a><br/>



<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</body>
</html>