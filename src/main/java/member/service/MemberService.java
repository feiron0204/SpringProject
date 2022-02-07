package member.service;

import java.util.Map;

import javax.servlet.http.HttpSession;



public interface MemberService {

	String login(Map<String,String> map,HttpSession httpSession);
}
