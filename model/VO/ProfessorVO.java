package model.VO;

import java.util.Calendar;

public class ProfessorVO {
    private String nome;
    private String email;
    private String senha;
    private Calendar dataCriacao;
    private DisciplinaVO[] disciplinas;

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
        if (dataCriacao != null)
            this.dataCriacao = dataCriacao;
    }

    public DisciplinaVO[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(DisciplinaVO[] disciplinas) {
        this.disciplinas = disciplinas;
    }
}