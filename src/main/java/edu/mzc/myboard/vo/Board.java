package edu.mzc.myboard.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
public class Board {
    private final int seq;
    private final String title;
    private final User writer;
    private final String content;
    private final Date regDate;
    private final int cnt;
}
