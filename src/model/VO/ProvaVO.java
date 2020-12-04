package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ProvaVO {
    private long id;
    private DisciplinaVO disciplina = new DisciplinaVO();
    private String titulo = "";
    private Calendar dataCriacao = Calendar.getInstance();
    private List<QuestaoVO> questoes = new ArrayList<QuestaoVO>();

    public ProvaVO() {

    }

    public ProvaVO(String titulo) {
        setTitulo(titulo);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        if (disciplina != null)
            this.disciplina = disciplina;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isEmpty())
            this.titulo = titulo.trim();
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da prova pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public List<QuestaoVO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoVO> questoes) {
        this.questoes = questoes;
    }

    @Override
    public String toString() {
        String saida = titulo + "\n> " + disciplina + " <";
        List<QuestaoVO> questoes = getQuestoes();
        int ordem = 1;

        // Listar as questões
        Iterator<QuestaoVO> questoesIt = questoes.iterator();
        while (questoesIt.hasNext()) {
            saida += "\n\n" + ordem++ + ". " + questoesIt.next();
        }

        return saida;
    }
    
    public String getProvaRespondida() {
        String saida = "PROVA RESPONDIDA\n" + titulo + "\n> " + disciplina + " <";
        List<QuestaoVO> questoes = getQuestoes();
        int ordem = 1;

        // Listar as questões
        Iterator<QuestaoVO> questoesIt = questoes.iterator();
        while (questoesIt.hasNext()) {
            saida += "\n\n" + ordem++ + ". " + questoesIt.next().getQuestaoRespondida();
        }

        return saida;
    }
    
}
