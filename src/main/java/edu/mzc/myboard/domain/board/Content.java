package edu.mzc.myboard.domain.board;

import lombok.Getter;

@Getter
public class Content {
    private final String content;

    public Content(String content) {
        validate(content);
        this.content = content;
    }

    private void validate(String content) {
        if (content == null || isBlank(content)) {
            throw new IllegalArgumentException("내용이 없습니다.");
        }
    }

    private static boolean isBlank(String content) {
        return content.trim().isEmpty();
    }
}
