package src.controller;

import src.model.BO.DisciplinaBO;
import src.model.BO.ProfessorBO;
import src.model.BO.ProvaBO;
import src.model.BO.QuestaoComAlternativasBO;
import src.model.VO.AlternativaVO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoSubjetivaVO;
import src.model.VO.QuestaoVO;

public class Teste {

    public static void main2(String[] args) {

        ProfessorVO professor = new ProfessorVO("helihelcio@ufersa.edu.br");
        professor.setSenha("valendo10");

        ProfessorBO professorBO = new ProfessorBO();
        System.out.println(professorBO.autenticar(professor) ? "Entrou" : "Não entrou");
        System.out.println(professor);

        DisciplinaVO disciplina = new DisciplinaVO("Redes de Computadores", "CC1234M");

        DisciplinaBO disciplinaBO = new DisciplinaBO();
        disciplinaBO.adicionar(disciplina, professor);

        QuestaoSubjetivaVO questao1 = new QuestaoSubjetivaVO(QuestaoVO.FACIL, "Em qual semestre estamos?", "2020.1");
        disciplinaBO.adicionar(disciplina, questao1);

        QuestaoComAlternativasVO questao2 = new QuestaoComAlternativasVO(QuestaoVO.FACIL, "Em qual ano estamos?");
        QuestaoComAlternativasBO questao2BO = new QuestaoComAlternativasBO();

        AlternativaVO alt1 = new AlternativaVO("2019", false);
        questao2BO.adicionar(questao2, alt1);

        AlternativaVO alt2 = new AlternativaVO("2020", true);
        questao2BO.adicionar(questao2, alt2);

        disciplinaBO.adicionar(disciplina, questao2);

        ProvaVO prova1 = new ProvaVO("2020.1 - Unidade 1 - Prova A");
        ProvaBO prova1BO = new ProvaBO();
        disciplinaBO.adicionar(disciplina, prova1);
        prova1BO.adicionar(prova1, questao1);
        prova1BO.adicionar(prova1, questao2);

        // System.out.println(prova1.getProvaRespondida());
    }

}
