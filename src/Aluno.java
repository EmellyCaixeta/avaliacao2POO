import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa implements GerenciadorCadastroAluno {
    private int matricula; 
    private int anoIngresso; 
    private List<Nota> notas;
    
    
    public Aluno(String nome, String dataNascimento, String telefone, Endereco endereco, int matricula, int anoIngresso) {
        super(nome, dataNascimento, telefone, endereco);
        this.matricula = matricula; 
        this.anoIngresso = anoIngresso; 
        this.notas = new ArrayList<>(); 
    } 
    
    public void adicionarNota(Nota nota) { 
        this.notas.add(nota); 
    }

     // Getters e Setters 
     public int getMatricula() {
         return matricula; 
        }
        
    public void setMatricula(int matricula) {
    this.matricula = matricula; 
    }

     public int getAnoIngresso() {
         return anoIngresso; 
        }
        
    public void setAnoIngresso(int anoIngresso) {
         this.anoIngresso = anoIngresso; 
        } 

    public List<Nota> getNotas() {
             return notas; 
        }

    public void setNotas(List<Nota> notas) {
         this.notas = notas; 
        } 

        @Override
        public void cadastrarAluno() {
            // Implementação do método cadastrarAluno
            System.out.println("Exito! Aluno cadastrado com sucesso!");
        }

    }