<%@ page language="java" pageEncoding="UTF-8"%>
<form action="${url}" method="post">
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right">
			<span class="line-item bold">菜品名称</span>
		</div>
		<div class="line-md-9">
			<input type="text" class="form-control" name="name" placeholder="请输入菜品名称">
		</div>
	</div>
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right">
			<span class="line-item bold">描述</span>
		</div>
		<div class="line-md-9">
			<textarea class="form-control" rows="3" name="desc" placeholder="请输入菜品描述"></textarea>
		</div>
	</div>
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right">
			<span class="line-item bold">价格</span>
		</div>
		<div class="line-md-9">
			<input type="text" class="form-control" name="price" placeholder="请输入价格">
		</div>
	</div>
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right">
			<span class="line-item bold">口味</span>
		</div>
		<div class="line-md-9">
			<div class="row">
				<div class="line-md-2">
					<button id="addTaste" class="btn btn-default">新增</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right">
			<span class="line-item bold">分量</span>
		</div>
		<div class="line-md-9">
			<div class="row">
				<div class="line-md-2">
					<button id="addQuantity" class="btn btn-default">新增</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row row-gap-bg">
		<div class="line-md-2 text-right"></div>
		<div class="line-md-9">
			<input class="btn btn-primary" type="submit" value="提交" />
		</div>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		$('#addTaste')
				.click(
						function() {
							console.log($(this).parentsUntil(".row"));
							var line = $('<div class="row row-gap-sm"><div class="line-md-2"><input type="text" class="form-control" name="tasteName" placeholder="名称"></div><div class="line-md-2"><input type="text" class="form-control" name="tastePrice" placeholder="价格差"></div></div>')
							$(this).parent().parent().before(line);
							return false;
						});
		$('#addQuantity')
				.click(
						function() {
							console.log($(this).parentsUntil(".row"));
							var line = $('<div class="row row-gap-sm"><div class="line-md-2"><input type="text" class="form-control" placeholder="名称"></div><div class="line-md-2"><input type="text" class="form-control" placeholder="价格差"></div></div>')
							$(this).parent().parent().before(line);
							return false;
						});
	});
</script>