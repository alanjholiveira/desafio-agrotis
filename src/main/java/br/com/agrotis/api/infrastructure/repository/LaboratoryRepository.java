package br.com.agrotis.api.infrastructure.repository;

import br.com.agrotis.api.domain.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

}
