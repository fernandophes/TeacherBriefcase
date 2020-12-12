package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public interface ProfessorDisciplinaInterDAO {
    
    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina);

    // Buscar disciplinas do professor
    public ResultSet buscar(ProfessorVO professor);

    // Buscar professores da disciplina
    public ResultSet buscar(DisciplinaVO disciplina);

    public void remover(ProfessorVO professor, DisciplinaVO disciplina);
}
