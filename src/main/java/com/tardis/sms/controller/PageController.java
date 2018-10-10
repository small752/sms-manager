package com.tardis.sms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aliyuncs.utils.StringUtils;
import com.tardis.sms.service.ExcelBatchImportService;
import com.tardis.sms.util.ExcelImportUtils;

@Controller
@RequestMapping(value = "/page")
public class PageController {
	
	@Autowired
	private ExcelBatchImportService excelBatchImportService;
	
	@RequestMapping("/h")
    public String h() {
		System.out.println("2page");
        return "index";
    }
	
    //导入
    @PostMapping(value = "batchImport")
    public String batchImportUserKnowledge(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		
        //判断文件是否为空
        if(file==null){
       	 session.setAttribute("msg","文件不能为空！");
       	 return "redirect:toUserKnowledgeImport";
        }
        
        //获取文件名
        String fileName=file.getOriginalFilename();
        
        //验证文件名是否合格
        if(!ExcelImportUtils.validateExcel(fileName)){
       	 session.setAttribute("msg","文件必须是excel格式！");
       	 return "redirect:toUserKnowledgeImport";
        }
        
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
       	 session.setAttribute("msg","文件不能为空！");
       	 return "redirect:toUserKnowledgeImport";
        }
        
        //批量导入
        String message = excelBatchImportService.batchImport(fileName, file, "sss");
        session.setAttribute("msg",message);
        return "redirect:toUserKnowledgeImport";
    }

}
