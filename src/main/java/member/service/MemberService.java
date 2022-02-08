package member.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import member.bean.MemberDTO;



public interface MemberService {

	public String login(Map<String,String> map);

	public void logout();

	public String checkId(String id);

	public void write(MemberDTO memberDTO);

	public MemberDTO modifyForm();

	public void modify(MemberDTO memberDTO);

	public void delete(String id);
}
