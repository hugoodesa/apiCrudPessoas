package br.com.stapasssoli.apicrudpessoas.entity.impl;

import br.com.stapasssoli.apicrudpessoas.dto.impl.PessoaDTO;
import br.com.stapasssoli.apicrudpessoas.entity.IEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pessoas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Pessoa implements IEntity<PessoaDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy",locale = "pt-BR")
    private LocalDate nascimento;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Endereco> enderecos;

    @Override
    public PessoaDTO toDTO() {
        return PessoaDTO
                .builder()
                .nascimento(this.nascimento)
                .nome(this.nome)
                .enderecos(Objects.nonNull(this.enderecos) ? this.enderecos.stream().map(Endereco::toDTO).toList() : new ArrayList<>())
                .build();
    }

    public void atualizar(PessoaDTO pessoaDTO) {
        this.enderecos = pessoaDTO.toEntity().getEnderecos();
        this.nascimento = pessoaDTO.getNascimento();
        this.nome = pessoaDTO.getNome();
    }

    public void adicionarEndereco(Endereco endereco) {

        if(!endereco.getIsPrincipal()){
            ArrayList<Endereco> enderecos = new ArrayList<>(this.enderecos);
            enderecos.add(endereco);
            this.enderecos = enderecos;
            return ;
        }

        List<Endereco> enderecosModificado = this.enderecos.stream().map(ed -> {
            ed.removerPricipal();
            return ed;
        }).toList();

        ArrayList<Endereco> enderecos = new ArrayList<>(enderecosModificado);
        enderecos.add(endereco);

        this.enderecos = enderecos;
    }
}
