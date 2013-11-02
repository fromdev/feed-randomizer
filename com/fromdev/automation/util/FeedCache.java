package com.fromdev.automation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;

public class FeedCache {

	private static List<ShareableItem> cache = new ArrayList<ShareableItem>();
	private static final int MAX_SIZE_CACHE = 1000;

	public static void add(ShareableItem item) {
		if (!cache.contains(item)) {
			if (cache.size() >= MAX_SIZE_CACHE) {
				cache.remove(getRandomInCacheSizeRange());
			}
			cache.add(item);
		}
	}

	public static ShareableItem get(int i) {
		return cache.get(i);
	}

	public static void clear() {
		cache.clear();
	}

	public static ShareableItem getRandomItem() {
		if (cache.size() > 1) {
			int random = getRandomInCacheSizeRange();
			System.out.println("picked " + random);
			return cache.remove(random);
		}
		return null;
	}

	private static int getRandomInCacheSizeRange() {
		return (int) (Math.random() * (cache.size() - 1));
	}

	public static List cache() {
		return cache;
	}

}
