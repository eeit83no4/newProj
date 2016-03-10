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

$('#BT').click(function(){
	var xml = new XMLHttpRequest();
	var itemno=[];
	$(":input:checkbox:checked").each(function(){
			console.log($(this).val());
		itemno.push($(this).val());
		console.log(itemno);
	})
		xml.open("get", "/projHY20160201/CreateGroupservlet.controller?itemno="+ itemno + "&store_no="+${store_no}, true);// 傳值給StoreServlet
		xml.send();	
})