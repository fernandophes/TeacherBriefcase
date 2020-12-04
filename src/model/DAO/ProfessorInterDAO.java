package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.ProfessorVO;

public interface ProfessorInterDAO extends BaseInterDAO<ProfessorVO> {

    public ResultSet buscarPorEmail(ProfessorVO vo);

}
