package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.model.BO.DisciplinaBO;

public abstract class QuestaoVO {
    private long id;
    private String enunciado = "";
    private DisciplinaVO disciplina = new DisciplinaVO();
    private int dificuldade;
    private Calendar dataCriacao;
    private List<String> assuntos = new ArrayList<String>();
    private List<ProvaVO> provas = new ArrayList<ProvaVO>();

    public static final int FACIL = 0;
    public static final int MEDIA = 1;
    public static final int DIFICIL = 2;

    public QuestaoVO() {

    }

    public QuestaoVO(int dificuldade, String enunciado) {
        setDificuldade(dificuldade);
        setEnunciado(enunciado);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public QuestaoVO(int dificuldade, List<String> assuntos, String enunciado) {
        setDificuldade(dificuldade);
        setEnunciado(enunciado);
        setAssuntos(assuntos);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        if (enunciado != null && !enunciado.isEmpty())
            this.enunciado = enunciado.trim();
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        if (disciplina != null) {
            // Atualizar a disciplina antiga
            DisciplinaBO antiga = new DisciplinaBO();
            antiga.remover(this.disciplina, this);

            // Atualizar a questão
            this.disciplina = disciplina;

            // Atualizar a nova disciplina
            DisciplinaBO nova = new DisciplinaBO();
            nova.adicionar(disciplina, this);
        }
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        if (dificuldade >= 0)
            this.dificuldade = dificuldade;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da questão pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<String> assuntos) {
        this.assuntos = assuntos;
    }

    public List<ProvaVO> getProvas() {
        return provas;
    }

    public void setProvas(List<ProvaVO> provas) {
        this.provas = provas;
    }

    public String getDificuldadeRotulo() {
        String saida;

        switch (dificuldade) {
            case FACIL:
                saida = "Fácil";
                break;

            case MEDIA:
                saida = "Média";
                break;

            case DIFICIL:
                saida = "Difícil";
                break;

            default:
                saida = "Nível desconhecido";
                break;
        }

        return saida;
    }

    @Override
    public String toString() {
        return enunciado;
    }

    public String getQuestaoRespondida() {
        String saida = enunciado + " [" + getDificuldadeRotulo().toLowerCase() + "]";
        return saida;
    }

}
