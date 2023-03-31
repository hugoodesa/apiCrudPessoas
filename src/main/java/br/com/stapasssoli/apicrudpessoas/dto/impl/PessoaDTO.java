package br.com.stapasssoli.apicrudpessoas.dto.impl;

import br.com.stapasssoli.apicrudpessoas.dto.IDTO;
import br.com.stapasssoli.apicrudpessoas.entity.impl.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaDTO implements IDTO<Pessoa> {

    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy",locale = "pt-BR")
    private LocalDate nascimento;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<EnderecoDTO> enderecos;


    @Override
    public Pessoa toEntity() {
        return Pessoa
                .builder()
                .nome(this.nome)
                .nascimento(this.nascimento)
                .enderecos(Objects.nonNull(this.enderecos) ? this.enderecos.stream().map(EnderecoDTO::toEntity).toList() : new ArrayList<>())
                .build();
    }
}
