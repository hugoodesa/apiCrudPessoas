package br.com.stapasssoli.apicrudpessoas;

import br.com.stapasssoli.apicrudpessoas.dto.impl.EnderecoDTO;
import br.com.stapasssoli.apicrudpessoas.dto.impl.PessoaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiCrudPessoasApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void deveCadastrarUmaPessoa() throws Exception {

        PessoaDTO pessoaDTO = PessoaDTO
                .builder()
                .nome("Hugo Luiz Stapasoli de Sá")
                .nascimento(LocalDate.of(1996, 9, 23))
                .enderecos(List.of(EnderecoDTO
                        .builder()
                        .CEP("88745000")
                        .numero("73")
                        .isPrincipal(false)
                        .logradouro("Rua Paulio Alexandre da Silva")
                        .cidade("Capivari de Baixo")
                        .build()))
                .build();

        var json = this.mapper.writeValueAsString(pessoaDTO);

        URI uri = new URI("/pessoa");

        String response = mockMvc.perform(get(uri).contentType(APPLICATION_JSON).content(json))
                .andExpect(status().is(OK.value())).andReturn().getResponse().getContentAsString();

        PessoaDTO pessoaDTO1 = mapper.readValue(response, PessoaDTO.class);

        assertTrue(pessoaDTO1.getNome().equals("Hugo Luiz Stapassoli de Sá"));

    }

    @Test
    void deveBuscarUmaPessoaPorID() {
    }

    @Test
    void deveBuscarTodasAsPessoas() {
    }

    @Test
    void deveDeletarUmaPessoa() {
    }

    @Test
    void deveAtualizarUmaPessoa() {
    }

}
