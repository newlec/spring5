package com.newlecture.web.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.NoticeView;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	//  /notice/list?p=1&f=title&q=sdfaf
	//  1. HttpServletRequest �� �̿��� �������� ���
	//  2. �������� �����ϴ� ����� �̿��ϴ� ���
	
	// �Է� ��� 2 : ������ ����� �̿��� �Է� ���
	@RequestMapping("list")  // list?p=1
	//int, Integer, int?, 
    //int? x = null;
	public String list(Model model
			, @RequestParam(name="p", defaultValue="1") Integer page
			) throws ClassNotFoundException, SQLException {
					
		List<NoticeView> list = noticeDao.getList(page);
		
		model.addAttribute("list", list);
		
		return "notice/list";
	}
	
	/*
	// �Է� ��� 1 : ���� ����� �̿��� �Է� ���
	@RequestMapping("list")
	public String list(Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		int page = 1;
		String p = request.getParameter("p");
		if(p != null && !p.equals(""))
			page = Integer.parseInt(p);
		
		List<NoticeView> list = noticeDao.getList(page);
		
		model.addAttribute("list", list);
		
		return "notice/list";
	}
	*/
}




