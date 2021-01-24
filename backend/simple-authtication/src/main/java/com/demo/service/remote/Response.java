package com.demo.service.remote;

import com.demo.entity.User;
import lombok.Data;

@Data
public class Response {
    private Integer status;
    private User user;
}
