package com.wechat.util;

import java.io.FileInputStream;
import java.util.List;

import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;

import com.wechat.dao.util.DbHandler;

public class DbUpdateValidater {

	public static void main(String[] args) throws Exception {
		DbHandler handler = new DbHandler("test", "root", "root");
		String srcPath = "E:/workspace/wechat/wechat-dao/src/test/resources/wechat.sql.20150312";
		String update = "E:/workspace/wechat/wechat-dao/src/test/resources/wechat.sql.20150312-20150313";
		handler.dropTables();
		handler.execute(new FileInputStream(srcPath));
		handler.execute(new FileInputStream(update));
		List<SchemaUpdateScript> list = handler.generateSchemaUpdateScriptList();
		handler.dropTables();
		handler.close();
		assert (list.size() == 0);
	}
}
