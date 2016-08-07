package com.ponleu.config.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @author Kimsour
 * @date 07 April 2016
 * 
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper {
	private GZipResponseStream gzipOutputStream;
	private PrintWriter printWriter;

	public GZipResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	@Override
	public void setContentLength(int len) {
	}

	public void close() throws IOException {
		if (this.printWriter != null) {
			this.printWriter.close();
		}
		if (this.gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}

	@Override
	public void flushBuffer() throws IOException {
		if (this.printWriter != null) {
			this.printWriter.flush();
		}

		try {
			if (this.gzipOutputStream != null) {
				this.gzipOutputStream.flush();
			}
		} catch (IOException ex) {
			throw ex;
		}

		try {
			super.flushBuffer();
		} catch (IOException ex) {
			throw ex;
		}
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.printWriter != null) {
			throw new IllegalStateException(
					"PrintWriter obtained already - cannot get OutputStream");
		}
		if (this.gzipOutputStream == null) {
			this.gzipOutputStream = new GZipResponseStream(getResponse()
					.getOutputStream());
		}
		return this.gzipOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (this.printWriter == null && this.gzipOutputStream != null) {
			throw new IllegalStateException(
					"OutputStream obtained already - cannot get PrintWriter");
		}
		if (this.printWriter == null) {
			this.gzipOutputStream = new GZipResponseStream(getResponse()
					.getOutputStream());
			this.printWriter = new PrintWriter(
					new OutputStreamWriter(this.gzipOutputStream, getResponse()
							.getCharacterEncoding()));
		}
		return this.printWriter;
	}

}
