<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://apis.google.com/js/api:client.js"></script>
<script>	
	var auth2;// The Sign-In object.
// 	Initializes Signin v2 and sets up listeners.
	var onGoogleLoad=function(){
		gapi.load('auth2',function(){//啟動gapi
			auth2=gapi.auth2.init({
				client_id: '695260937486-cc1ssubhsjtetadl71vqaf2mhsekmqsj.apps.googleusercontent.com',
				scope: 'profile',
				cookiepolicy: 'single_host_origin'
			})
			auth2.currentUser.listen(signOut);
		})		
	}	
	var signOut=function() {//使用者登出google
	    var xxx = gapi.auth2.getAuthInstance();
	    xxx.signOut().then(function () {
	      console.log('User signed out.');
	      location.href ='index.jsp';
	    });
	}
</script>
</head>
<body>
<%//將session移除，使用者登出我們的網站
session.invalidate();
%>
<script>onGoogleLoad();</script>
</body>
</html>