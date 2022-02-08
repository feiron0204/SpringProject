package board.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardDTO {
		private int seq;
		private String id;
		private String name;
		private String email;
		private String subject;
		private String content;
		
		private int ref;
		private int lev;
		private int step;
		private int pseq;
		private int reply;
		
		private int hit;
		private String logtime; //DB에서 스트링으로 꺼내올꺼임
}
