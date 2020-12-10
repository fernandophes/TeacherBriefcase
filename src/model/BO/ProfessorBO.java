package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.AuthenticationException;
import src.model.DAO.ProfessorDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class ProfessorBO implements ProfessorInterBO {

    ProfessorDAO professorDAO = new ProfessorDAO();

    public void cadastrar(ProfessorVO professor) {
        // cadastra um novo Professor

        // analisa
        // TODO DAO
    }

    public List<ProfessorVO> listar() {
        // lista todos os professores

        List<ProfessorVO> lista = new ArrayList<ProfessorVO>();
        // analisa
        // TODO DAO
        return lista;
    }

    public ProfessorVO buscar(ProfessorVO professor) throws AuthenticationException {

        ResultSet resultado = professorDAO.buscar(professor);

        try {
            if (resultado != null && resultado.next()) {
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setSenha(resultado.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTime(resultado.getDate("data_criacao"));
                professor.setDataCriacao(criacao);
            } else {
                throw new SQLException("A busca não retornou nenhum resultado.");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return professor;
    }

    public ProfessorVO buscarPorEmail(ProfessorVO professor) throws AuthenticationException {

        ResultSet resultado = professorDAO.buscarPorEmail(professor);

        try {
            if (resultado != null && resultado.next()) {
                professor.setNome(resultado.getString("nome"));
                professor.setId(resultado.getLong("id"));
                professor.setSenha(resultado.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTime(resultado.getDate("data_criacao"));
                professor.setDataCriacao(criacao);
            } else {
                throw new SQLException("A busca não retornou nenhum resultado.");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return professor;
    }

    public void atualizar(ProfessorVO professor) {
        // TODO Fazer verificações
        professorDAO.atualizar(professor);
    }

    public void excluir(ProfessorVO professor) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.excluir(professor);
    }

    public ProfessorVO autenticar(ProfessorVO professor) throws AuthenticationException {
        // verifica se o e-mail e a senha do Professor correspondem ao BD

        ProfessorVO busca = new ProfessorVO(professor.getEmail());
        busca = buscarPorEmail(busca);

        if (busca.getSenha().equals(professor.getSenha()))
            return busca;
        else
            throw new AuthenticationException();
    }

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        // adiciona a disciplina à lista, caso ainda não exista

        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina não estiver na lista deste professor, será adicionada
        if (!lista.contains(disciplina)) {
            lista.add(disciplina);
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.adicionar(disciplina, professor);

            // TODO DAO
        }
    }

    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        // remove a disciplina deste professor

        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina estiver na lista deste professor, será removida
        if (lista.remove(disciplina)) {
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.remover(disciplina, professor);

            // TODO DAO
        }
    }
}
