package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.util.StringUtil;

@SuppressWarnings("serial")
public class FeedOnBufferEmptyServlet extends FeedRandomizerServlet {
	public static final String PROFILE_ID = "profileId";
	public static final String ACCESS_TOKEN = "accessToken";
	protected String defaultProfileId = "5042c321fc99f7612900000f";
	protected String defaultAccessToken = "1/14e185521a83bb5f4a253d59e5d53a5e";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.defaultProfileId = StringUtil.ensureNotNullOrEmpty(
				config.getInitParameter(PROFILE_ID), this.defaultProfileId);
		this.defaultAccessToken = StringUtil.ensureNotNullOrEmpty(
				config.getInitParameter(ACCESS_TOKEN), this.defaultAccessToken);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String itemRss = "";

		if (StringUtil.isBufferEmpty(getBufferPendingUrl(getProfileId(req),
				getAccessToken(req)))) {
			itemRss = super.findItem();
		}
		resp.setContentType(APPLICATION_RSS_XML);
		resp.getWriter().println(rssPrefix + itemRss + rssSuffix);
	}

	protected String getAccessToken(HttpServletRequest req) {
		return StringUtil.ensureNotNullOrEmpty(req.getParameter(ACCESS_TOKEN),
				this.defaultAccessToken);
	}

	protected String getProfileId(HttpServletRequest req) {
		return StringUtil.ensureNotNullOrEmpty(req.getParameter(PROFILE_ID),
				this.defaultProfileId);
	}

	protected String getBufferPendingUrl(String profileId, String accessToken) {
		return "https://api.bufferapp.com/1/profiles/" + profileId
				+ "/updates/pending.json?access_token=" + accessToken
				+ "&count=1";
	}
}
