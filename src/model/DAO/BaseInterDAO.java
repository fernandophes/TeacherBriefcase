package src.model.DAO;

public interface BaseInterDAO<VO> extends CleanBaseInterDAO<VO> {

    public void cadastrar(VO vo) throws Exception;
    
}
