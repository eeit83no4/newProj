<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

			
							
				<span>商品名稱：</span><input type='text' name='itemName' size='20' required="required"/><span class='required'>${errorMsg.noItemName}(必填)</span><br/>
				<span>商品類型：</span>
				<label><input type='radio' name='itemClass1st' value='飲料' checked='checked'>飲料</label>
				<label><input type='radio' name='itemClass1st' value='便當'>便當</label>
				<label><input type='radio' name='itemClass1st' value='甜點'>甜點</label>				
				<label><input type='radio' name='itemClass1st' value='其他'>其他</label><br/>
				<!-- 商品大小價錢區 -->
				<span>商品價錢：</span><input type='text' size='60' name='SizePrice' required="required"/><span class='required'>(必填)</span><br/>
				<span class='required'>${errorMsg.noSizePrice}</span><br/>
				<!-- 商品照片 -->
				<span>商品照片：</span><input type="file" name='itemPic' accept="image/*" /><hr/>
				<!-- 第二第三層屬性區 -->
				<span>商品細項(單選)：</span><br/>
				<textarea name='class2class3' rows="4" cols="50"></textarea><hr/>
				<!-- 加料區 -->
				<span>加料(複選)：</span><br/>
				<textarea name='extraStuff' rows="4" cols="50"></textarea><br/>
				
		
	

