package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.Multipart;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pojo.Detail;
import pojo.Page;
import service.DetailService;

@Controller
public class DetailController {
   @Autowired
   private DetailService detailService;
   
   @RequestMapping("/querys")
	public String querys(@RequestParam(value="invid",required=false)
	int invid,@RequestParam(value="num",required=false,defaultValue="1")int num,
	Model model,HttpSession httpSession){
		 try {
			 int count = detailService.count(invid);
				Page page = new Page();
				
				page.setCountSize(count);
				
				page.setCurrNo(num);
				
				int pageNum = (page.getCurrNo()-1)*page.getSize();
				
				List<Detail> list = detailService.querys(invid, pageNum, page.getSize());
				
				model.addAttribute("list",list);
				
				model.addAttribute("count",count);
				
				model.addAttribute("page",page);
				
				model.addAttribute("invid",invid);
				
				httpSession.setAttribute("invid",invid);
				
				return "selectHF";
		} catch (Exception e) {
			e.printStackTrace();
		}
		       return null;
		
	}
   
    @RequestMapping("/add")
	@ResponseBody
	public String add(Detail detail,HttpSession httpSession,Model model,@RequestParam(value="pathFile",required=false)MultipartFile multipart){
    	  
		
			if(!multipart.isEmpty()){
				 String name = multipart.getOriginalFilename();//原文件
				 
				 String num = name.substring(name.lastIndexOf("."), name.length());//截取点后的数据，包含开始位置不包含结束位置
				 
				 String path = httpSession.getServletContext().getRealPath("upload");//上传路径位置
				 
				 name = System.currentTimeMillis()+new Random().nextInt(10000000)+"dd"+num;//文件后缀名，处理重复名称
				 
				 
				 String prefix = FilenameUtils.getExtension(name);//获取原文件后缀
				 
				 int filesize=500000;//获取原文件大小，并进行大小判断
				 
				 if(multipart.getSize()>filesize){
					  model.addAttribute("pathfileError","文件大小不能超过500KB");
					  
					  return "add";
				 }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("peng") || prefix.equalsIgnoreCase("jpeg")){
					 String fileName = System.currentTimeMillis()+new Random().nextInt(10000000)+"ss.jpg";
					 
					 File file = new File(path,fileName);
				    
					  if(!file.exists()){
						  file.mkdirs();
					  }
					  
					  try {
						  name = path+File.separator+name;
						  
						  multipart.transferTo(file);
						  
						  detail.setPath(name);
					} catch (Exception e) {
						
						e.printStackTrace();
						
						model.addAttribute("pathfileError","上传失败");
						
						return "add";
					}
					  
				 }
				
			}else{
				
				try {
					int invid = (int) httpSession.getAttribute("invid");
					
					detail.setInvid(invid);
					
					boolean falg = detailService.add(detail);
					
					model.addAttribute("invid",invid);
					
					if(falg==true){
						
						return "true";
						
					}else if(falg=false){
						
						return "false";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
			
	}
   
}
