package model.BO;

import java.util.List;

public interface BaseInterBO<VO> {
    
    public void cadastrar(VO vo);

    public List<VO> listar();

    public VO buscar(VO vo);

    public void editar(VO vo);

    public void excluir(VO vo);
}
