package com.web.view.site.admin;

import java.util.List;

import com.web.dao.entity.User;
import com.web.view.View;
import com.web.view.impl.template.TemplateViewExpression;

public class UserListView extends View {

	private List<User> list = null;
	private String tpl = "<li class='list-group-item'><a href='user/$openid'>$openid</a></li>";
	private TemplateViewExpression exp = TemplateViewExpression.compile(tpl);

	public UserListView(List<User> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		sb.append("<ul class='list-group'>");
		for (User user : list) {
			View view = exp.createBuilder().setParam("openid", user.getOpenId()).build();
			view.render(sb);
		}
		sb.append("</ul>");
	}

}
