package com.tianrui.test.mongo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.util.Base64;
import com.mongodb.gridfs.GridFSDBFile;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class FileUploadTest {
	
	public static Logger logger =LoggerFactory.getLogger(FileUploadTest.class);
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@Test
	public void uploadTest() throws FileNotFoundException{
		
		//File file= new File("d://1.jpg");
		
		String msg= "";
		byte[] out = Base64.decodeFast(msg);
		
		InputStream input = new ByteArrayInputStream(out);
		gridFsTemplate.store(input, "2.jpg");
	}
	@Test
	public void findTest() throws FileNotFoundException{
		 List<GridFSDBFile> list =gridFsTemplate.find(null);
		 for( GridFSDBFile gridFSDBFile:list ){
			 System.out.println(gridFSDBFile.getFilename());
			 System.out.println(gridFSDBFile.getMD5());
			 System.out.println(gridFSDBFile.getChunkSize());
		 }
	}

	
	 void getImgString(){
	
			//boolean a = FileUtils.writeFileAll("f:image/0369874.png", out, 0, out.length);
	 }
	
}
