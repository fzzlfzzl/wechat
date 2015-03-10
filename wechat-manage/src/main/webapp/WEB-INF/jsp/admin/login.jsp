<%@ page language="java" pageEncoding="UTF-8"%>
<div style="height: 30px"></div>
<div style="max-width: 330px; margin: 0 auto">
	<form action="login" method="post">
		<div class="form-group">
			<label for="name">用户名</label>
			<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" autocomplete="off">
		</div>
		<div class="form-group">
			<label for="pwd">密码</label>
			<input type="password" class="form-control" id="pwd" name="pwd" placeholder="请输入密码">
		</div>
		<button type="submit" class="btn btn-primary">OK</button>
	</form>
</div>

