package edu.mzc.myboard.dto;

import edu.mzc.myboard.domain.board.Content;
import edu.mzc.myboard.domain.board.Title;
import edu.mzc.myboard.vo.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BoardDTO {
    private int seq;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Title title;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Content content;
    private Date regDate;
    private int cnt;
    private String userId;
    private String writer;

    public String getTitle() {
        return title.getTitle();
    }

    public void setTitle(String title) {
        this.title = new Title(title);
    }

    public String getContent() {
        return content.getContent();
    }

    public void setContent(String content) {
        this.content = new Content(content);
    }

    // 검색 관련 변수
    private String searchCondition;
    private String searchKeyword;
}
