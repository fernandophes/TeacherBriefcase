package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.QuestaoVO;

public interface QuestaoInterDAO extends BaseInterDAO<QuestaoVO> {
    
    public ResultSet buscarPorDificuldade(QuestaoVO vo);

}
