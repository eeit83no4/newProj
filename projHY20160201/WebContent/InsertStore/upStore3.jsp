<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>
<style>
#right{
	background-color: red;
	width:310px;
}
.form-group{
/* 	background-color: lime; */
/* 	width:350px; */
  float: left;  
}
</style>
</head>
<body class="home">
<div id="wrap">
	<jsp:include page="/header.jsp"/>
<%-- 	${getItemAll.綠茶} --%>
<%-- <c:set var="length" value="${fn:length(getItemAll)}"/> --%>
<%-- +++++${length} --%>
<%-- ${fn:length(getItemAll)} --%>

<%-- ${StoreNo} --%>
<%-- <c:forEach var="i" items="${getItemAll}"> --%>
<%-- 	${i.item_name}	 --%>
	
<%-- </c:forEach> --%>
	<form action="<c:url value='/insertStoreAction2.action' />" method="get" role="form" data-toggle="validator">
	<div class="container col-md-2">
  <ul class="nav nav-pills nav-stacked" id="item">
  </ul>
  <input type="button" value="+" id="but88" /><br />
</div>			
		<div style="float: left; border: 10px;margin: 100px" id="right">
			<span id="itemP" style="display:none">品名: </span> <input type="text" value="" name="item" id="itemName" style="display:none"/><br />
			<input type="button" id="bt0" value="+" style="display:none"/>
			<div id="99"><input type="button" id="but99" value="+" style="display:none"/></div>
		</div>
		
		<div id="dialog" title="Basic dialog">
  		<input type="text" value="" id="AAB"/><br/>
  		<input type="radio" name="first" value="飲料" id="radio" checked="checked" />飲料
  		<input type="radio" name="first" value="便當" />便當
  		<input type="radio" name="first" value="甜點" />甜點
  		<input type="radio" name="first" value="其他" />其他
