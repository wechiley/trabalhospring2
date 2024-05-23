package com.academify.model;

import jakarta.persistence.*;


import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "garconete")
public class Garconete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Matrícula é obrigatória")
    @Pattern(regexp = "\\d+", message = "Matrícula deve ser composta por caracteres numéricos")
    private String matricula;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @NotBlank(message = "RG é obrigatório")
    private String rg;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotNull(message = "Data de contratação é obrigatória")
    @PastOrPresent(message = "Data de contratação deve ser uma data válida e menor que a data atual")
    private Date dataContratacao;

    @NotBlank(message = "Status de trabalho é obrigatório")
    @Pattern(regexp = "ativo|licença-maternidade|férias|desligado", message = "Status de trabalho deve ser um dos valores: ativo, licença-maternidade, férias, desligado")
    private String statusTrabalho;

    @NotNull(message = "Salário é obrigatório")
    private Double salario;

    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getStatusTrabalho() {
        return statusTrabalho;
    }

    public void setStatusTrabalho(String statusTrabalho) {
        this.statusTrabalho = statusTrabalho;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }
}
