package com.ponleu.config.gzip;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * 
 * @author Kimsour
 * @date 07 April 2016
 * 
 */
public class GZipResponseStream extends ServletOutputStream {
	private GZIPOutputStream gzipOutputStream;

	public GZipResponseStream(OutputStream output) throws IOException {
		this.gzipOutputStream = new GZIPOutputStream(output);
	}

	@Override
	public void close() throws IOException {
		this.gzipOutputStream.close();
	}

	@Override
	public void flush() throws IOException {
		this.gzipOutputStream.flush();
	}

	@Override
	public void write(byte b[]) throws IOException {
		this.gzipOutputStream.write(b);
	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		this.gzipOutputStream.write(b, off, len);
	}

	@Override
	public void write(int b) throws IOException {
		this.gzipOutputStream.write(b);
	}

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		// TODO Auto-generated method stub
		
	}
}
