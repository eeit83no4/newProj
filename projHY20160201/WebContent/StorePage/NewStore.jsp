<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Store</title>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>

</head>
<body>
<div id="div1">
<form>
<td><div  id="group"></div><a type="button" class="add" >+</a></td>
</form>
<a type="button" class="add2" >+</a>
</div>
<script>
var checkbox = '<input type="checkbox"/><input type="text" style="width: 50px"/>';

$(document).on('click', '.add', function () {
    $('#group').append(checkbox);
    $('#group').append(text);
    $('#group').controlgroup().trigger('create');
});

var form = '<div  id="group"></div><a type="button" class="add" >+</a>';
$(document).on('click', '.add2', function () {
 $('#div1').append(form);
 $('#div1').append(text);
 $('#div1').controlgroup().trigger('create');
});
// $(document).on('change', '[type=checkbox]', function (e) {
// });
</script>
</body>
</html>