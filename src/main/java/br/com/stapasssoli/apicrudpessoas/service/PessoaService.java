package br.com.stapasssoli.apicrudpessoas.service;

import br.com.stapasssoli.apicrudpessoas.dto.impl.PessoaDTO;
import br.com.stapasssoli.apicrudpessoas.entity.impl.Pessoa;
import br.com.stapasssoli.apicrudpessoas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public ResponseEntity cadastrarPessoa(PessoaDTO pessoaDTO){
        var pessoa = this.repository.save(pessoaDTO.toEntity());
        return ResponseEntity.ok(pessoa.toDTO());
    }

    public ResponseEntity deletarPessoa(Long id){
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity buscarPessoa(Long id){
        var pessoa = this.repository.getReferenceById(id);
        return ResponseEntity.ok(pessoa.toDTO());
    }

    public Page<PessoaDTO> listarPessoas(Pageable pageable){
        Page<Pessoa> pessoas = this.repository.findAll(pageable);
        return new PageImpl<>(pessoas.stream().map(Pessoa::toDTO).toList());
    }

    public ResponseEntity atualizarPessoa(Long id,PessoaDTO PessoaDTO){
        var pessoa = this.repository.getReferenceById(id);
        pessoa.atualizar(PessoaDTO);

        return ResponseEntity.ok(pessoa.toDTO());
    }

}
