package com.server2.demo.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseException extends RuntimeException{
    public final BaseResponseCode baseResponseCode;
}
