package com.blog.pojo.dto;

import lombok.Data;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

@Data
public class CommentReportDto {
    private Long commentId;

    private String reason;

    private String description;
}
