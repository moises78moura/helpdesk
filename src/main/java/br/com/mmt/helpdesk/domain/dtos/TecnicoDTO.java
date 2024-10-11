package br.com.mmt.helpdesk.domain.dtos;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.enuns.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO() {
        super();
    }

//    public TecnicoDTO(Tecnico tecnico) {
//        this.id = tecnico.getId();
//        this.nome = tecnico.getNome();
//        this.cpf = tecnico.getCpf();
//        this.email = tecnico.getEmail();
//        this.senha = tecnico.getSenha();
//        this.perfis = tecnico.getPerfis().stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
//        this.dataCriacao = tecnico.getDataCriacao();
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(perfil -> Perfil.toEnum(perfil)).collect(Collectors.toSet());
    }
    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis.stream().map(perfil -> perfil.getCodigo()).collect(Collectors.toSet());
    }
    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
