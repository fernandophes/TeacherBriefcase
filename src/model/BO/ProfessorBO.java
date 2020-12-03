package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.model.DAO.ProfessorDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class ProfessorBO implements ProfessorInterBO {

    public void cadastrar(ProfessorVO professor) {
        // cadastra um novo Professor

        // analisa
        // DAO
    }

    public List<ProfessorVO> listar() {
        // lista todos os professores

        List<ProfessorVO> lista = new ArrayList<ProfessorVO>();
        // analisa
        // DAO
        return lista;
    }

    public ProfessorVO buscar(ProfessorVO professor) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        ResultSet resultado = professorDAO.buscar(professor);

        try {
            if (resultado != null && resultado.next()) {
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setSenha(resultado.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTimeInMillis(resultado.getDate("data_criacao").getTime());
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
    
    public ProfessorVO buscarPorEmail(ProfessorVO professor) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        ResultSet resultado = professorDAO.buscarPorEmail(professor);

        try {
            if (resultado != null && resultado.next()) {
                professor.setNome(resultado.getString("nome"));
                professor.setEmail(resultado.getString("email"));
                professor.setSenha(resultado.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTimeInMillis(resultado.getDate("data_criacao").getTime());
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

    public void editar(ProfessorVO professor) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        // TODO Fazer verificações
        professorDAO.editar(professor);
    }

    public void excluir(ProfessorVO professor) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.excluir(professor);
    }

    public boolean autenticar(ProfessorVO professor) {
        // verifica se o e-mail e a senha do Professor correspondem ao BD

        String senhaFornecida = professor.getSenha();

        ProfessorVO original = buscarPorEmail(professor);

        return senhaFornecida.equals(original.getSenha());
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

            // DAO
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

            // DAO
        }
    }
}
