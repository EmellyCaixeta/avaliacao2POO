import java.util.ArrayList;
import java.util.List;

public class Turma {
    private int codigoTurma;
    private Disciplina disciplina;
    private Professor professor;
    private List<Aluno> alunos;
    private int anoLetivo;

    //Construtor
    public Turma(int codigoTurma, Disciplina disciplina, Professor professor, int anoLetivo) {
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.anoLetivo = anoLetivo;
        this.alunos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigoTurma;
    }

    public String getDisciplina() {
        return disciplina.getNome();
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }


}
