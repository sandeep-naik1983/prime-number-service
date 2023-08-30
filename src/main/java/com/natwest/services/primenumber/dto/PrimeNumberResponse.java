package com.natwest.services.primenumber.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Generated
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@ToString
@EqualsAndHashCode
public class PrimeNumberResponse implements Serializable {

    Integer initial;

    List<Integer> primes;

}
