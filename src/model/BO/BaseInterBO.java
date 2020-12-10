package src.model.BO;

import java.util.List;

public abstract interface BaseInterBO<VO> {
    
    public void cadastrar(VO vo);

    public List<VO> listar();

    public VO buscar(VO vo) throws Exception;

    public void atualizar(VO vo);

    public void excluir(VO vo);
}
