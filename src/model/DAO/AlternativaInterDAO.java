package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.AlternativaVO;

public interface AlternativaInterDAO extends BaseInterDAO<AlternativaVO> {
    public ResultSet buscarPorQuestao(AlternativaVO vo);
}
