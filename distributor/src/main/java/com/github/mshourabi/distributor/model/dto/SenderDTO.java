package com.github.mshourabi.distributor.model.dto;

import com.github.mshourabi.client.enums.Platform;
import com.github.mshourabi.distributor.model.entity.Sender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public class SenderDTO {


    /**
     *
     * @param id
     * @param platform
     * @param name
     * @param createdAt
     * @param lastModifiedAt
     */
    @Schema(name = "SenderInfo")
    public record Info(Long id, Platform platform, String name, boolean isActive, Instant createdAt, Instant lastModifiedAt) {

        public static Info map(Sender sender) {
            return new Info(
                    sender.getId(),
                    sender.getPlatform(),
                    sender.getName(),
                    sender.isActive(),
                    sender.getCreatedAt(),
                    sender.getLastModifiedAt());
        }
    }

    /**
     *
     * @param name
     * @param active
     */
    @Schema(name = "SenderCreatedRequest")
    public record CreateRequest(

            @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
            @NotBlank
            String name,
            @Schema(name = "active", requiredMode = Schema.RequiredMode.AUTO, defaultValue = "false")
            boolean active) {

        public static Sender map(CreateRequest createRequest) {
            Sender sender = new Sender();
            sender.setName(createRequest.name);
            sender.setActive(createRequest.active);
            return sender;
        }
    }

}
