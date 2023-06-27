package pl.edu.wat.control;

import java.util.Collection;

import pl.edu.wat.model.HashDTO;

public interface HashService {

    Collection<HashDTO> getAll();

    void save(final HashDTO dto);

    void remove(final HashDTO dto);
}
