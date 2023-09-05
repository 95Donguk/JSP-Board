package edu.mzc.myboard.domain.user;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Id {
    private static final int MIN_ID_LENGTH = 4;
    private static final int MAX_ID_LENGTH = 16;

    private final String id;

    public Id(String id) {
        validate(id);
        this.id = id;
    }

    private void validate(String id) {
        if (id == null || isBlank(id)) {
            throw new IllegalArgumentException("아이디를 입력하지 않았습니다.");
        }
        if (isInValidId(id)) {
            throw new IllegalArgumentException("아이디는 영어와 숫자만 가능합니다.");
        }
        if (isInValidIdLength(id)) {
            throw new IllegalArgumentException("아이디는 4자 이상 16자 이하으로 작성해주세요.");
        }
    }

    private boolean isBlank(String id) {
        return id.trim().isEmpty();
    }

    private boolean isInValidIdLength(String id) {
        return id.length() < MIN_ID_LENGTH || id.length() > MAX_ID_LENGTH;
    }

    private boolean isInValidId(String id) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        return !matcher.matches();
    }
}
