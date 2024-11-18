import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aluno extends Pessoa implements GerenciadorCadastroAluno {
    private String matricula;
    private int anoIngresso;
    private List<Nota> notas;

    //Construtor
    public Aluno(String nome, String dataNascimento, String telefone, String matricula, int anoIngresso, 
                    String rua, int numero, String cidade, String estado, String cep) {
        super(nome, dataNascimento, telefone, rua, numero, cidade, estado, cep); // Herda os dados de pessoa
        this.matricula = matricula;
        this.anoIngresso = anoIngresso;
        this.notas = new ArrayList<>(); //Cria uma lista variavel de notas das disciplinas
    }

    //Getters
    public String getMatricula() {
        return matricula;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    //Implementando cadastrar alunos
    @Override
    public void cadastrarAluno(List<Aluno> alunos) {
        Scanner scanner = new Scanner(System.in);
    
        // Inserir os dados
        System.out.print("Nome do Aluno: ");
        String nome = scanner.nextLine();
    
        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();
    
        System.out.print("Telefone (apenas numeros): ");
        String telefone = scanner.nextLine();
    
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
    
        System.out.print("Numero: ");
        int numero = scanner.nextInt();
        scanner.nextLine();  // Limpa a linha residual após nextInt()
    
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
    
        System.out.print("Estado (Sigla): ");
        String estado = scanner.nextLine();
    
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
    
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
    
        System.out.print("Ano de Ingresso: ");
        int anoIngresso = scanner.nextInt();
        scanner.nextLine();  // Limpa a linha residual após nextInt()
    
        // Cadastro do aluno com endereço
        Aluno aluno = new Aluno(nome, dataNascimento, telefone, matricula, anoIngresso, rua, numero, cidade, estado, cep);
        alunos.add(aluno); // Adiciona o aluno à lista
    
        System.out.println("Aluno cadastrado com sucesso!");
        
        // Chama o método de salvar
        salvar(alunos);
    }
    
    public static void cadastrarAlunoNoMain(List<Aluno> alunos) {
        Aluno aluno = new Aluno("", "", "", "", 0, "", 0, "", "", "");  // Instancia um Aluno vazio
        aluno.cadastrarAluno(alunos);  // Chama o método cadastrarAluno para preencher os dados
    }
    

    public static void salvar(List<Aluno> alunos)
    {
        Salvar.salvarAluno(alunos); //Salva no bloco de notas
    }
}