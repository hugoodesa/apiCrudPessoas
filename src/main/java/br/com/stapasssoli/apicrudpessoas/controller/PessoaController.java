package br.com.stapasssoli.apicrudpessoas.controller;

import br.com.stapasssoli.apicrudpessoas.service.PessoaService;
import br.com.stapasssoli.apicrudpessoas.dto.impl.PessoaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @PostMapping
    public ResponseEntity cadastrarPessoa (@RequestBody @Valid PessoaDTO pessoaDTO){
        return this.service.cadastrarPessoa(pessoaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPessoa (@PathVariable Long id){
        return this.service.buscarPessoa(id);
    }

    @GetMapping
    public Page buscarPessoas (@PageableDefault(size = 5,sort = {"nome"},direction = Sort.Direction.ASC) Pageable pageable){
        return this.service.listarPessoas(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPessoa(@PathVariable Long id){
        return this.service.deletarPessoa(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarPessoa (@PathVariable Long id, @RequestBody PessoaDTO dto){
        return this.service.atualizarPessoa(id,dto);
    }

}
