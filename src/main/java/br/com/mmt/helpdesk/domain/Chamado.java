package br.com.mmt.helpdesk.domain;

import br.com.mmt.helpdesk.domain.enuns.Prioridade;
import br.com.mmt.helpdesk.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "CHAMADO")
@Table(name = "CHAMADO", schema = "helpdesk")
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "DATA_ABERTURA")
    private LocalDateTime dataAbertura = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "DATA_FECHAMENTO")
    private LocalDateTime dataFechamento = LocalDateTime.now();

    @Column(name = "PRIORIDADE")
    private Prioridade prioridade;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "OBSERVACAO")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "TECNICO_ID")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente  cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, LocalDateTime dataFechamento, Prioridade prioridade, Status status, String titulo, String observacao, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.dataAbertura = LocalDateTime.now();
        this.dataFechamento = dataFechamento;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacao = observacao;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamado)) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(getId(), chamado.getId()) && Objects.equals(getDataAbertura(), chamado.getDataAbertura()) && Objects.equals(getDataFechamento(), chamado.getDataFechamento()) && getPrioridade() == chamado.getPrioridade() && getStatus() == chamado.getStatus() && Objects.equals(getTitulo(), chamado.getTitulo()) && Objects.equals(getObservacao(), chamado.getObservacao()) && Objects.equals(getTecnico(), chamado.getTecnico()) && Objects.equals(getCliente(), chamado.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataAbertura(), getDataFechamento(), getPrioridade(), getStatus(), getTitulo(), getObservacao(), getTecnico(), getCliente());
    }
}
