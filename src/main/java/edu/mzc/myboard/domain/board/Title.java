package edu.mzc.myboard.domain.board;

import lombok.Getter;

@Getter
public class Title {
    private final String title;

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        if (title == null || isBlank(title)) {
            throw new IllegalArgumentException("제목이 없습니다.");
        }
    }

    private static boolean isBlank(String title) {
        return title.trim().isEmpty();
    }
}
