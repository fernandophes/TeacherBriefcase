package controller;

import model.BO.DisciplinaBO;
import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;

public class Teste {
    
    public static void main (String[] args) {
        ProfessorVO professor = new ProfessorVO();
        professor.setNome("Helihelcio");
        professor.setEmail("helihelcio@ufersa.edu.br");
        professor.setSenha("valendo10");

        DisciplinaVO disciplina = new DisciplinaVO();
        disciplina.setCodigo("CC1234M");
        disciplina.setNome("Redes de Computadores");

        DisciplinaBO disciplinaBO = new DisciplinaBO();
        disciplinaBO.adicionar(disciplina, professor);

        System.out.println(disciplina.getProfessores());
    }

}
