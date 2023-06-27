package pl.edu.wat.model;

import java.time.Instant;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HashDTO {

    private String hash128;

    private String hash256;

    private String hash512;

    private Instant created;

    public HashDTO(String hash128, String hash256, String hash512) {
        this.hash128 = hash128;
        this.hash256 = hash256;
        this.hash512 = hash512;
    }
}
