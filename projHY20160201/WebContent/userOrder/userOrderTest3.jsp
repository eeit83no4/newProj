<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>鴻揚科技有限公司-團購系統</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<style>
table,tr,td,th{
	font-size:20px;
	width:100%;
	margin:0 auto;
/* 	border:1px solid gray; */
	text-align: center;
}
#LefttDiv{ 
	float:left;
	border:2px solid gray; 
	width:200px; 
} 
#MediumDiv{
	border:2px solid gray; 
	margin-left:210px;
	margin-right:510px; 
} 
#RightDiv{ 
	float:right; 	
	border:2px solid gray; 
	width:500px;
} 
.button_div{
	text-align: center;
	margin-top: 2px;
	margin-bottom: 2px;
}
#totalDiv{
	text-align: right;
	margin:5px;
	border:1px solid gray;
}
.ann{
	border:1px solid gray;
	margin:3px;
}
#quantityamount{
	text-align:center;
}
/* 品項 */
.biaoTi{
	text-align: center;
}
/* 第二第三屬性區塊 */
#test9{
	border:1px solid gray;
	margin:3px;
}
/* 價錢區塊 */
#sizeZone{
	border:1px solid gray;
	margin:3px;
}
#content{
	margin:5px;
}
</style>
</head>
<body  class="home">
<div id="wrap">
	<!-- 	載入導覽列 -->
	<jsp:include page="/header.jsp"/>
	<!-- -------------------品項區塊--------------------------- -->

	<div id="LefttDiv">
		<div class='biaoTi'><h4>商品</h4></div>
		<div class='ann'>
			<table id="itemZone"></table>
		</div>
		<div class="ann">目前人數：<font id="peopleNo">${notice.usersString}</font></div>
		<div class="ann">目前累積金額：<font id="amountTotal">${notice.amountString}元</font></div>
		<c:if test="${!empty notice.ann}">
			<div class="ann">公告事項：<div id="annZone">${notice.ann}</div></div>
		</c:if>
		<c:if test='${!empty notice.shipment}'>
			<div class="ann">運費計算：<div id="shipmentZone">${notice.shipment}元</div></div>
		</c:if>
	</div>	
	<!-- -------------確認區塊-------------------- -->
	<div id="RightDiv">
		<form action="#">
			<div class="button_div">
				<input type="button" class='btn btn-default' name="order" value="訂下去" id="confirm"/>
				<input type="button" class='btn btn-default' name="clear" value="全部清除" id="clearBt"/>
				<input type="button" class='btn btn-default' name="cancel" value="取消"/>
			</div>
			<div id="totalDiv">
				小計：<font id="total">0</font>
			</div>
			<div id="content"></div>
			
				
		</form>	
	</div>
	<!-- -------------------特製區塊--------------------------- -->
	<div id="MediumDiv">		
		<div class='biaoTi'><h4>特製選項</h4></div>
		<form>
			<div class='biaoTi'>
				<span>商品：</span><span id="itemname">紅茶</span>
			</div>
			<div class='biaoTi'>
				<img id="imgZone" height="160" width="120"/>
			</div>
			<div id="sizeZone">	
			</div>
			<div id="addHere">				
			</div>
			
			<div class='ann'>
				<fieldset>
					<legend>備註：</legend>
					<textarea style='width:98%' rows="2" placeholder="備註" id="remark"/></textarea>
				</fieldset>
			</div>
			<div id="quantityamount">
				<fieldset>
					<span>原始單價：</span><input type="text" value="0" id="originitemprice" readonly/><br/>
					<span>加料後單價：</span><input type="text" value="0" id="itemprice" readonly/><br/>
					<span style='display:none'>運費計算後單價：</span><input type="text" value="0" id="discountitemprice" style='display:none' readonly/><br/>
					<span>數量：</span><input type="number" min="1" value="1" id="quantity"/><br/>
				</fieldset>
			</div>
			<div class="button_div">
				<button type="button" class='btn btn-default' id="addNewBt">加入</button>
			</div>
		</form>		
	</div>
	<!-- 	載入底部 -->
	<jsp:include page="/footer.jsp"/>
	
