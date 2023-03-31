package br.com.stapasssoli.apicrudpessoas.service;

import br.com.stapasssoli.apicrudpessoas.dto.impl.EnderecoDTO;
import br.com.stapasssoli.apicrudpessoas.dto.impl.PessoaDTO;
import br.com.stapasssoli.apicrudpessoas.entity.impl.Endereco;
import br.com.stapasssoli.apicrudpessoas.entity.impl.Pessoa;
import br.com.stapasssoli.apicrudpessoas.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Autowired
    PessoaService pessoaService;

    @Transactional
    public ResponseEntity cadastrarEndereco(EnderecoDTO enderecoDTO,Long idPessoa){
        PessoaDTO pessoaDTO = (PessoaDTO) this.pessoaService.buscarPessoa(idPessoa).getBody();
        Pessoa pessoa = pessoaDTO.toEntity();
        pessoa.adicionarEndereco(enderecoDTO.toEntity());
        this.pessoaService.atualizarPessoa(idPessoa,pessoa.toDTO());

        return ResponseEntity.ok(pessoa.getEnderecos().stream().map(Endereco::toDTO).toList());
    }

}
