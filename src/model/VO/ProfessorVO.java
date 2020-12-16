package src.model.VO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.AuthenticationException;

public class ProfessorVO {

    private long id;
    private String nome = "";
    private String email = "";
    private String senha = "";
    private Calendar dataCriacao = Calendar.getInstance();
    private List<DisciplinaVO> disciplinas = new ArrayList<DisciplinaVO>();

    public static ProfessorVO logado = null;

    public ProfessorVO() {

    }

    public ProfessorVO(String email) throws AuthenticationException {
        setEmail(email);
    }

    public ProfessorVO(String nome, String email, String senha) throws AuthenticationException {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws AuthenticationException {
        if (id > 0)
            this.id = id;
        else
            throw new AuthenticationException("O id precisa ser maior que zero");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws AuthenticationException {
        if (nome != null && !nome.isEmpty())
            this.nome = nome.trim();
        else
            throw new AuthenticationException("O nome não pode ficar em branco");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws AuthenticationException {
        if (email != null && !email.isEmpty())
            if (email.contains("@") && email.contains("."))
                this.email = email.trim();
            else
                throw new AuthenticationException("O e-mail precisa conter \"@\" e \".\".");
        else
            throw new AuthenticationException("O e-mail não pode ficar em branco.");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws AuthenticationException {
        if (senha != null && !senha.isEmpty()) {
            senha = senha.trim();
            if (senha.length() >= 8)
                if (senha.length() <= 20)
                    this.senha = senha;
                else
                    throw new AuthenticationException("A senha precisa ter 20 caracteres ou menos");
            else
                throw new AuthenticationException("A senha precisa ter 8 caracteres ou mais");
        } else
            throw new AuthenticationException("A senha não pode ser ficar em branco");
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) throws AuthenticationException {
        // A data de criação (cadastro) do professor pode ser passada ou atual, mas não
        // futura
        if (dataCriacao != null)
            if (dataCriacao.compareTo(Calendar.getInstance()) <= 0)
                this.dataCriacao = dataCriacao;
            else
                throw new AuthenticationException("A data de criação não pode ser futura");
        else
            throw new AuthenticationException("A data de criação não pode ficar em branco");
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