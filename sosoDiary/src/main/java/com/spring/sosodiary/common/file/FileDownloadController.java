package com.spring.sosodiary.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {

	private static String PROFILEIMG_REPO="D:\\KimSoYoung\\portfolio_SpringProject\\soso\\diaryImage";
	private static String DIARYIMG_REPO="D:\\KimSoYoung\\portfolio_SpringProject\\soso\\diaryImage";
   
   /* ����� ���̺귯�� Ȱ�� */
   @RequestMapping("/download")
   public void download(@RequestParam("imageFileName") String imageFileName, HttpServletRequest request, HttpServletResponse response) throws Exception{
   
      OutputStream out= response.getOutputStream();
      String downFile=PROFILEIMG_REPO + "\\" + imageFileName;
      File file = new File(downFile); //�ٿ�ε��� ���� ��ü�� ����

      //������ �ٿ�ε� ������� ó��
      if(file.exists()) {
         Thumbnails.of(file).size(50, 50).outputFormat("png").toOutputStream(out);
      }else {
         return;
      }
      byte[] buffer = new byte[1024*8]; //8k ��
      out.write(buffer);
      out.close();
   }
   
   @RequestMapping("/profile")
   public void profile(@RequestParam("profileImg") String profileImg, @RequestParam("id") String id, HttpServletResponse response) throws Exception {
      OutputStream out=response.getOutputStream();
      String path=null;
      if(profileImg==null || profileImg.equals("")) {
         path=PROFILEIMG_REPO+"\\_defaultImg\\defaultImg.jpg";
      }else {
         path=PROFILEIMG_REPO+"\\"+id+"\\"+profileImg;
      }
      File imageFile=new File(path);
      response.setHeader("Cache-Control", "no-cache");
      response.addHeader("content-disposition", "attachment;fileName="+profileImg); 
      FileInputStream fis=new FileInputStream(imageFile);
      byte[] buffer=new byte[1024*8];
      while(true) {
         int count=fis.read(buffer);
         if(count==-1) break;
         out.write(buffer,0,count); 
      }
      fis.close();
      out.close();
   }
   @RequestMapping("/diaryimage")
   public void product(@RequestParam("diaNo") String diaNo, HttpServletResponse response) throws Exception {
	   OutputStream out=response.getOutputStream();
	   String path=DIARYIMG_REPO+"\\"+diaNo+"\\image.jpg";
	   File imageFile=new File(path);
	   response.setHeader("Cache-Control", "no-cache");
	   response.addHeader("content-disposition", "attachment;fileName=image.jpg"); 
	   FileInputStream fis=new FileInputStream(imageFile);
	   byte[] buffer=new byte[1024*8];
	   while(true) {
		   int count=fis.read(buffer);
		   if(count==-1) break;
		   out.write(buffer,0,count); 
	   }
	   fis.close();
	   out.close();
   }

}