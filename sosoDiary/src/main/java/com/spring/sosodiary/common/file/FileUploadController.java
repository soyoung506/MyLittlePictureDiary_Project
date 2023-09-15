package com.spring.sosodiary.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	 private static String IMAGE_REPO_PATH="C:\\springtofit\\image_repo";
   
   @RequestMapping(value="/form")
   public String form() {
      return "uploadForm";
   }
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map map = new HashMap(); //매개변수 정보와 파일 정보를 저장할 map 생성함
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement(); //이름도 받고 값도 받아서 map에 저장하려고 Enumeration 이용 <키, 값>
			String value=multipartRequest.getParameter(name);													
			map.put(name, value);
		}
		List fileList=fileProcess(multipartRequest);
		map.put("fileList", fileList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("product/productList");
		return mav;	
	}
	


   
   //파일 처리 메서드
   private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
      List<String> fileList = new ArrayList<String>();
      Iterator<String> fileNames = multipartRequest.getFileNames();
      while(fileNames.hasNext()) {
         String fileName=fileNames.next();
         //파일 이름에 대한 multipartFile 객체를 가져옴
         MultipartFile mFile= multipartRequest.getFile(fileName);
         String originalFileName=mFile.getOriginalFilename();
         fileList.add(originalFileName);
         File file = new File(IMAGE_REPO_PATH + "\\" + fileName);
         if(mFile.getSize() != 0) {
            if(! file.exists()) {
               if(file.getParentFile().mkdirs()) {
                  file.createNewFile();
               }
            }
            //임시로 저장된 MultipartFile을 실제 파일로 전송한다
            mFile.transferTo(new File(IMAGE_REPO_PATH + "\\" + originalFileName));
         }
      }
      return fileList;
   }

}