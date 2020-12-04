package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public DisciplinaVO(String nome, String codigo) {
        setNome(nome);
        setCodigo(codigo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.isEmpty())
            this.codigo = codigo.trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome.trim();
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da disciplina pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
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