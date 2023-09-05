package edu.mzc.myboard.domain.user;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Name {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || isBlank(name)) {
            throw new IllegalArgumentException("이름을 입력하지 않았습니다.");
        }
        if (isInValidName(name)) {
            throw new IllegalArgumentException("이름은 한글과 영어만 입력이 가능합니다.");
        }
        if (isMixLanguage(name)) {
            throw new IllegalArgumentException("이름은 한글와 영어롤 혼합해서 입력할 수 없습니다.");
        }
        if (isInValidNameLength(name)) {
            throw new IllegalArgumentException("이름은 2자 이상 50자 이하만 입력이 가능합니다");
        }
    }

    private boolean isMixLanguage(String name) {
        String korRegex = "^[가-힣]+$";
        Pattern korPattern = Pattern.compile(korRegex);
        Matcher korMatcher = korPattern.matcher(name);
        String engRegex = "^[a-zA-Z]+$";
        Pattern engPattern = Pattern.compile(engRegex);
        Matcher engMatcher = engPattern.matcher(name);
        return !(korMatcher.matches() || engMatcher.matches());
    }

    private boolean isInValidNameLength(String name) {
        return name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH;
    }

    private boolean isBlank(String name) {
        return name.trim().isEmpty();
    }

    private boolean isInValidName(String name) {
        String regex = "^[가-힣a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return !matcher.matches();
    }
}
