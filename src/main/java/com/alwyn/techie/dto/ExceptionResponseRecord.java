package com.alwyn.techie.dto;

import java.time.LocalDateTime;

public record ExceptionResponseRecord(LocalDateTime time, String message, String errorCode) {

}
