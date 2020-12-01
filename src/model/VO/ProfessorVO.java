package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfessorVO {
    private String nome = "";
    private String email = "";
    private String senha = "";
    private Calendar dataCriacao = Calendar.getInstance();
    private List<DisciplinaVO> disciplinas = new ArrayList<DisciplinaVO>();

    public ProfessorVO() {

    }

    public ProfessorVO(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

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
        if (email != null && email.contains("@"))
            this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha != null && senha.length() >= 8 && senha.length() <= 20)
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
        String saida = "Prof. " + nome + " (" + email + ")";
        return saida;
    }
    
    
}