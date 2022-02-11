package guestbook.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Component
@Data
public class GuestbookDTO {
	private int seq;
	private String name;
	private String email;
	private String homepage;
	private String subject;
	private String content;
	@JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
	private Date logtime;
}
