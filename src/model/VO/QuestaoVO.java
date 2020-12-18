package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.OperationException;
import src.model.QuestaoDificuldade;

public abstract class QuestaoVO extends BaseVO {

    private String enunciado = "";
    private DisciplinaVO disciplina = new DisciplinaVO();
    private QuestaoDificuldade dificuldade;
    private Calendar dataCriacao = Calendar.getInstance();
    private List<String> assuntos = new ArrayList<String>();

    public QuestaoVO() {

    }

    public QuestaoVO(QuestaoDificuldade dificuldade, String enunciado) throws OperationException {
        setDificuldade(dificuldade);
        setEnunciado(enunciado);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws OperationException {
        if (id > 0)
            this.id = id;
        else
            throw new OperationException("O id precisa ser maior que zero");
    }

    public QuestaoVO(QuestaoDificuldade dificuldade, List<String> assuntos, String enunciado) throws OperationException {
        setDificuldade(dificuldade);
        setEnunciado(enunciado);
        setAssuntos(assuntos);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) throws OperationException {
        if (enunciado != null && !enunciado.isEmpty())
            this.enunciado = enunciado.trim();
        else
            throw new OperationException("O enunciado não pode ficar em branco");
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        this.disciplina = disciplina;
    }

    public QuestaoDificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(QuestaoDificuldade dificuldade) {
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
