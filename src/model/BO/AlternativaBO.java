package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.AlternativaDAO;
import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public class AlternativaBO {

    private static AlternativaDAO alternativaDAO = new AlternativaDAO();
    
    public void cadastrar(AlternativaVO alternativa, QuestaoComAlternativasVO questao) throws OperationException {
        if (alternativa != null) {
            alternativaDAO.cadastrar(alternativa, questao);
        } else
            throw new OperationException("A questão não pode ser nula.");
    }

    
    public List<AlternativaVO> listar() {

        List<AlternativaVO> lista = new ArrayList<AlternativaVO>();

        ResultSet consulta = alternativaDAO.listar();

        if (consulta != null)
            try {
                while (consulta.next()) {
                    AlternativaVO atual = new AlternativaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return lista;
    }

    
    public AlternativaVO buscar(AlternativaVO alternativa) {

        if (alternativa != null) {
            ResultSet consulta = alternativaDAO.buscar(alternativa);

            if (consulta != null)
                try {
                    if (consulta.next()) {
                        alternativa.setTexto(consulta.getString("texto"));
                        alternativa.setVerdadeira(consulta.getBoolean("verdadeira"));

                        Calendar criacao = Calendar.getInstance();
                        criacao.setTime(consulta.getDate("data_criacao"));
                        alternativa.setDataCriacao(criacao);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return alternativa;
    }

    
    public List<AlternativaVO> buscar(QuestaoComAlternativasVO questao) {

        List<AlternativaVO> lista = new ArrayList<AlternativaVO>();

        ResultSet consulta = alternativaDAO.buscar(questao);

        if (consulta != null)
            try {
                while (consulta.next()) {
                    AlternativaVO atual = new AlternativaVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return lista;
    }

    
    public void atualizar(AlternativaVO alternativa) throws OperationException {
        if (alternativa != null) {
            alternativaDAO.atualizar(alternativa);
        } else
            throw new OperationException("A alternativa não pode ser nula.");
    }

    
    public void excluir(AlternativaVO alternativa) throws OperationException {
        if (alternativa != null) {
            alternativaDAO.excluir(alternativa);
        } else
            throw new OperationException("A alternativa não pode ser nula.");
    }
}
