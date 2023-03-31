package br.com.stapasssoli.apicrudpessoas.entity.impl;

import br.com.stapasssoli.apicrudpessoas.dto.impl.EnderecoDTO;
import br.com.stapasssoli.apicrudpessoas.entity.IEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enderecos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Endereco implements IEntity<EnderecoDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String CEP;
    private String numero;
    private String cidade;
    private Boolean isPrincipal;

    public void removerPricipal(){
        isPrincipal = false;
    }

    public void setarPricipal(){
        isPrincipal = true;
    }

    @Override
    public EnderecoDTO toDTO() {
        return EnderecoDTO
                .builder()
                .logradouro(this.logradouro)
                .CEP(this.CEP)
                .cidade(this.cidade)
                .isPrincipal(this.isPrincipal)
                .numero(this.numero)
                .build();
    }
}
