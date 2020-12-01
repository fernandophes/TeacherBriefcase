package controller;

import model.BO.DisciplinaBO;
import model.BO.ProvaBO;
import model.BO.QuestaoComAlternativasBO;
import model.DAO.BaseDAO;
import model.VO.AlternativaVO;
import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;
import model.VO.ProvaVO;
import model.VO.QuestaoComAlternativasVO;
import model.VO.QuestaoSubjetivaVO;
import model.VO.QuestaoVO;

public class Teste {

    public static void main(String[] args) {

        System.out.println(BaseDAO.getConnection());

        ProfessorVO professor = new ProfessorVO("Helihelcio", "helihelcio@ufersa.edu.br", "valendo10");

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

        System.out.println(prova1.getProvaRespondida());
    }

}
