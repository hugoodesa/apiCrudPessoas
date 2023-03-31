package br.com.stapasssoli.apicrudpessoas.dto.impl;

import br.com.stapasssoli.apicrudpessoas.dto.IDTO;
import br.com.stapasssoli.apicrudpessoas.entity.impl.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoDTO implements IDTO<Endereco> {

    private String logradouro;
    private String CEP;
    private String numero;
    private String cidade;
    private Boolean isPrincipal;

    public Endereco toEntity() {
        return Endereco
                .builder()
                .logradouro(this.logradouro)
                .CEP(this.CEP)
                .cidade(this.cidade)
                .isPrincipal(this.isPrincipal)
                .numero(this.numero)
                .build();
    }


}
