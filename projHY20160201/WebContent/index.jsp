<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>

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
							location.href='/projHY20160201/index/indexServlet.controller'
						}
					});	
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
	      'width': 250,
	      'height': 50,
	      'longtitle': true,
	      'theme': 'dark',
	      'onsuccess': onSuccess,
	      'onfailure': onFailure
	    });
	  }
</script>
<style>
</style>
</head>
<body style='background-color:#555'>

	<div style='text-align:center;width:420px;height:320px;margin:0 auto'>
		<div style='width:320px;margin-top:150px;margin-left:50px'>
			<div style='width:250px;margin-left:35px'>
				<div id="my-signin2"></div>
			</div>
		</div>
	</div>

<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</body>
</html>