</div>

<script src="http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script>
	$(function(){
			
		
		
// 		------------一開始載入品項區塊-------------
		<c:forEach var="itemno" items="${itemnos}">
			<c:forEach var="itemname" items="${itemnames}">
				console.log('${itemname[itemno]}:');		
				$('#itemZone').prepend($('<tr/>').append($('<td/>').append($('<a/>').text('${itemname[itemno]}').attr('href','#').attr('id','${itemno}'))));		
			</c:forEach>
		</c:forEach>
// 		----------一開始載入特製區塊-------------
		<c:forEach var="itemno" items="${itemnos}" begin="1" end="1">		
			
		
			<c:forEach var="itemname" items="${itemnames}">
				console.log('=======================${itemname[itemno]}:');
				<c:forEach var="class2List" items="${class2Lists}">				
						//品項名稱
						$('#itemname').text('${itemname[itemno]}');
						//圖片
						$('#imgZone').attr('src','<c:url value="/userOrder/showPicAction.action?itemno="/>${itemno}');
						//sieze區域
						<c:forEach var="sizeprices" items="${sizepriceLists}">
							$('#sizeZone').text('Size：');
							<c:forEach var="sizeprice" items="${sizeprices[itemno]}">											
								var y='${sizeprice}';
								var priceno=y.split("=")[0];//價錢編號
								var price=y.split("=")[1];//價錢
								console.log('價錢編號:'+priceno+','+'價錢'+price);
								$('#sizeZone').append($('<label/>').text(price).prepend($('<input/>').attr('checked','checked').attr('name','size').attr('type','radio').attr('value',y).attr('onclick','xxx(value)')));
								var itemprice=($(':input:radio:checked').val().split("=")[1]).match(/[0-9]+/g);
								$('#itemprice').val(itemprice);//改變單價區塊
								$('#originitemprice').val(itemprice);//改變原始單價區塊
								//----------運費計算								
								$('#discountitemprice').val(itemprice);////改變運費計算後單價區塊
							</c:forEach>						
						</c:forEach>
						//第二第三屬性
						<c:forEach var="class2" items="${class2List[itemno]}">
							console.log('${class2}:');
							$('#addHere').prepend($('<div/>').attr('id','test9').text('${class2}:'));
							<c:forEach var="class3List" items="${class3Lists}">
								console.log('${class3List[itemno][class2]}');						
								<c:choose>
									<c:when test='${class2=="加料"}'>//複選區
										<c:forEach var="class3List" items="${class3List[itemno][class2]}">
											console.log('${class3List}');
											$('#test9').append($('<label/>').text('${class3List}').prepend($('<input/>').attr('type','checkbox').attr('name','${class2}').attr('value','${class3List}')));
										</c:forEach>	
									</c:when>
									<c:otherwise>//單選區
										<c:forEach var="class3List" items="${class3List[itemno][class2]}">
											console.log('${class3List}');
											$('#test9').append($('<label/>').text('${class3List}').prepend($('<input/>').attr('type','radio').attr('name','${class2}').attr('value','${class3List}')));
										</c:forEach>
									</c:otherwise>
								</c:choose>		
							</c:forEach>								
						</c:forEach>
				</c:forEach>			
			</c:forEach>
	
		</c:forEach>
// 		----------按下物品改變特製區塊資料-------------
		<c:forEach var="itemno" items="${itemnos}">
			<c:forEach var="itemname" items="${itemnames}">
				console.log('${itemname[itemno]}:');
				<c:forEach var="class2List" items="${class2Lists}">	
					$('#${itemno}').click(function(){
						$('#addHere').empty();
						$('#sizeZone').empty();
						//品項名稱
						$('#itemname').text('${itemname[itemno]}');
						//圖片
						$('#imgZone').attr('src','<c:url value="/userOrder/showPicAction.action?itemno="/>${itemno}');
						//sieze區域
						<c:forEach var="sizeprices" items="${sizepriceLists}">
							$('#sizeZone').text('Size：');
							<c:forEach var="sizeprice" items="${sizeprices[itemno]}">
								var y='${sizeprice}';
								var priceno=y.split("=")[0];//價錢編號
								var price=y.split("=")[1];//價錢
								console.log('價錢編號:'+priceno+','+'價錢'+price);
								$('#sizeZone').append($('<label/>').text(price).prepend($('<input/>').attr('checked','checked').attr('name','size').attr('type','radio').attr('value',y).attr('onclick','xxx(value)')));
								var itemprice=($(':input:radio:checked').val().split("=")[1]).match(/[0-9]+/g);
								$('#itemprice').val(itemprice);//改變單價區塊
								$('#originitemprice').val(itemprice);//改變原始單價區塊
								$('#discountitemprice').val(itemprice);////改變折扣後單價區塊
							</c:forEach>						
						</c:forEach>
						//第二第三屬性
						<c:forEach var="class2" items="${class2List[itemno]}">
							console.log('${class2}:');
							$('#addHere').prepend($('<div/>').attr('id','test9').text('${class2}:'));
							<c:forEach var="class3List" items="${class3Lists}">
								console.log('${class3List[itemno][class2]}');						
								<c:choose>
									<c:when test='${class2=="加料"}'>//複選區
										<c:forEach var="class3List" items="${class3List[itemno][class2]}">
											console.log('${class3List}');
											$('#test9').append($('<label/>').text('${class3List}').prepend($('<input/>').attr('type','checkbox').attr('name','${class2}').attr('value','${class3List}').attr('onclick','xxx2(this)')));
										</c:forEach>	
									</c:when>
									<c:otherwise>//單選區
										<c:forEach var="class3List" items="${class3List[itemno][class2]}">
											console.log('${class3List}');
											$('#test9').append($('<label/>').text('${class3List}').prepend($('<input/>').attr('type','radio').attr('name','${class2}').attr('value','${class3List}')));
										</c:forEach>
									</c:otherwise>
								</c:choose>		
							</c:forEach>								
						</c:forEach>
					})
				</c:forEach>			
			</c:forEach>
		</c:forEach>
		
				

		
// 		--------------------------------------------------------------
		var arr=[];//儲存所有訂單的資訊
		
// 		------------清除訂單---------
		$("#clearBt").click(function(){
			$("#content").empty();
			$("#total").text(0);
			arr=[];
		});	
// 		------------將選好的商品加入訂單---------
		$("#addNewBt").click(function(){
			var itemname=$("#itemname").text();//商品名字
			
			//圖片
			var pic=parseInt(($('#imgZone').attr('src').split("?")[1]).match(/[0-9]+/g));
			console.log('============================'+pic);
			
			
			var detail=null;//特製選項
			$(":input:radio:checked").each(function(){
				if($(this).attr("name")=='size'){
					var c=$(this).attr("name")+':'+$(this).val().split('=')[1];
					if(detail==null){
						detail=c;
					}else{
						detail=detail+","+c;	
					}
				}else{
					var c=$(this).attr("name")+':'+$(this).val();
					if(detail==null){
						detail=c;
					}else{
						detail=detail+","+c;	
					}
				}
				
			});
			//特製選項for加料
			var exTra=$(":input:checkbox").attr("name");//exTra=加料
			var exTras=null;
			$(":input:checkbox:checked").each(function(){
				if(exTras==null){
					exTras=$(this).val();
				}else{
					exTras=exTras+','+$(this).val();
				}
			});
			if(exTras!=null){
				detail=detail+','+exTra+':'+exTras;				
			}
			
			var remark=$("#remark").val();//備註
			var itemPrice=parseFloat($("#itemprice").val());//商品加料後單價，轉為數字
			var originitemprice=parseFloat($("#originitemprice").val());//商品原始單價，轉為數字
			var discountitemprice=parseFloat($("#discountitemprice").val());//折扣後單價，轉為數字
			var quantity=parseInt($("#quantity").val());//數量，轉為數字
			var oprice_no=parseInt($('input[name="size"]:checked').val().split('=')[0]);//商品價錢編號			
			if(remark){//是否有備註				
				$("#content").append("<p>"+itemname+"{"+detail+","+"備註:"+remark+",加料後價錢："+itemPrice+",折扣後價錢："+discountitemprice+"}"+"X"+quantity+"</p>"+"<hr/>");
			}else{
				$("#content").append("<p>"+itemname+"{"+detail+",加料後價錢："+itemPrice+",折扣後價錢："+discountitemprice+"}"+"X"+quantity+"</p>"+"<hr/>");
			}
			
			var total=parseFloat($("#total").text())//目前小計，轉為數字			
			total=total+(discountitemprice*quantity);//折扣後價錢*數量，加總後小計
			$("#total").text(total);
			
			//儲存每一筆			
			arr.push({'group_user_no':'${group_user_no}','ostore_name':'${notice.store}','oprice_no':oprice_no,'oitem_name':itemname,'originitemprice':originitemprice,'oprice':itemPrice,'oprice_after':discountitemprice,'oclass':detail,'ps':remark,'quantity':quantity,'pic':pic});

		});
// 		------------如果加料有價錢，重新計算商品單價---------
		$(":checkbox").change(function(){
			var value=parseFloat(($(this).val()).match(/[0-9]+/g));//加料價，轉為數字
			var itemPrice=parseFloat($("#itemprice").val());//商品加料後單價，轉為數字
			var discountitemprice=parseFloat($('#discountitemprice').val());//商品折扣後價錢，轉為數字
			var b=$(this).prop("checked");//被點選的方塊的狀態 true/false			
			if(b==true){//是否有被勾選
				$("#itemprice").val(itemPrice+value);
				$('#discountitemprice').val(discountitemprice+value);
			}else{
				$("#itemprice").val(itemPrice-value);
				$('#discountitemprice').val(discountitemprice-value)
			}
		});
// 		------------訂下去---------
		$('#confirm').click(function(){
			if(!$.isEmptyObject(arr)){
				var jsonString=JSON.stringify(arr);
				console.log(jsonString);
				$.ajax({
					"type":"POST",
					"url":'<c:url value="/userOrder/userOrderAction.action"/>',
					"data":{jsonString},											
					"success":function(){
						location.href='<c:url value='/index/indexServlet.controller'/>';
					}
				});		
				
			}else{
				console.log('你尚未選購');
			}
		});
		
	});
// 	------------選擇不同size時，商品單價會改變---------
	function xxx(price){
		var newprice=(price.split("=")[1]).match(/[0-9]+/g);
		//加料後單價
		var k=document.getElementById('itemprice');
		//原始單價
		var t=document.getElementById('originitemprice');
		//折扣後單價
		var w=document.getElementById('discountitemprice');
		//清除加料區塊
		var checkboxs=document.getElementsByName('加料');
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=false;
		}
		k.value=newprice;
		t.value=newprice;
		w.value=newprice;
	}
//		------------如果加料有價錢，重新計算商品單價---------
	function xxx2(obj){		
		var extra=parseFloat(obj.value.match(/[0-9]+/g));//加購價，轉為數字		
		var k=parseFloat(document.getElementById('itemprice').value);//商品加料後價錢，轉為數字		
		var w=parseFloat(document.getElementById('discountitemprice').value);//折扣後單價，轉為數字
		if(obj.checked){
			document.getElementById('itemprice').value=k+extra;
			document.getElementById('discountitemprice').value=w+extra;
		}else{
			document.getElementById('itemprice').value=k-extra;
			document.getElementById('discountitemprice').value=w-extra;
		}		
	}
	

</script>


</body>
</html>