import java.util.List;
import java.util.Scanner;

public class Professor extends Pessoa implements GerenciadorCadastroProfessor {
    private String areaDeFormacao;
    private int anoDeAdmissao;
    private String email;

    // Construtor recebendo rua, numero, cidade, estado e cep diretamente
    public Professor(String nome, String dataNascimento, String telefone, String areaDeFormacao,
                     int anoDeAdmissao, String email, String rua, int numero, String cidade, String estado, String cep) {
        super(nome, dataNascimento, telefone, rua, numero, cidade, estado, cep); // Herdando da classe Pessoa
        this.areaDeFormacao = areaDeFormacao;
        this.anoDeAdmissao = anoDeAdmissao;
        this.email = email;
    }

    //Getters
    public String getAreaDeFormacao() {
        return areaDeFormacao;
    }

    public int getAnoDeAdmissao() {
        return anoDeAdmissao;
    }

    public String getEmail() {
        return email;
    }

    //Implementando cadastrar o professor
  @Override
public void cadastrarProfessor(List<Professor> professores) { 
    Scanner scanner = new Scanner(System.in);

    // Coleta de dados do professor
    System.out.print("Nome do Professor: ");
    String nome = scanner.nextLine();

    System.out.print("Data de Nascimento (dd/mm/aaaa): ");
    String dataNascimento = scanner.nextLine();

    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();

    System.out.print("Rua: ");
    String rua = scanner.nextLine();

    System.out.print("Numero: ");
    int numero = scanner.nextInt();
    scanner.nextLine();  // Limpa o buffer após nextInt()

    System.out.print("Cidade: ");
    String cidade = scanner.nextLine();

    System.out.print("Estado (Sigla): ");
    String estado = scanner.nextLine();

    System.out.print("CEP: ");
    String cep = scanner.nextLine();

    System.out.print("Área de Formação: ");
    String areaDeFormacao = scanner.nextLine();

    System.out.print("Ano de Admissão: ");
    int anoDeAdmissao = scanner.nextInt();
    scanner.nextLine();  // Limpa o buffer após nextInt()

    System.out.print("Email: ");
    String email = scanner.nextLine();

    // Cria um novo professor com os dados de endereço já no construtor
    Professor novoProfessor = new Professor(nome, dataNascimento, telefone, areaDeFormacao, anoDeAdmissao, email, rua, numero, cidade, estado, cep);
    professores.add(novoProfessor);

    System.out.println("Professor cadastrado com sucesso!");

    // Salva o professor
    salvar(professores);
}


    public static void cadastrarProfessorNoMain(List<Professor> professores, List<Disciplina> disciplinas) {
        if (disciplinas.isEmpty()) { //Se nao tiver disciplina não tem porque cadastrar professores
            System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina antes de cadastrar um professor.");
            return;
        }

        // Instancia um Professor para chamar o método de cadastro
        Professor professor = new Professor("", "", "", "", 0, "", "", 0, "", "","");
        professor.cadastrarProfessor(professores);  // Chama o método para coletar os dados do novo professor

        // Após o cadastro, associe o professor a uma disciplina existente
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma disciplina para associar ao professor:");

        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println((i + 1) + " - " + disciplinas.get(i).getNome()); // Retorna o codigo e nome das disciplinas existentes
        }

        System.out.print("Escolha o número da disciplina: ");
        int disciplinaIndex = scanner.nextInt() - 1;

        //Se o numero escolhido não estiver na lista de disciplinas, vai desconsiderar
        if (disciplinaIndex < 0 || disciplinaIndex >= disciplinas.size()) {
            System.out.println("Opção inválida. Professor não foi associado a nenhuma disciplina.");
        } else {
            Disciplina disciplinaEscolhida = disciplinas.get(disciplinaIndex);
            disciplinaEscolhida.getProfessores().add(professor); // Associa o professor à disciplina
            System.out.println("Professor associado à disciplina: " + disciplinaEscolhida.getNome());
        } //vincula os dois


    }

    public static void salvar(List<Professor> professores)
    {
        Salvar.salvarProfessores(professores); //Salva no bloco de notas
    }

    @Override
    public String toString() {
        return nome + ""; //tostring
    }
}
