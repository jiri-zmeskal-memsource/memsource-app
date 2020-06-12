package cz.denk.memsource.repository;

import cz.denk.memsource.repository.entity.MemsourceCredentials;
import org.springframework.data.repository.CrudRepository;

public interface CredentialsRepository extends CrudRepository<MemsourceCredentials, Long> {

    MemsourceCredentials findByUsername(String username);

}
