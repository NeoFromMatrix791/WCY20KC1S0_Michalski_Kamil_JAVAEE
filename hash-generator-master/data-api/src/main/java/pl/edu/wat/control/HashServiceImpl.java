package pl.edu.wat.control;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import pl.edu.wat.entity.HashEntity;
import pl.edu.wat.model.HashDTO;
import pl.edu.wat.util.AssertHashModifiers;

@Stateless
public class HashServiceImpl implements HashService {

    @Inject
    private Datastore datastore;

    @Override
    public Collection<HashDTO> getAll() {
        return StreamSupport.stream(datastore.find(HashEntity.class).spliterator(), false)
                .map(entity -> new HashDTO(entity.getHash128(), entity.getHash256(), entity.getHash512(), entity.getCreated()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(final HashDTO dto) {
        AssertHashModifiers.arePropertiesValid(dto);

        HashEntity entity = new HashEntity();
        entity.setHash128(dto.getHash128());
        entity.setHash256(dto.getHash256());
        entity.setHash512(dto.getHash512());
        entity.setCreated(Instant.now());

        datastore.save(entity);
    }

    @Override
    public void remove(final HashDTO dto) {
        AssertHashModifiers.arePropertiesValid(dto);

        final Query<HashEntity> query = datastore.find(HashEntity.class)
                .field("hash128").equal(dto.getHash128())
                .field("hash256").equal(dto.getHash256())
                .field("hash512").equal(dto.getHash512());

        datastore.delete(query);
    }
}
