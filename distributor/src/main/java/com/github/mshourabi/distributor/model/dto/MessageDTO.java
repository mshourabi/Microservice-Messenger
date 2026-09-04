package com.github.mshourabi.distributor.model.dto;

import com.github.mshourabi.client.enums.MessageStatus;
import com.github.mshourabi.client.enums.SendingStrategy;
import com.github.mshourabi.distributor.model.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.List;

public class MessageDTO {

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

        public static Info map(Message message){
            return new Info(
                    message.getId(),
                    message.getReferenceId(),
                    message.getContent(),
                    message.getReceiverIdentifier(),
                    message.getStatus(),
                    message.getSendingStrategy(),
                    message.getCreatedAt(),
                    message.getLastModifiedAt());
        }
    }


    /**
     *
     * @param content
     * @param receiverIdentifier
     * @param senderIds
     */
    @Schema(name = "MessageCreatedRequest")
    public record CreateRequest(

            @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED, description = "Message's contents.")
            @NotBlank
            String content,

            @Schema(name = "receiverIdentifier", requiredMode = Schema.RequiredMode.REQUIRED, description = "Receiver's Identifier depend on platform.")
            @NotBlank
            String receiverIdentifier,

            @Schema(name = "sendingStrategy", defaultValue = "IN_QUEUE", description = "Send Message Strategy.")
            SendingStrategy sendingStrategy,

            @Schema(name = "senderId", requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of senders, the first one has most priority.")
            @NotEmpty
            List<Long> senderIds
            ){


        public CreateRequest {
            sendingStrategy = sendingStrategy != null ? sendingStrategy: SendingStrategy.IN_ORDER;
        }


        /**
         *
         * @param createRequest
         * @return
         */
        public static Message map(CreateRequest createRequest){
            return new Message(createRequest.content, createRequest.receiverIdentifier);
        }

    }
}
