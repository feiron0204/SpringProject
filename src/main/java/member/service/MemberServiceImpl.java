package member.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private HttpSession httpSession;

	@Override
	public String login(Map<String,String> map) {
		MemberDTO memberDTO=memberDAO.login(map);
		if(memberDTO!=null) {
			httpSession.setAttribute("memName", memberDTO.getName());
			httpSession.setAttribute("memId", memberDTO.getId());
			httpSession.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			return "ok";
		}else {
			return "fail";
		}
		
	}

	@Override
	public void logout() {
		httpSession.invalidate();
	}

	@Override
	public String checkId(String id) {
		MemberDTO memberDTO=memberDAO.checkId(id);
		if(memberDTO!=null)
			return "exist";
		else 
			return "non_exist";
	}

	@Override
	public void write(MemberDTO memberDTO) {
		memberDAO.write(memberDTO);
	}

	@Override
	public MemberDTO modifyForm() {
		return memberDAO.modifyForm((String)httpSession.getAttribute("memId"));
	}

	@Override
	public void modify(MemberDTO memberDTO) {
		memberDAO.modify(memberDTO);
	}

	@Override
	public void delete(String id) {
		memberDAO.delete(id);
		httpSession.invalidate();
	}


}
