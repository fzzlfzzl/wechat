<%@ page language="java" pageEncoding="UTF-8"%>
<div id="holder">
	<form id="form">
		<div class="form-group">
			<input type="hidden" name="openid" value="${openid}" />
		</div>
		<div class="form-group">
			<a class="btn btn-default outline-primary" type="button">麻辣</a> <a
				class="btn btn-default" type="button">微辣</a>
		</div>
		<div class="form-group">
			<a class="btn btn-default outline-primary" type="button">大份</a> <a
				class="btn btn-default" type="button">中份</a> <a
				class="btn btn-default" type="button">小份</a>
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="telephone"
				name="telephone" placeholder="电话">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="address" name="address"
				placeholder="送餐地址">
		</div>
		<button type="submit" class="btn btn-success">确定</button>
	</form>
</div>
<script type="text/javascript">
	$(function() {
		$('#form').find('a').click(function(e) {
			$(this).parent().children().removeClass('outline-primary');
			$(this).addClass('outline-primary');
		});
		$('#form :submit').click(function(e) {
			console.log($('#form').serialize());
			$.post('${dealUrl}', $('#form').serialize(), function(data) {
				$('#holder').html(data);
			});
			return false;
		});
	});
</script>