</div>
	<div>
	<div class="form-group">
		<br />	
		<label for="inputName" class="control-label">店家名稱</label>
		<input type="text" value="草莓店" id="inputName" name="store" placeholder="店家名稱"  class="form-control" />
		<label for="inputName" class="control-label">電話</label>
		<input type="text" value="093435737" id="phone" name="phone" placeholder="電話" class="form-control" />	
		<label for="inputName" class="control-label">地址</label>
		<input type="text" value="大安區" id="phone" name="address" placeholder="地址" class="form-control" />
		<label for="inputName" class="control-label">類型</label>		
 		<div id="showBlock" ></div> 					
		<input type="submit" name="submit" value="送出" id="id">		
		</div>
		</div>
	</form>
	<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script>

	var txtId = 1;
	
		$(function(){	
			ii=0;
			jsonData = null;
			itemId = null;
			first55 = null;		
			storeNo = null;
			
			//點擊彈出視窗
		    $( "#but88" ).click(function() {						
		    	$( "#dialog" ).dialog( "open" );			      
		    });
			//長出店家類型 名稱
			$('#inputName').val('${data.storeName}');	 
			    var b="${data.storeClass}";
			    var c=b.split(",");			    
			    var d="${data.storeClassAll}";
			    var e=d.split(",");
			    for (var i = 0; i < e.length; i++) {
				    $("#showBlock").append('<input type="checkbox" id="div' + txtId + '" name="storeClass"  value="'+e[i]+'" />'+e[i]+'');
				    txtId++;
				    }
				for(var i = 0; i < c.length; i++){
					for (var y = 0; y < e.length; y++) {
						if(c[i] == (e[y])){
							$('input[value="'+c[i]+'"]').prop("checked",true);
								break;
							}					
						}
					}
			
				
			//長出商品總量
			$.each(${getItemAll},function(key,value){
				 $('#item').prepend($('<li>').prepend($('<a>').attr('data-toggle','tab')
															  .attr('href','#')
						 									  .attr('name','item'+value.getItem_no)
						 									  .attr('class','item')
						 									  .text(value.item_name)))
			})
			
			
			
			
				
 				 
 				 
 				 function insertItem(){				
					var arr=[];
					$('input[name="attributes"]').each(function(){
						arr.push($(this).val());
		 				console.log($(this).val());
					})
					var jsonString=JSON.stringify(arr);
//	 				//console.log(jsonString);
					storeNo='${StoreNo}'
					var itemName=$('#itemName').val();					
					$.ajax({
						'type':'get',
						'url':'/projHY20160201/SelectItemServlet.insert',
// 						'datatype':'json',
						'data':{jsonString,itemId,itemName,storeNo,first55},
// 						'success':function(data3){
// 							itemId = data3.itemNoS;
// 							alert(itemId)
// 						}
					})
					//取得物品第一層
				$.ajax({
					'type':'get',
					'url':'/projHY20160201/SelectFirstServlet.select',
					'datatype':'json',
					'data':{'item_no':itemId},
					'success':function(data2){						
// 						alert(data2.first)
						first55 = data2.first;
// 						alert(first55)
					}
				})
 				 }

			
			
			//SHOW  點擊使用ajax抓出資料
			$('.item').click(function() {
				$('#itemName').val($(this).text())
				var itemIdShow = $(this).attr('name').substr(4,100);
				itemId = itemIdShow;
				$.ajax({
					'type':'get',
					'url':'/projHY20160201/SelectItemServlet.select',
					'datatype':'json',
					'data':{'item_no':itemIdShow},
					'success':function(data){						
						jsonData = data;
						item();						
					}
				})
				
				
				$(this).show()	
				$('input').show()
				$('#itemP').show()
				}
			)	
			
	
			//彈跳視窗內容
			 $( "#dialog" ).dialog({				 
				 modal: true,		 
			      autoOpen: false,	
			      buttons:{
			    	  確定: function() {
			    		  $('li').attr('class','')
			    		  if($('#AAB').val() != ''){			    		  
			    		  
						var first11=$('input[name=first]:checked').val()
							first55=$('input[name=first]:checked').val()
			              ii=0;			    		  
			    		  firstFunction(first11);
			              $(this).dialog( "close" )
// 			              itemId = null;                         
			              $('input').show()
						  $('#itemP').show()
// 			              item();
			              var itemName22 = $('#AAB').val()
// 			              alert($('#AAB').val())
			           		clickInsertItem(itemName22);
			              
// 			              alert(itemId)
			              
			    		  }
			    	  }		      	
			      }
			    });			 
				
				
			  //長出第二層屬性 第三層
			    function item(){
// 			    	$('#right').empty();
			    	$('div[id^="div"]').remove();
			    	$.each(jsonData, function(x, val){			
			    	    if (x=="defaultClass"){
// 			    	    	for( ii = 0; ii < val.length; ii++){
			    	        $.each(val , function(applier, a_val){							
								$.each(a_val,function(index2,target){						
									//index2第二層
// 									console.log('index2='+index2+', target='+target)
// 									console.log(ii)
									$("#99").before($('<div>').attr('id','div'+ii)
											 .append($('<input>').attr('type','text')
						                      			         .attr('name','attributes')
						                      			         .attr('value',index2)
						                	                     .attr('class','classDDD'+ii)).append($('<br>')));
													if(index2 == "Size"){$('input[value="Size"]').prop('readonly','readonly')}
									//target第三層
													for(var y = 0; y < target.length; y++){
														$('#bt'+ii).remove();
											           	$("#div"+ii).append($('<input>').attr('type','text')
											           									.attr('name','attributes')
											           								   .attr('value',target[y])
											           								   .attr('class','class'+ii)
											           								   .attr('style','width: 50px'))
											           								   		.append($('<input>').attr('type','button')
											           								   							.attr('id','bt'+ii)
											           								   							.attr('value','+').attr('onclick','killer(this)'))
											           								if(y == target.length-1){$("#right").append($('<br>'));}
														 }
								
									ii++;
								})

		    	      		});                

			    	    }
			    	});
			    		
	    		}
	    		
	    		
			    	
// 	    		$('input').click(function(){
// 					alert("aa")
// 					var getId=$(this).attr('id');
// 					var no=getId.substring(2);
// 				    $('input[id^=bt'+no+']').before($('<input>').attr('type','text')
// 				    								.attr('name','attributes')
// 		           								   .attr('placeholder','加布丁(10)')
// 		           								   .attr('class','class'+ii)
// 		           								   .attr('style','width: 65px')
// 		           								   .attr('onblur','bbb(this)'))

// 				})
			//++屬性根細項  (新增)
			$('input[id^="but99"]').click(function(){
				$("#99").before($('<div>').attr('id','div'+ii)
						 .append($('<input>').attr('type','text')
								     		 .attr('name','attributes')
	                      			         .attr('placeholder','屬性')	                      			         
	                	                     .attr('class','classDDD'+ii).attr('onblur','aaa(this)')).append($('<br>')));
				$("div[id$="+ii+"]").append($('<input>').attr('type','text')
							.attr('name','attributes')
						   .attr('placeholder','加布丁(10)')
						   .attr('class','class'+ii)
						   .attr('style','width: 65px').attr('onblur','bbb(this)'))
						   		.append($('<input>').attr('type','button')
						   							.attr('id','bt'+ii)
						   							.attr('value','+').attr('onclick','abc(this)'))
						$(this).attr('onclick','focus1()');
	                       ii++;
			})
			//刪除細項
				$('input[class^="class"]').blur(function(){
					var val=$(this).val()					
					if(val==''){
						$(this).remove();
					}else{
						var rc = /^.+\(\d+\)$/;
						if(!rc.test(val) && $(this).attr('class').charAt(5) != 'D'){							
							val=val.replace('(', '');
							val=val.replace(')', '');
							$(this).val(val+'(0)')
							}						
					}	
				})
				
			//刪除屬性	
				$('input[class^="classD"]').blur(function(){
					var no=$(this).attr('class').substring(8);
					var val=$(this).val()					
						if(val==''){
							$("div[id$="+no+"]").remove();
						}

				})
				

   												
	
				$('#a').click(function(){
					$('input[id^="but88"]').before($('<a>').attr('href','#')
											.text($('#AAB').val())												
   												).before($('<br>'))   											 
				})	
				
				$('input[id^="but99"]').click(function(){
// 					alert($(this).id())
				})
				
				
				$('#id').click(function(){
					 insertItem();
					alert('aa')
				})
				
		})//load end
		function killer(aa){
// 			alert($(aa).attr('id'));
				var getId=$(aa).attr('id');
				var no=getId.substring(2);
			    $('input[id^="bt'+no+'"]').before($('<input>').attr('type','text')
			    								.attr('name','attributes')
	           								   .attr('placeholder','加布丁(10)')
	           								   .attr('class','class'+ii)
	           								   .attr('style','width: 65px')
	           								   .attr('onblur','bbb(this)'))
		}
		
		function abc(obj){	
			var getId=$(obj).attr('id');
			var no=getId.substring(2);
		    $('input[id^=bt'+no+']').before($('<input>').attr('type','text')
		    								.attr('name','attributes')
//            								   .attr('value','細項(0)')
           								   .attr('placeholder','加布丁(10)')
           								   .attr('class','class'+ii)
           								   .attr('style','width: 65px')
           								   .attr('onblur','bbb(this)'))			   
		}		
		function aaa(obj){
					var no=$(obj).attr('class').substring(8);
					var val=$(obj).val()
						if(val==''){
							$("div[id$="+no+"]").remove();
						}
		}
		function bbb(obj){

			var val=$(obj).val()
			if(val==''){
				$(obj).remove();
			}else{					
				var rc = /^.+\(\d+\)$/;
				if(!rc.test(val)){					
					val=val.replace('(', '');
					val=val.replace(')', '');
					$(obj).val(val+'(0)')
					}						
				
			}
			
}
		
		function focus1() {
		    $('input[class^=classDDD'+(ii-1)+']').focus();
//  			alert('aa')
		}
		
		function firstFunction(first11){
			    	switch (first11){
					case "飲料":			    
// 				var jsonData1 = {defaultClass :[
// 							 {SIZE : ['特大(30)', '大(25)' , '中(20)', '小(15)']},
// 					        {冷熱 : ['正常冰(0)','少冰(0)', '去冰(0)' ,'溫(0)']},
// 					        {甜度 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
// 							]}
				var jsonData1 = {defaultClass :[
							 {Size : ['特大(30)', '大(25)' , '中(20)', '小(15)'],
					       		冷熱 : ['正常冰(0)','少冰(0)', '去冰(0)' ,'溫(0)'],
					 		       甜度 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
							]}
				jsonData = jsonData1;
					break;
				case "便當":
				var jsonData2 = {defaultClass :[
				  							 {Size : ['基本(50)', '加大(60)']},
				  					        {其他 : ['加飯(5)','加飯(10)', '加菜(10)']},				  					       
				  				]}
				jsonData = jsonData2;
					break;
					case "甜點":						
				var jsonData3 = {defaultClass :[
				  							 {Size : ['大(45)' , '中(35)', '小(25)']},
				  					        {口味 : ['巧克力(0)','草莓(0)', '奶油(0)' ,'花生(0)']},
				  					        {其他 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
				  				]}
				jsonData = jsonData3;
					break;
				case "其他":
				var jsonData4 = {defaultClass :[
				  							 {Size : ['大(150)' , '中(100)', '小(50)']},
				  					        {顏色 : ['紅色(0)','黑色(0)', '白色(0)']},
				  					        {其他 : ['加配件(30)','加套件(50)']}
				  				]}
				jsonData = jsonData4;
				break;			  	  
			    	}
			    }	
		
		function clickInsertItem(itemName22){			
			var arr=[];
			$('input[name="attributes"]').each(function(){
				arr.push($(this).val());
					console.log($(this).val());
			})
			var jsonString=JSON.stringify(arr);
//				//console.log(jsonString);
			storeNo='${StoreNo}'
			var itemName=$('#itemName').val();	
			itemId = null;  
// 			alert(jsonString);
// 			alert(itemId);
// 			alert(itemName22);
// 			alert(storeNo);
// 			alert(first55);
			$.ajax({
				'type':'get',
				'url':'/projHY20160201/SelectItemServlet.insert',
				'datatype':'json',
				'data':{jsonString,itemId,itemName22,storeNo,first55},
				'success':function(data4){		
					oop(data4.itemNoS);
// 						alert(data4.itemNoS)
// 						return itemName22;
				}
			})
		}
		function oop(no){
			alert(no)
			itemId = no;
			
			$('#item').append($('<li>').attr('class','active')
                    .prepend($('<a>').attr('data-toggle','tab')
                    .attr('href','#')									  				
	  				.attr('name','item'+itemId)
	 				.attr('class','item')									  
				    .text($('#AAB').val())))
			$('#itemName').val($('#AAB').val())
			$('#AAB').val('')	
		}
		
		//偷偷把商品存進資料庫		
		 $('.item').on('click',insertItem)
		 $('a').click(function(){
				alert($(this).attr('name'))
			})
	</script>
	<jsp:include page="/footer.jsp"/>
</div>
</body>
</html>