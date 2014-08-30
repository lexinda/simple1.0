package com.lexindasoft.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	static final int BUFFER = 8192;

	private File zipFile;
	private FileOutputStream fileOutputStream;
	private CheckedOutputStream cos;
	private ZipOutputStream out;

	public ZipUtil(String pathName) {
		zipFile = new File(pathName);
		try {
			fileOutputStream = new FileOutputStream(zipFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		out = new ZipOutputStream(cos);
	}

	public void destroy() {

		try {
			out.close();
			cos.close();
			fileOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void compress(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "file not found!");
		try {

			String basedir = "";
			compress(file, this.out, basedir);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void compress(File file, ZipOutputStream out, String basedir) {
		if (file.isDirectory()) {
			this.compressDirectory(file, out, basedir);
		} else {
			this.compressFile(file, out, basedir);
		}
	}

	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].getName().endsWith("zip")){
				continue;
			}
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	private void compressFile(File file, ZipOutputStream out, String basedir) {

		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
//			ZipEntry entry = new ZipEntry(basedir + file.getName());
			ZipEntry entry = new ZipEntry(file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				this.out.write(data, 0, count);
			}
			bis.close();
			out.closeEntry();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		ZipUtil zc = new ZipUtil("d:/test001.zip");
		String[] aa = { "d:/001" };
		for (int i = 0; i < aa.length; i++) {
			zc.compress(aa[i]);
		}
		zc.destroy();
		System.out.println("OK....");
	}
}
