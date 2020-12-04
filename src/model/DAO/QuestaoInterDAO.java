package src.model.DAO;

import java.sql.ResultSet;

import src.model.VO.QuestaoVO;

public interface QuestaoInterDAO<VO extends QuestaoVO> extends BaseInterDAO<VO> {
    
    public ResultSet buscarPorDificuldade(VO vo);

}
