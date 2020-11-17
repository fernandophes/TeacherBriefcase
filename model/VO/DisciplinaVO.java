package model.VO;

import java.util.Calendar;

public class DisciplinaVO {
    private String codigo;
    private String nome;
    private Calendar dataCriacao;
    private ProfessorVO[] professores;
    private AssuntoVO[] assuntos;

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        if (dataCriacao != null)
            this.dataCriacao = dataCriacao;
    }

    public ProfessorVO[] getProfessores() {
        return professores;
    }

    public void setProfessores(ProfessorVO[] professores) {
        this.professores = professores;
    }

    public AssuntoVO[] getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(AssuntoVO[] assuntos) {
        this.assuntos = assuntos;
    }
}