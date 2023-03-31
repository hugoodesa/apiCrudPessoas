package br.com.stapasssoli.apicrudpessoas.repository;

import br.com.stapasssoli.apicrudpessoas.entity.impl.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
