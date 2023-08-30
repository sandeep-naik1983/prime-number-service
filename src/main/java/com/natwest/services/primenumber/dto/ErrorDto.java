package com.natwest.services.primenumber.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class ErrorDto {

    String message;

    Integer status;

    Date timeStamp;
}
