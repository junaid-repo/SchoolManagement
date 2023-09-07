package com.management.school.smusers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseOutput {
    String username;
    String returnMsg;
    Long returnCode;
}
