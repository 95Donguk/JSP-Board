package edu.mzc.myboard.domain.user;

import lombok.Getter;

@Getter
public class Password {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 16;

    private final String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    private void validate(String password) {
        if (password == null || isBlank(password)) {
            throw new IllegalArgumentException("비밀번호를 입력하지 않았습니다.");
        }
        if (isInValidPasswordLength(password)) {
            throw new IllegalArgumentException("비밀번호를 8자 이상 입력해 주세요.");
        }
    }

    private boolean isInValidPasswordLength(String password) {
        return password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH;
    }

    private boolean isBlank(String password) {
        return password.trim().isEmpty();
    }
}
