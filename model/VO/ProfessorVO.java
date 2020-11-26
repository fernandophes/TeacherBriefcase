package model.VO;

import java.util.Calendar;
import java.util.List;

public class ProfessorVO {
    private String nome;
    private String email;
    private String senha;
    private Calendar dataCriacao;
    private List<DisciplinaVO> disciplinas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty())
            this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação (cadastro) do professor pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public List<DisciplinaVO> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaVO> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "ProfessorVO{" + "nome=" + nome + ", email=" + email + ", senha=" + senha + ", disciplinas=" + disciplinas + '}';
    }
    
    
}