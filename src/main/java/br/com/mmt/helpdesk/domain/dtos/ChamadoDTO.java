package br.com.mmt.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAbertura = LocalDateTime.now();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFechamento;

    @NotNull(message = "Campo prioridade é obrigatório")
    private Integer codigoPrioridade;

    private Integer codigoStatus;
    @NotNull(message = "Campo título é obrigatório")
    private String titulo;

    @NotNull(message = "Campo observação é obrigatório")
    private String observacao;

    private Integer idTecnico;

    private String nomeTecnico;

    private Integer idCliente;

    private String nomeCliente;

    public ChamadoDTO() {
        super();
        setCodigoStatus(0);
    }

//    public ChamadoDTO(Integer prioridade,
//                      String titulo,
//                      String observacao,
//                      Integer idCliente) {
//        this.prioridade = prioridade;
//        this.titulo = titulo;
//        this.observacao = observacao;
//        this.idCliente = idCliente;
//    }

    public ChamadoDTO(Integer id,
                      LocalDateTime dataAbertura,
                      LocalDateTime dataFechamento,
                      Integer prioridade,
                      Integer status,
                      String titulo,
                      String observacao,
                      Integer idTecnico,
                      String nomeTecnico,
                      Integer idCliente,
                      String nomeCliente) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.codigoPrioridade = prioridade;
        this.codigoStatus = status;
        this.titulo = titulo;
        this.observacao = observacao;
        this.idTecnico = idTecnico;
        this.nomeTecnico = nomeTecnico;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
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

    public Integer getCodigoPrioridade() {
        return codigoPrioridade;
    }

    public void setCodigoPrioridade(Integer codigoPrioridade) {
        this.codigoPrioridade = codigoPrioridade;
    }

    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(Integer codigoStatus) {
        this.codigoStatus = codigoStatus;
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

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
