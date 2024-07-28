package sb.application;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank(message = "title must not be blank")
        String title,

        @NotBlank(message = "content must not be blank")
        String content
) {
}
