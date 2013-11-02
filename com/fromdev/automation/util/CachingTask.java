package com.fromdev.automation.util;

public class CachingTask implements Runnable {

	private String[] feedUrls;

	public CachingTask(String[] url) {
		this.feedUrls = url;
	}

	@Override
	public void run() {
		FeedReader.loadFeeds(feedUrls);
	}

}
