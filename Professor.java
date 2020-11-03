public class Professor {
    private int id;
    private String nome;
    private String email;
    private String username;
    private String senha;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(int novoId) {
        this.id = novoId;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void setUsername(String NovoUsername) {
        this.username = NovoUsername;
    }

    public void setSenha(String NovaSenha) {
        this.senha = NovaSenha;
    }
}