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

        // As verificações de segurança são feitas nos setters

        professorDAO.cadastrar(professor);

    }

    public List<ProfessorVO> listar() throws AuthenticationException {

        List<ProfessorVO> lista = new ArrayList<ProfessorVO>();

        ResultSet resultado = professorDAO.listar();

        try {
            if (resultado != null)
                while (resultado.next()) {
                    ProfessorVO atual = new ProfessorVO();
                    atual.setId(resultado.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

                DisciplinaBO disciplinaBO = new DisciplinaBO();
                professor.setDisciplinas(disciplinaBO.buscar(professor));
            } else {
                throw new SQLException("A busca não retornou nenhum resultado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }

    @Override
    public List<ProfessorVO> buscar(DisciplinaVO disciplina) {
        // TODO Fazer a busca no DAO
        return null;
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
            e.printStackTrace();
        }

        return professor;
    }

    public void atualizar(ProfessorVO professor) {
        professorDAO.atualizar(professor);
    }

    public void excluir(ProfessorVO professor) {
        professorDAO.excluir(professor);
    }

    public ProfessorVO autenticar(ProfessorVO professor) throws AuthenticationException {
        // Verifica se o e-mail e a senha do Professor correspondem ao BD

        ProfessorVO busca = new ProfessorVO(professor.getEmail());
        busca = buscarPorEmail(busca);

        if (busca.getSenha().equals(professor.getSenha()))
            return busca;
        else
            throw new AuthenticationException();
    }

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina não estiver na lista deste professor, será adicionada
        if (!lista.contains(disciplina)) {
            lista.add(disciplina);
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.adicionar(disciplina, professor);

            // Se a disciplina ainda NÃO estiver adicionada no BD (pode ocorrer de estar)
            if (disciplinaBO.buscar(professor).contains(disciplina))
                professorDAO.adicionar(professor, disciplina);
        }
    }

    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina estiver na lista deste professor, será removida
        if (lista.remove(disciplina)) {
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.remover(disciplina, professor);

            // Se a disciplina ainda REALMENTE estiver adicionada no BD (pode ocorrer de não estar)
            if (disciplinaBO.buscar(professor).contains(disciplina))
                professorDAO.remover(professor, disciplina);
        }
    }
}
