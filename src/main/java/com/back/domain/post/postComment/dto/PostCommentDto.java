package com.back.domain.post.postComment.dto;

import com.back.domain.member.member.entity.Member;
import com.back.domain.post.postComment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String content,
        Member author
) {
    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreateDate(),
                postComment.getModifyDate(),
                postComment.getContent(),
                postComment.getAuthor()
        );
    }
}