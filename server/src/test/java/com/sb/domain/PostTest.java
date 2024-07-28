package com.sb.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.sb.domain.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    @DisplayName("게시물을 생성한다.")
    void create() {
        assertThatCode(() -> new Post("제목", "내용"))
                .doesNotThrowAnyException();
    }
}
