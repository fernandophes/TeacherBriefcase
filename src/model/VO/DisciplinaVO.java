package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.AuthenticationException;

public class DisciplinaVO {
    private long id;
    private String codigo = "";
    private String nome = "";
    private Calendar dataCriacao = Calendar.getInstance();
    private List<ProfessorVO> professores = new ArrayList<ProfessorVO>();
    private List<String> assuntos = new ArrayList<String>();
    private List<QuestaoVO> questoes = new ArrayList<QuestaoVO>();
    private List<ProvaVO> provas = new ArrayList<ProvaVO>();

    public DisciplinaVO() {

    }

    public DisciplinaVO(String nome, String codigo) throws AuthenticationException {
        setNome(nome);
        setCodigo(codigo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws AuthenticationException {
        if (id > 0)
            this.id = id;
        else
            throw new AuthenticationException("O id precisa ser maior que zero");
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) throws AuthenticationException {
        if (codigo != null && !codigo.isEmpty())
            this.codigo = codigo.trim();
        else
            throw new AuthenticationException("O código não pode ficar em branco");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws AuthenticationException {
        if (nome != null && !nome.isEmpty())
            this.nome = nome.trim();
        else
            throw new AuthenticationException("O nome não pode ficar em branco");
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) throws AuthenticationException {
        if (dataCriacao != null)
            if (dataCriacao.compareTo(Calendar.getInstance()) <= 0)
                this.dataCriacao = dataCriacao;
            else
                throw new AuthenticationException("A data de criação não pode ser futura");
        else
            throw new AuthenticationException("A data de criação não pode ficar em branco");
    }

    public List<ProfessorVO> getProfessores() {
        return professores;
    }

    public void setProfessores(List<ProfessorVO> professores) {
        this.professores = professores;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }

    public List<QuestaoVO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoVO> questoes) {
        this.questoes = questoes;
    }

    public List<ProvaVO> getProvas() {
        return provas;
    }

    public void setProvas(List<ProvaVO> provas) {
        this.provas = provas;
    }

    @Override
    public String toString() {
        String saida = nome + " (" + codigo + ")";
        return saida;
    }

}