package com.tianrui.service.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import com.tianrui.service.util.ImageUtils;

public class UptImgUtil {

	public static void main(String[] args) {
//		fileToBase64("H:\\6bba6b5db167426c85109dc58b5741dd (2).png");
//		fileToBase64("H:\\e93fbcc37deb474f90fcf8d0005dbc5c.png");
//		fileToBase64("H:\\c40f7e3512014c91a4fc0d90a97a3c4a.png");
//		H:\\che1.png  H:\\new.png
//		GenerateImage(fileToBase64("H:\\6bba6b5db167426c85109dc58b5741dd (2).png").substring(22));
		 try{  
            downLoadFromUrl("http://www.da156.cn/uploadimgs/c40f7e3512014c91a4fc0d90a97a3c4a.png");  
        }catch (Exception e) {  
            // TODO: handle exception  
        }  
	}
	
	/*** 获取网络图片base64*/
	public static String  downLoadFromUrl(String urlStr) throws IOException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        //得到输入流  
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组  
        byte[] getData = readInputStream(inputStream);   
        
        String base64 = Base64.encodeBase64String(getData);
//        System.out.println(base64);
        return base64;
//        if(base64.indexOf("data/image/")!=-1){
//        	GenerateImage(base64.substring(22));
//        }
//        System.out.println("info:"+url+" download success");   
	 }  
	 
	 /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
	  
	
	
	public static String fileToBase64(String path) {
        String base64 = "";
		try {
            byte[] bytes = ImageUtils.getBytesDelFile(path);;
            base64 = Base64.encodeBase64String(bytes);
            System.out.println(base64);
        } finally {
        }
        return base64;
    }
	//base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "H:\\new22.png";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
}
