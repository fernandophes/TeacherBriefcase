package src.model.BO;

import java.util.Iterator;
import java.util.List;

import src.model.VO.BaseVO;

public class BaseBO<VO extends BaseVO> {
    
    public boolean contem(List<VO> lista, VO objeto) {
        
        return localizar(lista, objeto) >= 0;
        
    }
    
    public int localizar(List<VO> lista, VO objeto) {

        Iterator<VO> listaIt = lista.iterator();

        for (int i = 0; listaIt.hasNext(); i++)
            if (listaIt.next().getId() == objeto.getId())
                return i;

        return -1;
    }

}
