<%@ page language="java" pageEncoding="UTF-8"%>
<form action="<%=request.getContextPath()%>/order/deal" method="post">
	<div class="form-group">
		<button class="btn btn-default" type="button">麻辣</button>
		<button class="btn btn-default" type="button">微辣</button>
	</div>
	<div class="form-group">
		<input type="text" class="form-control" id="telephone" name="telephone" placeholder="电话">
	</div>
	<div class="form-group">
		<input type="text" class="form-control" id="address" name="address" placeholder="送餐地址">
	</div>
	<button type="submit" class="btn btn-default">确定</button>
</form>