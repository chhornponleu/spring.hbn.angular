package com.ponleu.config.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Kimsour
 * @date 07 April 2016
 * 
 */
public class GZipFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (this.acceptsGZipEncoding(httpRequest)) {
			httpResponse.addHeader("Content-Encoding", "gzip");
			GZipResponseWrapper gzipResponse = new GZipResponseWrapper(
					httpResponse);
			chain.doFilter(request, gzipResponse);
			gzipResponse.close();
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
		String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
		return acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1;
	}
}
