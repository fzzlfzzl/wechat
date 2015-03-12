package com.web.view.site.admin;

import java.util.List;

import com.web.view.View;
import com.web.view.impl.composite.HtmlTag;
import com.wechat.dao.entity.Orders;

public class OrderListView extends View {

	private List<Orders> list = null;

	public OrderListView(List<Orders> list) {
		this.list = list;
	}

	@Override
	public void render(StringBuffer sb) {
		sb.append("<table class='table'>");
		for (Orders order : list) {
			HtmlTag tr = HtmlTag.tr();
			// openid name telephone address dishes
			String[] content = new String[] { order.getOpenid(), order.getUserName(), order.getTelephone(),
					order.getAddress(), order.getDishes() };
			for (String s : content) {
				HtmlTag td = HtmlTag.td();
				td.setContent(s);
				tr.addChild(td);
			}
			sb.append(tr);
		}
		sb.append("</table>");
	}
}
