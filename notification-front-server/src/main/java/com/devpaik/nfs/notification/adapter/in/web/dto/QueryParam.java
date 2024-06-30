package com.devpaik.nfs.notification.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record QueryParam(
        @Schema(description = "페이지 요청 번호", example = "1")
        @Min(value = 1, message = "최소 페이지 요청 번호는 1 이상입니다.")
        Integer page,

        @Schema(description = "목록 요청 갯수", example = "10")
        @Min(value = 10, message = "최소 목록 요청 수는 10건 이상 입니다.")
        @Max(value = 100, message = "최대 목록 요청 수는 100건 이하 입니다.")
        Integer limit
) {
}
