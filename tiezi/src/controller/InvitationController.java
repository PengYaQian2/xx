package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Invitation;
import pojo.Page;
import service.InvitationService;

@Controller
public class InvitationController {
	@Autowired
	private InvitationService invitationService;
	
	@RequestMapping("/query")
	public String query(@RequestParam(value="title",required=false)
	String title,@RequestParam(value="num",required=false,defaultValue="1")int num,
	Model model){
		 try {
			 int count = invitationService.count(title);
				Page page = new Page();
				
				page.setCountSize(count);
				
				page.setCurrNo(num);
				
				int pageNum = (page.getCurrNo()-1)*page.getSize();
				
				List<Invitation> list = invitationService.query(title, pageNum, page.getSize());
				
				model.addAttribute("list",list);
				
				model.addAttribute("count",count);
				
				model.addAttribute("page",page);
				
				model.addAttribute("title",title);
				
				return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		       return null;
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam("id") int id){
		try {
			boolean falg = invitationService.delete(id);
			if(falg==true){
				return "true";
			}else if(falg=false){
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
