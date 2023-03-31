package br.com.stapasssoli.apicrudpessoas.repository;

import br.com.stapasssoli.apicrudpessoas.entity.impl.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
