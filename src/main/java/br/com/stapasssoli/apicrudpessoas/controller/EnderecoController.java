package br.com.stapasssoli.apicrudpessoas.controller;

import br.com.stapasssoli.apicrudpessoas.dto.impl.EnderecoDTO;
import br.com.stapasssoli.apicrudpessoas.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping("/{idPessoa}")
    ResponseEntity cadastrarEndereco (@RequestBody EnderecoDTO enderecoDto,@PathVariable Long idPessoa){
        return this.service.cadastrarEndereco(enderecoDto,idPessoa);
    }

}
