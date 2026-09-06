package com.github.mshourabi.client.tekegramagent.dto;

import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public class SyncMessageDTO {


    /**
     *
     * @param id
     * @param referenceId
     * @param content
     * @param receiverIdentifier
     * @param status
     * @param createdAt
     * @param lastModifiedAt
     */
    @Schema(name = "MessageInfo")
    public record Info(
            Long id,
            String referenceId,
            String content,
            String receiverIdentifier,
            MessageStatus status,
            SendingStrategy sendingStrategy,
            Instant createdAt,
            Instant lastModifiedAt) {

    }


    /**
     *
     * @param content
     * @param receiverIdentifier
     */
    @Schema(name = "MessageSendRequest")
    public record SendDirectRequest(

            @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED, description = "Message's contents.")
            @NotBlank
            String content,

            @Schema(name = "receiverIdentifier", requiredMode = Schema.RequiredMode.REQUIRED, description = "Receiver's Identifier depend on platform.")
            @NotBlank
            String receiverIdentifier,

            @Schema(name = "referenceId", requiredMode = Schema.RequiredMode.REQUIRED, description = "Reference Id")
            @NotBlank
            String referenceId
    ) {
    }


    public record SendDirectResponse(String id, String Status, String Content, String receiverIdentifier, String referenceId) {
    }
}
