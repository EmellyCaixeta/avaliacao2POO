import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int cargaHoraria;
    private String codigo;
    private List<Professor> professores;

    //Construtor
    public Disciplina(String nome, int cargaHoraria, String codigo) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.codigo = codigo;
        this.professores = new ArrayList<>(); // criar lista variavel de professores da disciplina
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

}
