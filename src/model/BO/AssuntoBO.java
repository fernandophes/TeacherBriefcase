package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.exception.OperationException;
import src.model.DAO.AssuntoDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.QuestaoVO;

public class AssuntoBO implements AssuntoInterBO {

    AssuntoDAO assuntoDAO = new AssuntoDAO();
    DisciplinaBO disciplinaBO = new DisciplinaBO();

    @Override
    public void cadastrar(DisciplinaVO disciplina, String assunto) throws OperationException {
        if (disciplina != null && assunto != null && !assunto.isEmpty()) {
            assuntoDAO.cadastrar(disciplina, assunto);

            // Adiciona o assunto à DisciplinaVO
            List<String> novaLista = disciplina.getAssuntos();
            novaLista.add(assunto);
            disciplina.setAssuntos(novaLista);
        } else
            throw new OperationException("Os dados informados são inválidos.");
    }

    @Override
    public List<List<String>> listar() {

        // Uma lista completa contendo as listas dos assuntos de cada disciplina,
        // separadamente
        List<List<String>> lista = new ArrayList<List<String>>();

        try {
            List<DisciplinaVO> disciplinas = disciplinaBO.listar();
            Iterator<DisciplinaVO> disciplinasIt = disciplinas.iterator();

            while (disciplinasIt.hasNext())
                lista.add(buscar(disciplinasIt.next()));
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public String buscar(DisciplinaVO disciplina, String assunto) throws OperationException {

        String resultado = null;

        if (disciplina != null && assunto != null) {
            ResultSet consulta = assuntoDAO.buscar(disciplina, assunto);

            try {
                if (consulta.next())
                    resultado = consulta.getString("nome");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("Os dados fornecidos não podem ser nulos");

        return resultado;
    }

    @Override
    public List<String> buscar(DisciplinaVO disciplina) throws OperationException {

        List<String> resultado = new ArrayList<String>();

        if (disciplina != null) {
            ResultSet consulta = assuntoDAO.buscar(disciplina);

            try {
                while (consulta.next())
                    resultado.add(consulta.getString("nome"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula");

        return resultado;
    }

    @Override
    public List<String> buscar(QuestaoVO questao) throws OperationException {

        List<String> resultado = new ArrayList<String>();

        if (questao != null) {
            ResultSet consulta = assuntoDAO.buscar(questao);

            try {
                while (consulta.next())
                    resultado.add(consulta.getString("nome"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else
            throw new OperationException("A disciplina fornecida não pode ser nula");

        return resultado;
    }

    @Override
    public void atualizar(DisciplinaVO disciplina, String assunto, String novo) throws OperationException {
        if (disciplina != null && assunto != null && novo != null && !assunto.isEmpty() && !novo.isEmpty())
            assuntoDAO.atualizar(disciplina, assunto, novo);
        else
            throw new OperationException("Os dados submetidos são inválidos.");
    }

    @Override
    public void excluir(DisciplinaVO disciplina, String assunto) throws OperationException {
        if (disciplina != null && assunto != null && !assunto.isEmpty())
            assuntoDAO.excluir(disciplina, assunto);
        else
            throw new OperationException("Os dados submetidos são inválidos.");
    }

}
