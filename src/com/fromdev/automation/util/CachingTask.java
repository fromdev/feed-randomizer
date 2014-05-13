package com.fromdev.automation.util;

public class CachingTask  {

	private String[] feedUrls;

	public CachingTask(String[] url) {
		this.feedUrls = url;
	}

	public void run() {
		FeedReader.loadFeeds(feedUrls);
	}

}
