import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    private String codigo;
    private List<Professor> professores;

    public Disciplina(String nome, int cargaHoraria, String codigo) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.codigo = codigo;
        this.professores = new ArrayList<>();
    }

    public void adicionarProfessor(Professor professor) {
        this.professores.add(professor);
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
