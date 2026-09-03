package com.github.mshourabi.client.telegramsender.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class MessageDTO {

    /**
     *
     * @param content
     * @param receiverIdentifier
     * @param senderIds
     */
    @Schema(name = "MessageSendRequest")
    public record SendRequest(

            @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED, description = "Message's contents.")
            @NotBlank
            String content,

            @Schema(name = "receiverIdentifier", requiredMode = Schema.RequiredMode.REQUIRED, description = "Receiver's Identifier depend on platform.")
            @NotBlank
            String receiverIdentifier,

            @Schema(name = "senderId", requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of senders, the first one has most priority.")
            @NotEmpty
            List<Long> senderIds
    ) {
    }


    public record SendResponse(String Status
    ) {
    }
}
