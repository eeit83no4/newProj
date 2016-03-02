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
		<div style="float: left; border: 10px;margin: 125px " id="left"></div>
<!-- 			<input type="text" value="草莓牛奶" name="item" /><br /> -->
<!-- 			<input type="button"  id="Ibtn" value="新增商品" /> -->
			<input type="button" value="+" id="but88" /><br />
		<div style="float: left; border: 10px;margin: 125px" id="right">
			品名: <input type="text" value="草莓牛奶" name="item" />
			<input type="button" id="bt0" value="+"/>
			<div id="99"><input type="button" id="but99" value="+"/></div>
		</div>
		
		<div id="dialog" title="Basic dialog">
  		<input type="text" value="" id="AAB"/><br/>
  		<input type="radio" name="first" value="飲料" />飲料
  		<input type="radio" name="first" value="便當" />便當
  		<input type="radio" name="first" value="甜點" />甜點
  		<input type="radio" name="first" value="其他" />其他
<!--   		<a id="a" href="#">OK</a> -->
  		
</div>
		<input type="submit" value="確定新增" id="id">
	</form>
	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script>
	ii=0;
	jsonData = null;
		$(function(){		
			 $( "#dialog" ).dialog({
				 modal: true,		 
			      autoOpen: false,	
			      buttons:{
			    	  確定: function() {
			    		  if($('#AAB').val() != ''){
			    		  $('input[id^="but88"]').before($('<a>').attr('href','#')
			    				  								 .attr('name','item')
									.text($('#AAB').val())												
											).before($('<br>')) 			    		  
			              var first11=$('input[name=first]:checked').val()	
			              ii=0;
			    		  first(first11);
			              $(this).dialog( "close" )
			              $('#AAB').val('')			              
			              item();
			    		  }
			    	  }			      	
			      }
			    });			 
			    $( "#but88" ).click(function() {
			      $( "#dialog" ).dialog( "open" );			      
			    });
				
			$('input[id^="bt"]').click(function(){
				var getId=$(this).attr('id');
				var no=getId.substring(2);
			    $('input[id^=bt'+no+']').before($('<input>').attr('type','text')
			    								.attr('name','attributes')
	           								   .attr('placeholder','加布丁(10)')
	           								   .attr('class','class'+ii)
	           								   .attr('style','width: 65px')
	           								   .attr('onblur','bbb(this)'))

			})
			
			function item(){
				//長出第二層屬性 第三層
				$('input[class^="class"]').remove();
				$.each(jsonData, function(x, val){			
				    if (x=="defaultClass"){
				    	for( ii = 0; ii < val.length; ii++){
				        $.each(val[ii] , function(applier, a_val){	
//					        	console.log(i);
// 							alert(a_val);
							$("#99").before($('<div>').attr('id','div'+ii)
														 .append($('<input>').attr('type','text')
									                      			         .attr('name','attributes')
									                      			         .attr('value',applier)
									                	                     .attr('class','classDDD'+ii)).append($('<br>')));
								if(applier == "SIZE"){$('input[class^=classD]').prop('readonly','readonly')}	                    
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
					
					}
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

				})
				

   												
	
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
		
		function first(first){
			    	switch (first){
					case "飲料":			    
				var jsonData1 = {defaultClass :[
							 {SIZE : ['特大(30)', '大(25)' , '中(20)', '小(15)']},
					        {冷熱 : ['正常冰(0)','少冰(0)', '去冰(0)' ,'溫(0)']},
					        {甜度 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
							]}
				jsonData = jsonData1;
					break;
				case "便當":
				var jsonData2 = {defaultClass :[
				  							 {SIZE : ['基本(50)', '加大(60)']},
				  					        {其他 : ['加飯(5)','加飯(10)', '加菜(10)']},				  					       
				  				]}
				jsonData = jsonData2;
					break;
					case "甜點":						
				var jsonData3 = {defaultClass :[
				  							 {SIZE : ['大(45)' , '中(35)', '小(25)']},
				  					        {口味 : ['巧克力(0)','草莓(0)', '奶油(0)' ,'花生(0)']},
				  					        {其他 : ['正常(0)','半糖(0)', '少糖(0)' ,'無糖(0)']}
				  				]}
				jsonData = jsonData3;
					break;
				case "其他":
				var jsonData4 = {defaultClass :[
				  							 {SIZE : ['大(150)' , '中(100)', '小(50)']},
				  					        {顏色 : ['紅色(0)','黑色(0)', '白色(0)']},
				  					        {其他 : ['加配件(30)','加套件(50)']}
				  				]}
				jsonData = jsonData4;
				break;			  	  
			    	}
			    }			
	</script>
	
</body>
</html>