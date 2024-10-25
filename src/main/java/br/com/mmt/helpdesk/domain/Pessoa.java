package br.com.mmt.helpdesk.domain;

import br.com.mmt.helpdesk.domain.enuns.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "PESSOA")
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @Column(name = "NOME")
    protected String nome;

    @CPF(message = "CPF Inválido")
    @Column(name = "CPF", unique = true)
    protected String cpf;

    @Column(name = "EMAIL", unique = true)
    protected String email;

    @Column(name = "SENHA")
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIL")
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_CRIACAO")
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
//        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
//        addPerfil(Perfil.CLIENTE);
    }

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

    public void setPerfis(Set<Perfil>  perfis) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getId(), pessoa.getId()) && Objects.equals(getNome(), pessoa.getNome()) && Objects.equals(getCpf(), pessoa.getCpf()) && Objects.equals(getEmail(), pessoa.getEmail()) && Objects.equals(getSenha(), pessoa.getSenha()) && Objects.equals(getPerfis(), pessoa.getPerfis()) && Objects.equals(getDataCriacao(), pessoa.getDataCriacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf(), getEmail(), getSenha(), getPerfis(), getDataCriacao());
    }
}
