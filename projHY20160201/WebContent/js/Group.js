

			var userIds = [];//已經存過去的，比對用
			var realUser=null;//已經存過去的，傳值用
			//-------------加入團員
		  $("#inser").click(function() {
				var name = null;//已邀請區塊
				var name2 = null;//共同管理員

				$(':checkbox[name="inp"]:checked').each(function() {
					var userid = parseInt(($(this).val()).split('*')[0]);//員工編號
				    var username = ($(this).val()).split('*')[1];//員工姓名						
					userIds.push(userid);//儲存員工編號
					if(realUser==null){
						realUser=userid;
					}else{
						realUser=realUser+','+userid;
					}
					if (name == null) {
						name = "<div><input type='checkbox' name='userKKK' disabled='disabled' checked='checked' value='"+userid+'*'+username+"'/>"+ username+ "</div>";       
						name2 ="<label><input type='checkbox' name='adminDiv' value='"+userid+'*'+username+"'>"+ username+ "<br></label>";
					}else {	
						name = name + "<div><input type='checkbox' name='userKKK' disabled='disabled' checked='checked' value='"+userid+'*'+username+"'/>"+ username+ "</div>";
					    name2 = name2+ "<label><input type='checkbox' name='adminDiv' value='"+userid+'*'+username+"'>"+ username+"</label><br>";
					}
					console.log('empty')
					$(this).prop('disabled',true);					
				});
			
				console.log('=============');
				$("#inem2").append(name)//已邀請區塊
				$("#inadmin").append(name2)//共同管理員
			});

			//-------------取消
			$("#clear").click(function() {
				$("#inem2").empty();
				userIds = [];
				//document.getElementById("inser").value = "加入";
				$("#inem1").empty();
				$("#inadmin").empty();
				realUser=null;
				<c:forEach var="bean" items="${emdep}">					
					$("#inem1").append('<label><input type="checkbox" name="inp" value='+'${bean[0]}'+'*'+'${bean[1]}'+'>'+'${bean[1]}'+'</label><br>')
								
				</c:forEach>

			});
//-------------------------------------------------------------------------------------------------------	
			

	

//--------------------依部門分類------------------------------------------------------
			$("#select1").change(function() {
				var holdUser='1';//主揪				
				if($(this).val()=='所有部門'){
					$("#inem1").empty();					
					<c:forEach var="bean" items="${emdep}">
						var i=0;
						$.each(userIds,function(no,vo){
							//排除已邀請的
							if('${bean[0]}'==vo){
								console.log("vo="+vo);
								console.log("holdUser="+holdUser);
								i=1;
							}
						})
						//排除主揪自己
						if(i==0&&'${bean[0]}'!=holdUser){
							$("#inem1").append('<label><input type="checkbox" name="inp" value='+'${bean[0]}'+'*'+'${bean[1]}'+'>'+'${bean[1]}'+'</label><br>')
						}						
					</c:forEach>				
				}else{
					$(":selected").each(function() {				
						var depp= $(this).val();				
						console.log(depp);					
						$.ajax({
							"type":"POST",
							"url":'<c:url value="/SetGroup/showDepEmp.controller"/>',
							"data":{"dep_id":depp},		
							"success":function(data){
								console.log('*************'+data);		
								$("#inem1").empty();
								var i=0;
								$.each(data,function(index,value){//(1,2,3,4,5)									
									$.each(userIds,function(no,vo){//[1,2]
										//排除已邀請的
										if(value[0]==vo){
											i=1;
										}
									})
									//排除主揪自己
									if(i==0&&value[0]!=holdUser){
										$("#inem1").append('<label><input type="checkbox" name="inp" value='+value[0]+'*'+value[1]+'>'+value[1]+'</label><br>')										
									}
									i=0;
								})
							}					
						});
					});					
				}				
			});
			
 							

			//---------------部門分類結束-----------------------------------------------------
			
			
			//---------------------共同管理員----------------------------------------
			var adminIds = null;
			$('#setadmin').click(function() {
				 adminIds = null;
				$(':checkbox[name="adminDiv"]:checked').each(function() {
					var adminid = parseInt(($(this).val()).split('*')[0]);//員工編號
					if(adminIds==null){
						adminIds=adminid;
					}else{
						adminIds=adminIds+','+adminid;
					}
				})
				console.log("ad--"+adminIds);
			});
			
			
			var arr=[];
			
			$('#save').click(function() {
				var enddate = $("#enddate").val();;
				var groupna=$("#Tex1").val();
				var ann=$("#Tex2").val();
				arr.push({'store_name':'${sname}' ,'admin_id':adminIds,'user_Ids':realUser,'groupna':groupna,'ann':ann,'enddate':enddate});
				if(!$.isEmptyObject(arr)){
					var jsonString=JSON.stringify(arr);
					console.log(jsonString);
					$.ajax({
						"type":"post",
						"url":'<c:url value="/insertGroupServlet.controller"/>',
						"data":{jsonString},											
						"success":function(){
							alert("55555555");
						}
					});	
				
				};
			
			});
			
			
//-----------------------------------吉米--------------------------------- 

$(function(){		
	var i=1;
	var x=1;
	<c:forEach var="itemno" items='${itemnos}'>
		$("#f1").append('<dl><input type="checkbox" name="checkbox" value="'+${itemno}+'"/><span>${itemnames[itemno]}<span/><span id="it'+i+'"></span><dl/>');
		$("#it"+i).append('<dt><span>SIZE<span/><dd id="size'+i+'">');
		<c:forEach var="sizepriceList" items='${sizeprices[itemno]}'>
			$("#size"+i).append('<span>${sizepriceList.key}<span/><span class="text">${sizepriceList.value}<span/><br>');
		</c:forEach>
		<c:forEach var="class2List" items='${class2Lists}'>
			<c:forEach var="class2" items='${class2List[itemno]}'>
				$('#it'+i).append('<dt><span>${class2}<span/><dd id="c2'+x+'">');
				<c:forEach var="class3List" items='${class3Lists}'>
					<c:forEach var="class3" items='${class3List[itemno][class2]}'>
					$('#c2'+x).append('<span>${class3}<span/>');
					</c:forEach>
				</c:forEach>
			x++;
			</c:forEach>
		</c:forEach>
		i++;
	</c:forEach>
})


$('#save').click(function(){
	var xml = new XMLHttpRequest();
	var itemno=[];
	$(":input:checkbox:checked").each(function(){
			console.log($(this).val());
		itemno.push($(this).val());
		console.log(itemno);
	})
	
	xml.onreadystatechange = function(){
		if (xml.readyState == 4) {
			if (xml.status == 200){
				
			}
		}
	}

		
		xml.open("get", "/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+${store_no}, true);//傳值給StoreServlet
		xml.send();	
})
			
		
