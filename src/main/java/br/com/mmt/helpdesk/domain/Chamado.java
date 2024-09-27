package br.com.mmt.helpdesk.domain;

import br.com.mmt.helpdesk.domain.enuns.Prioridade;
import br.com.mmt.helpdesk.domain.enuns.Status;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Chamado {

    private Integer id;
    private LocalDateTime dataAbertura = LocalDateTime.now();
    private LocalDateTime dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private Tecnico tecnico;
    private Cliente  cliente;

    public Chamado() {
        super();
    }

    public Chamado(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Prioridade prioridade, Status status, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.prioridade = prioridade;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chamado)) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(getId(), chamado.getId()) && Objects.equals(getDataAbertura(), chamado.getDataAbertura()) && Objects.equals(getDataFechamento(), chamado.getDataFechamento()) && getPrioridade() == chamado.getPrioridade() && getStatus() == chamado.getStatus() && Objects.equals(getTecnico(), chamado.getTecnico()) && Objects.equals(getCliente(), chamado.getCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataAbertura(), getDataFechamento(), getPrioridade(), getStatus(), getTecnico(), getCliente());
    }
}
