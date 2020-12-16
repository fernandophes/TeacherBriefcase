package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public interface AlternativaInterDAO extends CleanBaseInterDAO<AlternativaVO> {

    public void cadastrar(AlternativaVO alternativa, QuestaoComAlternativasVO questao);
    
    public ResultSet buscar(QuestaoComAlternativasVO vo);

}
