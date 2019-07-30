package com.newlecture.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.MyUser;
import com.newlecture.web.entity.MyUserDetails;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Controller("adminNoticeController")
@RequestMapping("/admin/notice/")
public class NoticeController {
	
	@Autowired
	//@Qualifier("mybatisNoticeDao")
	private NoticeDao noticeDao;
	
	//@Autowired
	//private SqlSessionTemplate sqlSesson;

	@GetMapping("edit")
	public String edit(Integer id, Model model) throws ClassNotFoundException, SQLException {
			
		model.addAttribute("notice", noticeDao.get(id));
		
		//return "admin/notice/edit";
		return "admin.notice.edit";
	}
	
	@PostMapping("edit")
	public String edit(Notice notice) throws ClassNotFoundException, SQLException {
		
		Notice n = noticeDao.get(notice.getId());
		// title, content, id(hidden)
		n.setTitle(notice.getTitle());
		n.setContent(notice.getContent());
		
		noticeDao.update(n);
		
		//update Notice set title=?, content=?, writerId=?, hit=?, regDate=?
		//		where id= ?
		
		return "redirect:detail?id="+notice.getId();
	}
	
	
	@GetMapping("detail")
	public String detail(Integer id, Model model) throws ClassNotFoundException, SQLException {
		
		Notice notice = noticeDao.get(id);
		
		model.addAttribute(notice);
		
		return "admin.notice.detail";
	}
	
	@RequestMapping("list")
	public String list(Model model) throws ClassNotFoundException, SQLException {
		
		
		//1. ���� ������ �����ؼ�
		//    SqlSession sqlSession = ?;
		//    ���� ��ü�� ���ؼ� ���۸� ��
		//    ���ϴ� �޼ҵ带 ȣ���Ѵ�.
		
		
		// 2. ���ǵ����� ��� �۾��� �������� ���ø����� �����ϰ�
		//     ���⼭�� �׳� �� ���� ������ �� ȣ���ϴ� ����� ����.
				
		
		//List<NoticeView> list = sqlSesson.getMapper(NoticeDao.class).getList();
		//NoticeDao noticeDao = sqlSesson.getMapper(NoticeDao.class);
		List<NoticeView> list = noticeDao.getList();
		
		model.addAttribute("list", list);

		//return "admin/notice/list"; // jsp �������� ã������ url�� ����
		return "admin.notice.list"; // tiles���� ������ ������ ��Ź�ϱ� ���� ���� �̸�
	}
	
	// ## 4.x ���� ���� ��� ##
	// GET ��û
	@GetMapping("reg")
	// @RequestMapping(value="reg", method=RequestMethod.GET)
	public String reg() {
		
		//return "admin/notice/reg";
		return "admin.notice.reg";
	}

	// POST ��û
	@PostMapping("reg")
	public String reg(Notice notice
			, String category
			, Principal principal
			, MultipartFile file
			, HttpServletRequest request) throws IOException, ClassNotFoundException, SQLException {
		// public String reg(Notice notice) {
		// public String reg(String category, String title, String file, String content)
		// {

		System.out.println(category);
		System.out.println(notice.getTitle());
		System.out.println(file);
		System.out.println(notice.getContent());

		// 1. ���ε� ��θ� ���
		String urlPath = "/upload";
		String path = request.getServletContext().getRealPath(urlPath);
		
		System.out.println(path);
		
		// 2. ���ε�� ���ϸ� ���
		String fileName = file.getOriginalFilename();//filePart.getSubmittedFileName();
		
		// 3. ��� ������ �ֱ�
		String filePath = path + File.separator + fileName; // d:\aa + "bb.jpg" -> d:\aabb.jpg

		System.out.println(filePath);

		// 4. ��ΰ� ���ٴ� ���� ����
		File pathFile = new File(path);
		if (!pathFile.exists()) // �������� ������
			pathFile.mkdirs();// �������ּ���.
		
		// 5. ������ ���ϸ� ��ο� �̹� �����ϴ� ���� : �̸� ��å
		// aa.jpg -> aa.jpg1 ==> aa1.jpg
		// aa1.jpg -> aa(1).jpg
		/*
		 * File ? = new File(?);
		 * 
		 * if(? �����Ѵٸ�) { ����(Ȯ����)�� �߶� �̸��� ��� �� �������� �Ұ�ȣ()�� �ִ��� Ȯ���ϰ� ������ ��ȣ�� �˾Ƴ��� 1������ ����
		 * ��.. fileName = ?; }
		 */

		File sameFile = new File(filePath);
		System.out.println(sameFile);
		if (sameFile.exists()) {

			int n = fileName.lastIndexOf("."); // fileName=hello.jpg n =?, name= , suffix,
			String name = fileName.substring(0, n); //
			String suffix = fileName.substring(n);

			// name.endsWith(")");
			// int parenS = name.lastIndexOf("(");
			// int parenE = name.lastIndexOf(")");

			// String indexC = name.substring(parenS+1, parenE);

			// int indexN = Integer.parseInt(indexC);

			// if (parenS == -1)
			fileName = name + "(" + 1 + ")" + suffix;
			// else {
			// indexN++;
			// fileName = fileName.substring(0, parenS+1)+ indexN +")"+ suffix;
			// }
		}

		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(filePath);

		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fis.read(buf)) != -1)
			fos.write(buf, 0, size);

		fis.close();
		fos.close();
		
		// ���� �α��� �� ����� ������ ��� ���		
		notice.setWriterId(principal.getName());
		noticeDao.insert(notice);
		

		// ���𷺼� : list ��������
		return "redirect:list";
		// ������ : return "admin/notice/reg";
	}

	/*
	 * // GET ��û
	 * 
	 * @RequestMapping(value="reg", method=RequestMethod.GET) public String reg() {
	 * 
	 * return "admin/notice/reg"; }
	 * 
	 * // POST ��û
	 * 
	 * @RequestMapping(value="reg", method=RequestMethod.POST) public String
	 * reg(String title) {
	 * 
	 * // ���𷺼� : list �������� return "redirect:list"; // ������ : return
	 * "admin/notice/reg"; }
	 */
}
