package com.wechat.dao.db;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.junit.Test;

import com.wechat.dao.util.DbHandler;

public class ExportCreateTableScript {

	@Test
	public void test() throws Exception {
		String path = ClassLoader.getSystemResource("").getPath();
		path = path.replace("target/test-classes/", "src/test/resources/wechat.sql");
		System.out.println(path);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
		DbHandler handler = new DbHandler();
		List<SchemaUpdateScript> scripts = handler.generateSchemaUpdateScriptList();
		for (SchemaUpdateScript script : scripts) {
			System.out.println(script.getScript());
			bw.write(script.getScript());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
