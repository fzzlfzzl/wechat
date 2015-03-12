package com.wechat.message.tool;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.site.util.HttpsClient;
import com.site.util.JsonObject;
import com.web.interceptor.context.UserContext;
import com.wechat.dao.entity.AccessToken;
import com.wechat.dao.impl.AccessTokenDao;

public class AccessTokenManager {

	private static Logger logger = Logger.getLogger(AccessTokenManager.class);

	private static String APP_SECRET = "a22d71808ae9b3c7f9d926c7967a2c0c";
	private static String APP_ID = "wxddd3064d4314479d";

	private static AccessToken getAccessTokenFromDb() {
		Session session = UserContext.current().getSession();
		AccessTokenDao dao = new AccessTokenDao(session);
		AccessToken token = dao.get();
		if (token == null) {
			return null;
		} else if (isTimeout(token)) {
			dao.delete();
			return null;
		} else {
			return token;
		}
	}

	private static AccessToken getAccessTokenFromWeb(String url) {
		int retry = 3;
		for (int i = 0; i < retry; i++) {
			try {
				AccessToken token = queryAccessToken(url);
				return token;
			} catch (Exception e) {
				continue;
			}
		}
		throw new RuntimeException("Get Access Token From Web Fail, Url:" + url);
	}

	private static boolean isTimeout(AccessToken token) {
		if (token.getUpdatetime() + token.getTimeout() * 1000 - 200 * 1000 < System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	private static AccessToken queryAccessToken(String url) {
		HttpsClient client = new HttpsClient(url);
		String res = client.get();
		logger.info("Access Token Result: " + res);
		JsonObject obj = JsonObject.toJsonObject(res);
		AccessToken ret = new AccessToken();
		if (!obj.get("errcode").isNull() || obj.get("access_token").isNull()) {
			// 出错了
			logger.error(res);
			throw new RuntimeException("Get Access Token Fail");
		} else {
			ret.setAccessToken(obj.get("access_token").toString());
			ret.setTimeout(obj.get("expires_in").toInt());
			return ret;
		}
	}

	private static String getAccessTokenUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		String url = String.format(urlFmt, APP_ID, APP_SECRET);
		return url;
	}

	public static String getAccessToken() {
		AccessToken token = getAccessTokenFromDb();
		if (null != token && testAccessToken(token)) {
			return token.getAccessToken();
		}
		String tokenUrl = getAccessTokenUrl();
		token = getAccessTokenFromWeb(tokenUrl);
		AccessTokenDao dao = new AccessTokenDao(UserContext.current().getSession());
		dao.save(token);
		return token.getAccessToken();
	}

	private static boolean testAccessToken(AccessToken token) {
		String fmt = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";
		String url = String.format(fmt, token);
		HttpsClient client = new HttpsClient(url);
		String get = client.get();
		JsonObject obj = JsonObject.toJsonObject(get);
		if (obj.get("errcode").isNull())
			return true;
		else
			return false;
	}
}
