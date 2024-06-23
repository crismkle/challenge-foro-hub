package tech.challenge.foroHub.domain.topico;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {


    List<Topico> findByTitulo(String titulo);

    @Query("SELECT t FROM Topico t ORDER BY t.fechacreacion ASC")
    Page<Topico> listarTopicos(Pageable paginacion);

}
