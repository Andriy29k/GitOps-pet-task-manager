package com.task_manager.app.dto;

import java.time.Instant;

public record CommentResponseDto(long id,
                                 String text,
                                 long taskId,
                                 long authorId,
                                 String authorUsername,
                                 Instant createdAt
                                 ) {

}
