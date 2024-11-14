import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private Disciplina disciplina;
    private Professor professor;
    private List<Aluno> alunos;
    private int anoLetivo;

    public Turma(String codigo, Disciplina disciplina, Professor professor, int anoLetivo) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.alunos = new ArrayList<>();
        this.anoLetivo = anoLetivo;
    }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }
}
