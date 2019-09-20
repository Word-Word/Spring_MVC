package com.javalec.spring_mybatis;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javalec.spring_mybatis.dao.IDao;


@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;

	
	
	// ----- views ������ �ִ� jsp ���Ϸ� ������ �̵� -----
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "/writeForm";
	}
	
	
	@RequestMapping("/view")
	public String view() {
		return "/view";
	}
	
	
	
	
	// ----- DB ������ �ʿ��� �κ� -----
	
	@RequestMapping("/list")
	public String list(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.listDao());
		System.out.println("����Ʈ �̵�");

		return "/list";
	}
	
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		return "redirect:list";
	}

	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.deleteDao(request.getParameter("mId"));
		return "redirect:list";
	}
	
}
