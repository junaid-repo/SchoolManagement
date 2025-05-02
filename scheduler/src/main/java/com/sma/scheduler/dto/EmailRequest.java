package com.sma.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String toEmailId;
    private String fromEmailId;
    private String receiptName;
    private String senderName;
    private String subject;
    private String content;
}
