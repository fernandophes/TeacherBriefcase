package controller;

import model.BO.DisciplinaBO;
import model.BO.ProvaBO;
import model.BO.QuestaoComAlternativasBO;
import model.VO.AlternativaVO;
import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;
import model.VO.ProvaVO;
import model.VO.QuestaoComAlternativasVO;
import model.VO.QuestaoSubjetivaVO;
import model.VO.QuestaoVO;

public class Teste {
    
    public static void main (String[] args) {
        ProfessorVO prof = new ProfessorVO();
        prof.setNome("Helihelcio");
        prof.setEmail("helihelcio@ufersa.edu.br");
        prof.setSenha("valendo10");

        DisciplinaVO disciplina = new DisciplinaVO();
        disciplina.setCodigo("CC1234M");
        disciplina.setNome("Redes de Computadores");

        QuestaoSubjetivaVO questao1 = new QuestaoSubjetivaVO();
        questao1.setDisciplina(disciplina);
        questao1.setDificuldade(QuestaoVO.FACIL);
        questao1.setEnunciado("Em qual semestre estamos?");
        questao1.setGabarito("2020.1");

        QuestaoComAlternativasVO questao2 = new QuestaoComAlternativasVO();
        questao2.setDisciplina(disciplina);
        questao2.setDificuldade(QuestaoVO.FACIL);
        questao2.setEnunciado("Qual destes termos NÃO faz parte do conceito de OO?");

        AlternativaVO alternativa1 = new AlternativaVO();
        alternativa1.setTexto("Classes");
        alternativa1.setVerdadeira(false);

        AlternativaVO alternativa2 = new AlternativaVO();
        alternativa2.setTexto("Amarelo");
        alternativa2.setVerdadeira(true);

        AlternativaVO alternativa3 = new AlternativaVO();
        alternativa3.setTexto("Objetos");
        alternativa3.setVerdadeira(false);

        AlternativaVO alternativa4 = new AlternativaVO();
        alternativa4.setTexto("Herança");
        alternativa4.setVerdadeira(false);

        ProvaVO prova = new ProvaVO();
        prova.setTitulo("Avaliação individual - Unidade I - 2020.1");

        DisciplinaBO disciplinaBO = new DisciplinaBO();
        disciplinaBO.adicionar(disciplina, prof);
        disciplinaBO.adicionar(disciplina, questao1);
        disciplinaBO.adicionar(disciplina, questao2);
        disciplinaBO.adicionar(disciplina, prova);

        QuestaoComAlternativasBO questao2BO = new QuestaoComAlternativasBO();
        questao2BO.adicionar(questao2, alternativa1);
        questao2BO.adicionar(questao2, alternativa2);
        questao2BO.adicionar(questao2, alternativa3);
        questao2BO.adicionar(questao2, alternativa4);

        ProvaBO provaBO = new ProvaBO();
        provaBO.adicionar(prova, questao1);
        provaBO.adicionar(prova, questao2);

        System.out.println(prova);
    }

}
