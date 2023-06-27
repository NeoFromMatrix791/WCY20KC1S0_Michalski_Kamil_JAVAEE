package pl.edu.wat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class HashResult {

    private final String hash128;

    private final String hash256;

    private final String hash512;
}
