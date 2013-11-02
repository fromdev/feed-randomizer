package com.fromdev.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class ShareableItem {

	public static final int MAX_TAGS = 5;

	private String url;
	private Set tags;
	private String description;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getTags() {
		return tags;
	}

	public String getTagsString() {
		int count = 0;
		StringBuilder tagStr = new StringBuilder();
		for (Iterator iterator = tags.iterator(); iterator.hasNext();) {
			String tag = (String) iterator.next();
			tagStr.append(tag);
			count++;
			if (count < MAX_TAGS) {
				tagStr.append(", ");
			} else {
				break;
			}
		}
		return tagStr.toString();
	}

	public void setTags(Set tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toRssItem() {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss Z");
		return "<item>\n\t    <title>" + description
				+ "</title>\n\t <guid isPermaLink=\"false\">" + UUID.randomUUID().toString()
				+ "</guid>\n\t    <link>" + StringUtil.getRedirectedUrl(url) + "</link>\n\t    <pubDate>"
				+ sdf.format(new Date())
				+ "</pubDate>\n\t    <description><![CDATA[ <p>" + description
				+ "</p> ]]></description> \n        </item>";
	}

	@Override
	public String toString() {
		return "ShareableItem [url=" + url;
		// // + ", tags=" + tags
		// + ", description=" + description + ", tagsString="
		// + getTagsString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShareableItem && StringUtil.notNullOrEmpty(url)) {
			return url.equals(((ShareableItem) obj).getUrl());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return url.hashCode();
	}

}
