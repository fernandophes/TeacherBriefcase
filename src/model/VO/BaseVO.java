package src.model.VO;

import src.exception.OperationException;

public class BaseVO {
    
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) throws OperationException {
        if (id > 0)
            this.id = id;
        else
            throw new OperationException("O id precisa ser maior que zero");
    }
    
}
