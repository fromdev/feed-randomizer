package com.fromdev.automation.feed;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.util.FeedCache;
import com.fromdev.automation.util.FeedReader;
import com.fromdev.automation.util.ShareableItem;
import com.fromdev.automation.util.StringUtil;

@SuppressWarnings("serial")
public class FeedRandomizerServlet extends HttpServlet {
	private static final String APPLICATION_RSS_XML = "application/rss+xml";
	String appBaseUrl = "http://feedrandomizer.appspot.com/";
	String appFeedUrl = appBaseUrl + "feedrandomizer";
	private final String rssPrefix = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rss version=\"2.0\"\n    xmlns:content=\"http://purl.org/rss/1.0/modules/content/\"\n    xmlns:wfw=\"http://wellformedweb.org/CommentAPI/\"\n    xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\n    xmlns:atom=\"http://www.w3.org/2005/Atom\"\n>\n\n<channel>\n    <title>Feed Randomizer</title>\n    <link>"
			+ appBaseUrl
			+ "</link>\n    <atom:link href=\""
			+ appFeedUrl
			+ "\" rel=\"self\" type=\"application/rss+xml\" />\n    <description>Feed Randomizer</description>\n    <language>en</language>";
	private final String rssSuffix = "</channel>\n</rss>";

	/**
	 * default feed list to load in case cache is empty
	 */
	private String feedList;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		feedList = config.getInitParameter("feedList");
	}

	private void initFeedCache() {
		String[] feeds = { "http://feeds.feedburner.com/Fromdev" };
		feeds = StringUtil.splitOrDefault(feedList, feeds);
		FeedReader.loadFeeds(feeds);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ShareableItem item = FeedCache.getRandomItem();
		String itemRss = "";
		if (item != null) {
			itemRss = item.toRssItem();
		} else {
			initFeedCache();
		}
		resp.setContentType(APPLICATION_RSS_XML);
		resp.getWriter().println(rssPrefix + itemRss + rssSuffix);
	}
}
