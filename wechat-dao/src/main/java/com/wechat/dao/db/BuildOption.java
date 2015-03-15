package com.wechat.dao.db;

public class BuildOption {

	private static final String defaultPath = "hibernate.cfg.xml";

	public static BuildOption Online = new BuildOption(defaultPath, "IeSSkUaEwdLIOuyKGUtp", "Scu0HwGwVF0ov4ES7SGuH6Cj",
			"6OqgyPLkVIXrdmh8Rl8MpZ3YFKAfZ33U");
	public static BuildOption Development = new BuildOption(defaultPath, "wechat", "root", "root");
	public static BuildOption Test = new BuildOption(defaultPath, "test", "root", "root");

	private String path;
	private String db;
	private String user;
	private String pwd;

	public BuildOption(String path, String db, String user, String pwd) {
		this.path = path;
		this.db = db;
		this.user = user;
		this.pwd = pwd;
	}

	public String getPath() {
		return path;
	}

	public String getDb() {
		return db;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public static String getDefaultpath() {
		return defaultPath;
	}
}
