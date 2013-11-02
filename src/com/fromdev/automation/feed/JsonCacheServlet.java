package com.fromdev.automation.feed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.util.FeedCache;
import com.fromdev.automation.util.ShareableItem;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class JsonCacheServlet extends HttpServlet {

	private Gson gson = new Gson();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String responeJson = gson.toJson(FeedCache.cache());
		resp.setContentType("application/json");
		resp.getWriter().println(responeJson);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		String jsonData = req.getParameter("json");
		ArrayList<ShareableItem> items = gson.fromJson(jsonData,
				(new ArrayList<ShareableItem>()).getClass());
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			ShareableItem shareableItem = (ShareableItem) iterator.next();
			FeedCache.add(shareableItem);
		}
		resp.setContentType("application/json");
		resp.getWriter().println("ok");
	}
}
