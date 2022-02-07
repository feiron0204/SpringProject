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

	@Override
	public String login(Map<String,String> map, HttpSession httpSession) {
		MemberDTO memberDTO=(MemberDTO) memberDAO.login(map);
		if(memberDTO!=null) {
			httpSession.setAttribute("memName", memberDTO.getName());
			httpSession.setAttribute("memId", memberDTO.getId());
			httpSession.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
			return "ok";
		}else {
			return "fail";
		}
		
	}


}
