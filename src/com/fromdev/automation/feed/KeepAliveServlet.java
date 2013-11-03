package com.fromdev.automation.feed;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fromdev.automation.util.CachingTask;
import com.fromdev.automation.util.FeedReader;
import com.fromdev.automation.util.StringUtil;
import com.google.appengine.api.ThreadManager;

@SuppressWarnings("serial")
public class KeepAliveServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("" + Math.random());
	}
}
