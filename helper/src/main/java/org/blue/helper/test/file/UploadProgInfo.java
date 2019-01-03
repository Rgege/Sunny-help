//package org.blue.helper.test.file;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import org.blue.helper.test.file.MultiForm.ProgressInfo;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller("uploadProgInfo")
//@RequestMapping(value="/upload_prog/")
//public class UploadProgInfo {
//
//	//取得根节点板块
//	@RequestMapping(value="{progId}",method=RequestMethod.GET)
//	@ResponseBody
//	public  ProgressInfo getProgInfo(
//			HttpServletRequest request,
//			@PathVariable("progId") String progId)
//			throws Exception{
//
//		ServletContext context = request.getSession().getServletContext();
//		//WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(context);
//		ProgressInfo progInfo=ProgressInfo.getProgressInfo(context, progId);
//		//System.out.println("progId="+progId+"__"+progInfo);
//		return progInfo;
//
//
//	}
//	@RequestMapping("uploadResult")
//	@ResponseBody
//	public List<MultiForm.FormPart> list(HttpServletRequest request){
//		List<MultiForm.FormPart> listFormPart=(List<MultiForm.FormPart>)request.getAttribute(MultiForm.REQUEST_KEY_FORMPARTLIST);
//		return listFormPart;
//	}
//	@PostMapping("uploadResultPage")
//	public String uploadResultPage(String progId, HttpServletRequest request,Model model){
//		List<MultiForm.FormPart> listFormPart=(List<MultiForm.FormPart>)request.getAttribute(MultiForm.REQUEST_KEY_FORMPARTLIST);
//		model.addAttribute("listFormPart",listFormPart);
//		return "bus/fileUpDown/uploadResult";
//	}
//	@RequestMapping("uploadResultPage1")
//	public String uploadResultPage1(HttpServletRequest request,Model model){
//		List<String> list=new ArrayList<String>();
//		list.add("12");
//		list.add("abc");
////		Map<String,Object> map=new HashMap<String, Object>();
////		map.put("hello",list);
//		model.addAttribute("hello",list);
//		return "demo";
//	}
//}
