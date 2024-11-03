import java.util.ArrayList;
import java.util.List;

public class Professor extends Pessoa {
    private String areaDeFormacao;
    private String anoDeAdmissao;
    private String email;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String dataNascimento, String telefone, Endereco endereco, String areaDeFormacao, String anoDeAdmissao, String email) {
        super(nome, dataNascimento, telefone, endereco);
        this.areaDeFormacao = areaDeFormacao;
        this.anoDeAdmissao = anoDeAdmissao;
        this.email = email;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }


    public String getAreaDeFormacao() {
        return areaDeFormacao;
    }

    public void setAreaDeFormacao(String areaDeFormacao) {
        this.areaDeFormacao = areaDeFormacao;
    }

    public String getAnoDeAdmissao() {
        return anoDeAdmissao;
    }

    public void setAnoDeAdmissao(String anoDeAdmissao) {
        this.anoDeAdmissao = anoDeAdmissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
