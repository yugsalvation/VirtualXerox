package com.spring.virtualxerox.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
  public static String uploadDirectory = System.getProperty("user.home")+File.separator+"Documents"+File.separator+"workspace-spring-tool-suite-4-4.5.1.RELEASE"+File.separator+"VirtualXerox"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static";
  @RequestMapping("/")
  public String UploadPage(Model model) {
	 
	  return "uploadview";
  }
  @RequestMapping("/upload")
  public String upload(Model model,@RequestParam("files") MultipartFile file) {
	  StringBuilder fileNames = new StringBuilder();		
	 // System.out.println(model.getAttribute("filename"));
		  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		  fileNames.append(file.getOriginalFilename()+" ");
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		  try {

				//	Process p = Runtime.getRuntime().exec("chsh -s /bin/zsh;cd /Users/yugrawal/PycharmProjects/opencvexamples;python3 matplotlibsss.py");
				Process p = Runtime.getRuntime().exec("/usr/bin/python3 /Users/yugrawal/Documents/workspace-spring-tool-suite-4-4.5.1.RELEASE/VirtualXerox/src/main/resources/virtualxerox.py "+file.getOriginalFilename());
				
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				String s = null;
				while ((s = in.readLine()) != null) { // it returns null at the end of the file
				    System.out.println(s);
				}
				
				
				   
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		  try {
//			TimeUnit.SECONDS.sleep(4);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 
	  model.addAttribute("msg",fileNames.toString());
	  model.addAttribute("msg2","copy"+fileNames.toString());	

	  return "progress";
  }
  
  @RequestMapping("/progress")
  public String progress(Model model,HttpServletRequest request,HttpServletResponse response) {
	  String filename=(String) model.getAttribute("msg");
	  String msg2=(String)model.getAttribute("msg2");
	  
	  
	  
	  model.addAttribute("msg",filename);
	  model.addAttribute("msg2",msg2);	
		
	  return "progress";
  }
  
  @RequestMapping("/xeroxready")
  public String xeroxready(Model model,@RequestParam("msg") String filename,@RequestParam("msg2") String msg2,HttpServletRequest request,HttpServletResponse response) {
	 // String filename=(String) model.getAttribute("msg");
	  //String msg2=(String)model.getAttribute("msg2");

	  model.addAttribute("msg",filename);
	  model.addAttribute("msg2",msg2);	
		
	  return "uploadstatusview";
  }
  
//  @RequestMapping("/download")
//  public String download(Model model,@RequestParam("msg") String msg,HttpServletRequest request,HttpServletResponse response) {
//	  String filename=msg;
//	  System.out.println(filename);
//	 model.addAttribute("msg",filename);
//	 response.setContentType("application/jpg");
//		response.addHeader("content-disposition", "attachment; filename="+filename);
//		  Path file = Paths.get(uploadDirectory,filename);
//
//		
//		
//		
//	  return "uploadstatusview";
//  }
  
  
  
}
