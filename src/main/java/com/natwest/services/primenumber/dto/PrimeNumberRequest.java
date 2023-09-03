package com.natwest.services.primenumber.dto;

import com.natwest.services.primenumber.constant.AlgorithmType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class PrimeNumberRequest {
    Integer number;
    AlgorithmType algorithmType;
}
