public class Nota {
    private double valor;
    private String data;
    private Aluno aluno; // Associação com Aluno
    private Disciplina disciplina; // Associação com Disciplina

    public Nota(double valor, String data, Aluno aluno, Disciplina disciplina) {
        this.valor = valor;
        this.data = data;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    // Getters e Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
