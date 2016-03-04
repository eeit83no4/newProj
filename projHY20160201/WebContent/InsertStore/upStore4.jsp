<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#left{
	background-color: red;
	width:350px;
}
</style>
</head>
<body>
	<form action="<c:url value='/insertStore.action' />" method="get">
	
	
		店家名稱: <input type="text" value="草莓店" id="name" name="store" placeholder="店家名稱" /><br />
		電話: <input type="text" value="093435737" id="phone" name="phone" placeholder="電話" /><br />		
		地址: <input type="text" value="大安區" id="phone" name="address" placeholder="地址" /><br />			
 		類型: <div id="showBlock" ></div> 		
		<div style="float: left; border: 10px;margin: 125px " id="left"></div>
			<input type="text" value="草莓牛奶" name="item" /><br />
<!-- 			<input type="button"  id="Ibtn" value="新增商品" /> -->
			<input type="button" value="+" id="but88" /><br />
		<div style="float: left; border: 10px;margin: 125px" id="right">
			<input type="button" id="bt0" value="+"/>
			<div id="99"><input type="button" id="but99" value="+"/></div>
		</div>
		<input type="submit" value="新增" id="id">
		
		<div id="dialog" title="Basic dialog">
  		<input type="text" value="" id="AAB"/><br/>
<!--   		<a id="a" href="#">OK</a> -->
  		
</div>
	</form>
	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script>
	var txtId = 1;
	ii=0;
		$(function(){
			 $( "#dialog" ).dialog({
				 modal: true,		 
			      autoOpen: false,	
			      buttons:{
// 			    	 if($('#AAB').val() != '')
			    	  確定: function() {
			    		  if($('#AAB').val() != ''){
			    		  $('input[id^="but88"]').before($('<a>').attr('href','#')
									.text($('#AAB').val())												
											).before($('<br>')) 			    		  
			              $( this ).dialog( "close" );
			              $('#AAB').val('');
			    		  }
			    	  }			      	
			      }
			    });			 
			    $( "#but88" ).click(function() {
			      $( "#dialog" ).dialog( "open" );			      
			    });
// 			    $('#a').click(function(){
// 			    	 $( this ).dialog( "close" );
// 			    });
// 			var json="${attributes}"
// 					var json1=JSON.parse(json);
// 			alert(json1)
// 				ii=0;
				var jsonData = {defaultClass :[
							 {SIZE : ['特大(30)', '大(25)' , '中(20)', '小(15)']},
					        {冷熱 : ['正常冰(0)','少冰(0)', '去冰(0)' ,'溫(0)']},
					        {甜度 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
							]}
				
				
				
				
				//長出第二層屬性 第三層
				$.each(jsonData, function(x, val){
				    if (x=="defaultClass"){
				    	for( ii = 0; ii < val.length; ii++){
				        $.each(val[ii] , function(applier, a_val){	
// 				        	console.log(i);
							
							$("#99").before($('<div>').attr('id','div'+ii)
														 .append($('<input>').attr('type','text')
									                      			         .attr('name','attributes')
									                      			         .attr('value',applier)
									                	                     .attr('class','classDDD'+ii)).append($('<br>')));
       						if(applier == "SIZE"){$('input[class^=classD]').prop('readonly','readonly')}
// 									alert(applier);		                    
							for(var y = 0; y < a_val.length; y++){
							$('#bt'+ii).remove();
				           	$("#div"+ii).append($('<input>').attr('type','text')
				           									.attr('name','attributes')
				           								   .attr('value',a_val[y])
				           								   .attr('class','class'+ii)
				           								   .attr('style','width: 50px'))
				           								   		.append($('<input>').attr('type','button')
				           								   							.attr('id','bt'+ii)
				           								   							.attr('value','+'))
				           								if(y == a_val.length-1){$("#right").append($('<br>'));}
							  }
				      		});                
				      	  }
				    }
				});
				//長出店家類型 名稱
			$('#name').val('${data.storeName}');	 
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
				
			$('input[id^="bt"]').click(function(){
				var getId=$(this).attr('id');
				var no=getId.substring(2);
// 				alert(no);
			    $('input[id^=bt'+no+']').before($('<input>').attr('type','text')
			    								.attr('name','attributes')
	           								   .attr('placeholder','加布丁(10)')
	           								   .attr('class','class'+ii)
	           								   .attr('style','width: 65px')
	           								   .attr('onblur','bbb(this)'))

			})
			//++屬性根細項
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
// 						else if(val){
							
// 						}
				})
				
// 			$('input[id^="but88"]').click(function(){
// 					$(this).before($('<input>').attr('type','text')
// //    												.attr('id','bt'+ii)
// 													.attr('value','新增商品')													
//    												).before($('<br>'))
   												
// 				})	
				$('#a').click(function(){
					$('input[id^="but88"]').before($('<a>').attr('href','#')
											.text($('#AAB').val())												
   												).before($('<br>'))   											 
				})	
				
				
		})
		
		
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
// 			alert(obj)
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
		
		
		
	</script>
	
</body>
</html>