package edu.mzc.myboard.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Page<T> {
    // 한 페이지에 게시물 보여줄 갯수
    private static final int BOARD_VIEW_SIZE = 5;
    // 페이지 범위 수
    private static final int PAGE_RANGE_SIZE = 5;

    // 현재 페이지 번호
    private int pageNumber;

    // 페이지 블럭 시작 번호
    private int startPageNum;

    // 페이지 블럭 끝 번호
    private int endPageNum;

    // 총 페이지 수
    private int totalPageCount;

    // 총 게시물 수
    private int totalBoardCount;

    private int sqlLimitStart;
    private int sqlLimitEnd;

    @Setter
    private List<T> boards;

    public Page(int pageNumber, int totalBoardCount) {
        this.pageNumber = pageNumber;
        this.totalBoardCount = totalBoardCount;
        this.totalPageCount = (int) Math.ceil((double) totalBoardCount / BOARD_VIEW_SIZE);
        int range = (this.pageNumber % PAGE_RANGE_SIZE == 0) ?
                this.pageNumber / PAGE_RANGE_SIZE : (this.pageNumber / PAGE_RANGE_SIZE) + 1;
        this.startPageNum = (range - 1) * PAGE_RANGE_SIZE + 1;
        this.endPageNum = this.startPageNum + PAGE_RANGE_SIZE - 1;
        checkOverEndPage();
        this.sqlLimitStart = (this.pageNumber - 1) * BOARD_VIEW_SIZE;
        this.sqlLimitEnd = BOARD_VIEW_SIZE;
    }

    private void checkOverEndPage() {
        if (this.endPageNum >= this.totalPageCount) {
            this.endPageNum = this.totalPageCount;
        }
    }


}
