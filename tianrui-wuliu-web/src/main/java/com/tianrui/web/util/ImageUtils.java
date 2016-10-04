package com.tianrui.web.util;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
* @ClassName: ImageUtils
* @Description: 图片处理类
* @author Fengkx
* @date 2015-12-7 下午4:28:42
 */
public class ImageUtils {
	private static String suff = "jpg";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * Description : 图片经过压缩(适用于后台增加会员)[返回二进制流]
	 * Create Time : 2015-12-9 下午2:07:19
	 * Creator : Fengkx
	 */
	public static byte[]  getImageIOByAdd(MultipartFile file, HttpServletRequest request, String fileName) {
		try {
			int i = 0;
			//上传的图片
			String path = request.getSession().getServletContext().getRealPath("temp/" + fileName);
			//上传图片目标位置保存
			File targetFile = new File(path);
			file.transferTo(targetFile);
			path = path.substring(0, path.lastIndexOf(".")) + "x";
			Thumbnails.of(targetFile).scale(1f).outputFormat(suff).toFile(path + ++i);
			File outFile = new File(path + i + "." + suff);
			Thumbnails.of(outFile).forceSize(800,600).outputFormat(suff).toFile(path + ++i);
			return getBytesDelFile(path + i + "." + suff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Description : 图片经过压缩后,生成二进制流
	 * Create Time : 2016-1-13 上午9:10:59
	 * Creator : Fengkx
	 */
	public static byte[]  getImageByte(String filePath, String fileName) {
		try {
			int i = 0;
			//组装图片位置
			String path = filePath + File.separator + fileName;
			//读取图片文件
			System.out.println("pathhh"+path);
			File targetFile = new File(path);
			path = path.substring(0, path.lastIndexOf(".")) + "x";
			//滤镜过滤并转换文件格式
			Thumbnails.of(targetFile).scale(1f).outputFormat(suff).toFile(path + ++i);
			File outFile = new File(path + i + "." + suff);
			//尺寸压缩
			Thumbnails.of(outFile).forceSize(480,800).outputFormat(suff).toFile(path + ++i);
			return getBytesDelFile(path + i + "." + suff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Description : 图片经过压缩并添加水印(适用于后台修改会员)[返回二进制流]
	 * Create Time : 2015-12-9 下午2:07:19
	 * Creator : Fengkx
	 */
	public static byte[]  getImageIOByEdit(MultipartFile file, HttpServletRequest request, String fileName) {
		try {
			int i = 0;
			//上传的图片
			String path = request.getSession().getServletContext().getRealPath("temp/" + fileName);
			//水印图片
			String pressImgPath = request.getSession().getServletContext().getRealPath("img/waterMarkL.png");
			//上传图片目标位置保存
			File targetFile = new File(path);
			file.transferTo(targetFile);
			path = path.substring(0, path.lastIndexOf(".")) + "x";
			Thumbnails.of(targetFile).scale(1f).outputFormat(suff).toFile(path + ++i);
			File outFile = new File(path + i + "." + suff);
			File waterFile = new File(pressImgPath);
			Thumbnails.of(outFile).forceSize(800,600).watermark(Positions.CENTER, ImageIO.read(waterFile), 1f).outputFormat(suff).toFile(path + ++i);
			return getBytesDelFile(path + i + "." + suff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Description : 返回字节流并删除临时文件下的图片
	 * Create Time : 2015-12-9 下午2:08:27
	 * Creator : Fengkx
	 */
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
			deleteFile(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	/**
	 * Description : 将图片编译为二进制文件用于存储
	 * Create Time : 2015-12-17 下午5:04:34
	 * Creator : Fengkx
	 */
	public static byte[] getBytes(String filePath){
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	/**
	 * Description : 二进制流转换为图片并保存
	 * Create Time : 2015-12-9 下午2:09:05
	 * Creator : Fengkx
	 */
	private static void saveToImgByStr(byte[] imgByte, String imgPath, String imgName) {
		if (imgByte != null) {
			try {
				File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);
				InputStream in = new ByteArrayInputStream(imgByte);
				byte[] b = new byte[imgByte.length];
				int nRead = 0;
				while ((nRead = in.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Description : 图片旋转
	 * Create Time : 2015-12-9 下午2:29:40
	 * Creator : Fengkx
	 */
	public static byte[] changeImage(byte[] imgByte, int rotNum, HttpServletRequest request) {
		try {
			String path = request.getSession().getServletContext().getRealPath("temp");
			String imageName = sdf.format(new Date());
			saveToImgByStr(imgByte, path, imageName + "." + suff);
			File image = new File(path, imageName + "." + suff);
			Image src = ImageIO.read(image);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			Thumbnails.of(image).rotate(rotNum * 90).forceSize(width, height).outputFormat(suff).toFile(path + File.separator + imageName);
			return getBytesDelFile(path + File.separator + imageName + "." + suff);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Description : 添加水印
	 * Create Time : 2015-12-9 下午2:08:52
	 * Creator : Fengkx
	 */
	public static byte[] pressImage(HttpServletRequest request, byte[] imgByte) {
		try {
			String path = request.getSession().getServletContext().getRealPath("temp");
			//水印图片
			String pressImgPath = request.getSession().getServletContext().getRealPath("img/waterMarkL.png");
			String imageName = sdf.format(new Date());
			saveToImgByStr(imgByte, path, imageName + "." + suff);
			File image = new File(path, imageName + "." + suff);
			File waterFile = new File(pressImgPath);
			Thumbnails.of(image).forceSize(800,600).watermark(Positions.CENTER, ImageIO.read(waterFile), 1f).outputFormat(suff).toFile(path + File.separator + imageName);
			return getBytesDelFile(path + File.separator + imageName + "." + suff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除服务器上存在的图片
	 * @param file
	 */
	private static void deleteFile(String filepath){
		File file = new File(filepath);
		if(file.exists())
			file.delete();
	}
}