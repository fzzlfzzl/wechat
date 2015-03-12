package com.web.view.site.admin;

import java.util.List;

import com.web.view.View;
import com.web.view.impl.composite.HtmlTag;
import com.wechat.dao.entity.Message;

public class MessageListView extends View {

	private List<Message> list = null;

	public MessageListView(List<Message> list) {
		this.list = list;
	}

	private View th() {
		HtmlTag tr = HtmlTag.tr();
		HtmlTag th = null;
		String[] content = { "时间", "类型", "内容", "事件", "关键字" };
		for (String s : content) {
			th = HtmlTag.th();
			th.setContent(s);
			tr.addChild(th);
		}
		return tr;
	}

	private View tr(Message m) {
		HtmlTag tr = HtmlTag.tr();
		HtmlTag td = null;
		String[] content = { m.getCreateTimeString(), m.getMsgType(), m.getContent(), m.getEvent(), m.getEventKey() };
		for (String s : content) {
			td = HtmlTag.td();
			td.setContent(s);
			tr.addChild(td);
		}
		return tr;
	}

	@Override
	public void render(StringBuffer sb) {
		HtmlTag table = HtmlTag.table();
		table.addClass("table");
		table.addChild(th());
		for (Message m : list) {
			table.addChild(tr(m));
		}
		table.render(sb);
	}

}
