package pl.edu.wat.entity;

import java.time.Instant;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("WCY20KC1S0_Michalski_hash")
@Getter
@Setter
@EqualsAndHashCode
public class HashEntity {

    @Id
    private ObjectId id;

    private String hash128;

    private String hash256;

    private String hash512;

    private Instant created;
}
