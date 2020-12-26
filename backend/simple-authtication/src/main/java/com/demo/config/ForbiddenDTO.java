package com.demo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 系统认证若未通过的标准 Forbidden Object
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForbiddenDTO {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
