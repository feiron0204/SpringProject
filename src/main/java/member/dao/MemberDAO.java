package member.dao;

import java.util.Map;

import member.bean.MemberDTO;

public interface MemberDAO {
	public MemberDTO login(Map<String, String> map);

	public MemberDTO checkId(String id);

	public void write(MemberDTO memberDTO);

	public MemberDTO modifyForm(String id);

	public void modify(MemberDTO memberDTO);

	public void delete(String id);
}
