package com.tianrui.service.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 
 * @类描述：读取文件二进制流并删除图片
 * @创建人：lsj
 * @创建时间：2016年10月5日上午10:41:32
 *
 * @修改人：lsj
 * @修改时间：2016年10月5日上午10:41:32
 * @修改备注：
 *
 */
public class ImageUtils {
	/** 读取文件二进制流*/
	public static byte[] getBytesDelFile(String filePath){
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(512000);
			byte[] b = new byte[512000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
//			deleteFile(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	/** 删除图片*/
	private static void deleteFile(String filepath){
		File file = new File(filepath);
		if(file.exists())
			file.delete();
	}
}