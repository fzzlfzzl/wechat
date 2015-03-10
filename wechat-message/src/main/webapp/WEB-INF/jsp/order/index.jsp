<%@ page language="java" pageEncoding="UTF-8"%>
<form action="deal" method="post">
	<input type="hidden" name="openid" value="${openid}" />
	选择:
	<input type="radio" name="dishes" value="麻辣" />
	麻辣
	<input type="radio" name="dishes" value="微辣" />
	微辣
	<br />
	姓名:
	<input type="text" name="name" />
	<br />
	电话:
	<input type="text" name="telephone" />
	<br />
	地址:
	<input type="text" name="address" />
	<br />
	<input type="submit" value="OK" />
</form>

