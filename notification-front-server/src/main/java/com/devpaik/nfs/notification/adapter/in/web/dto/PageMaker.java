package com.devpaik.nfs.notification.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
public class PageMaker implements Serializable {

    @Schema(description = "현재 페이지")
    private int page;

    @Schema(description = "노출 개수")
    private int size;

    @Schema(description = "전체 조회 수")
    private long totalCount;

    @Schema(description = "노출 개수만큼의 마지막 페이지")
    private int endPage;

    @Schema(description = "이전페이지 이동 가능 여부")
    private boolean prev;

    @Schema(description = "다음페이지 이동 가능 여부")
    private boolean next;

    public PageMaker() {
        this(1,10,0);
    }

    public PageMaker(int page, int size, long totalCount) {
        this.page = page == 0 ? 1 : page;

        this.totalCount = totalCount;

        this.size = 10; //default 10

        if (size > 10) {
            this.size = size;
        }

        /* 마지막 페이지 */
        this.endPage = (int) Math.ceil(totalCount * 1.0 / size);

        if (this.endPage <= 0) {
            this.endPage = 1;
        }

        /* 시작 페이지(startPage)값이 1보다 큰 경우 true */
        this.prev = this.page > 1;

        /* 마지막 페이지(endPage)값이 1보다 큰 경우 true */
        this.next = this.page < endPage;
    }
}