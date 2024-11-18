//Importar todas as bibliotecas para evitar dor de cabeça
import java.io.*;
import java.util.*;


public class App {

    //Fazer lista dos dados e abrir o scanner
    static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu(); //Mostra as opções de escolha
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Ler a opção escolhida

            switch (opcao) {
                case 1:
                    Aluno.cadastrarAlunoNoMain(alunos);
                    break;
                case 2:
                    Professor.cadastrarProfessorNoMain(professores, disciplinas);
                    break;
                case 3:
                    cadastrarDisciplina();
                    break;
                case 4:
                    cadastrarTurma();
                    break;
                case 5:
                    adicionarNota();
                    break;
                case 6:
                    listarAlunos();
                    break;
                case 7:
                    listarProfessores();
                    break;
                case 8:
                    listarDisciplinas();
                    break;
                case 9:
                    listarTurmas();
                    break;
                case 10:
                    media();
                    break;
                case 11:
                    quantidadeAlunos();
                case 12:
                    alunoComMaiorNota();
                    break;
                case 13:
                    listarAlunosPorDisciplina();
                    break;
                case 14:
                    listarProfessoresETurmas();
                    break;
                case 15:
                    System.out.println("Encerrando programa..."); //Encerra o programa
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente."); //caso escolha um numero diferente da lista
            }
        } while (opcao != 15); //enquanto não for digitado 15 o menu aparece de novo
    }

    private static void exibirMenu() {
        System.out.println("=========================");
        System.out.println("      MENU PRINCIPAL     ");
        System.out.println("=========================");
        System.out.println("1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Professor");
        System.out.println("3 - Cadastrar Disciplina");
        System.out.println("4 - Cadastrar Turma");
        System.out.println("5 - Inserir Nota");
        System.out.println("6 - Relatório Alunos");
        System.out.println("7 - Relatório Professores");
        System.out.println("8 - Relatório Disciplinas");
        System.out.println("9 - Relatório Turmas");
        System.out.println("10 - Relatório de Aluno x Notas x Média");
        System.out.println("11 - Quantidade de Alunos Cadastrados");
        System.out.println("12 - Nome do Aluno com a Maior Nota");
        System.out.println("13 - Listar Alunos de uma Determinada Disciplina");
        System.out.println("14 - Listar as turmas que estão associadas a um determinado professor");
        System.out.println("15 - Sair");
        System.out.println("=========================");
    }


    
    private static void cadastrarDisciplina() {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();

        int cargaHoraria;
        while (true) {
            System.out.print("Carga horária (em horas): ");
            try {
                cargaHoraria = scanner.nextInt();
                scanner.nextLine(); // // Consumir o caractere de nova linha
                break; // Finaliza o loop se o numero for inteiro
            } catch (InputMismatchException e) {
                System.out.println("Carga horária inválida. Por favor, insira um número inteiro.");
                scanner.nextLine(); // Limpar a entrada incorreta (enquanto nao for inteiro ele repete)
            }
        }

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine(); //Lê o codigo da disciplina

        // Cria a nova disciplina e adicona ela na lista
        Disciplina novaDisciplina = new Disciplina(nome, cargaHoraria, codigo);
        disciplinas.add(novaDisciplina);

        System.out.println("Disciplina cadastrada com sucesso: " + novaDisciplina.getNome());
        Salvar.salvarDisciplinas(disciplinas); //Salva no bloco de notas
    }

    private static void cadastrarTurma() {
        String caminhoArquivoDisciplinas = "Disciplinas.txt";
        String caminhoArquivoProfessores = "Professores.txt";
    
        // Carregar disciplinas
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoDisciplinas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty() || !linha.contains(":")) {
                    continue; // Ignora linhas vazias ou mal formatadas
                }
    
                try {
                    String nome = linha.split(": ", 2)[1].trim();
                    String cargaHorariaLine = br.readLine();
                    String codigoLine = br.readLine();
                    
                    if (cargaHorariaLine != null && codigoLine != null) {
                        int cargaHoraria = Integer.parseInt(cargaHorariaLine.split(": ", 2)[1].trim());
                        String codigo = codigoLine.split(": ", 2)[1].trim();
                        disciplinas.add(new Disciplina(nome, cargaHoraria, codigo));
    
                        // Pula linha em branco ou separadora
                        br.readLine(); 
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao processar disciplina: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
            return;
        }
    
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas. Cadastre uma disciplina antes de cadastrar uma turma.");
            return;
        }
    
        // Carregar professores
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoProfessores))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty() || linha.startsWith("-")) {
                    continue; // Ignora linhas vazias e linhas de cabeçalho
                }
    
                try {
                    String nome = linha.split(": ", 2)[1].trim();
                    String dataNascimento = br.readLine().split(": ", 2)[1].trim();
                    String telefone = br.readLine().split(": ", 2)[1].trim();
                    String rua = br.readLine().split(": ", 2)[1].trim();
                    int numero = Integer.parseInt(br.readLine().split(": ", 2)[1].trim());
                    String cidade = br.readLine().split(": ", 2)[1].trim();
                    String estado = br.readLine().split(": ", 2)[1].trim();
                    String cep = br.readLine().split(": ", 2)[1].trim();
                    String areaDeFormacao = br.readLine().split(": ", 2)[1].trim();
                    int anoDeAdmissao = Integer.parseInt(br.readLine().split(": ", 2)[1].trim());
    
                    Professor professor = new Professor(nome, dataNascimento, telefone, areaDeFormacao, anoDeAdmissao,
                            "", rua, numero, cidade, estado, cep);
                    professores.add(professor);
    
                    br.readLine(); // Ignora a linha separadora
                } catch (Exception e) {
                    System.out.println("Erro ao processar professor: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
            return;
        }
    
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados. Cadastre um professor antes de cadastrar uma turma.");
            return;
        }
    
        // Cadastro de turma
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("==== Cadastro de Turma ====");
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println((i + 1) + " - " + disciplinas.get(i).getNome());
        }
    
        System.out.print("Digite o número da disciplina: ");
        int disciplinaIndex = scanner.nextInt() - 1;
    
        if (disciplinaIndex < 0 || disciplinaIndex >= disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Disciplina disciplinaEscolhida = disciplinas.get(disciplinaIndex);
    
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println((i + 1) + " - " + professores.get(i).getNome());
        }
    
        System.out.print("Escolha um professor (número): ");
        int professorIndex = scanner.nextInt() - 1;
    
        if (professorIndex < 0 || professorIndex >= professores.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        Professor professorEscolhido = professores.get(professorIndex);
    
        System.out.print("Digite o código da turma (numérico): ");
        int codigoTurma = scanner.nextInt();
    
        if (turmas.stream().anyMatch(turma -> turma.getCodigo() == codigoTurma)) {
            System.out.println("Esse código de turma já existe.");
            return;
        }
    
        System.out.print("Digite o ano letivo da turma: ");
        int anoLetivo = scanner.nextInt();
    
        Turma novaTurma = new Turma(codigoTurma, disciplinaEscolhida, professorEscolhido, anoLetivo);
        turmas.add(novaTurma);
    
        System.out.println("Turma cadastrada com sucesso: " + codigoTurma);
        Salvar.salvarTurmas(turmas);
    }
    
    
    

    private static void adicionarNota() {
        // Caminhos para os arquivos de alunos e disciplinas
        String caminhoArquivoAlunos = "Alunos.txt";
        String caminhoArquivoDisciplinas = "Disciplinas.txt";

        // Carregar alunos do arquivo
        alunos.clear(); // Limpar lista de alunos para evitar duplicados
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoAlunos))) {
            String linha;
            while ((linha = br.readLine()) != null) { //Le linha por linha até retornar nulo
                if (linha.trim().isEmpty() || linha.contains("Alunos")) { //Se conter o cabeçalho continua lendo
                    continue;
                }

                String nome = linha.split(": ")[1];
                String dataNascimento = br.readLine().split(": ")[1];
                String telefone = br.readLine().split(": ")[1];
                String rua = br.readLine().split(": ")[1];
                int numero = Integer.parseInt(br.readLine().split(": ")[1]);
                String cidade = br.readLine().split(": ")[1];
                String estado = br.readLine().split(": ")[1];
                String cep = br.readLine().split(": ")[1];
                String matricula = br.readLine().split(": ")[1];
                int anoIngresso = Integer.parseInt(br.readLine().split(": ")[1]);
                Aluno aluno = new Aluno(nome, dataNascimento, telefone, matricula, anoIngresso, rua, numero, cidade, estado, cep);
                alunos.add(aluno);

                br.readLine(); // Pular linha entre registros
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return; //se der erro lascou
        }

        // Carregar disciplinas do arquivo
        disciplinas.clear(); // Limpar lista de disciplinas para evitar duplicados
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoDisciplinas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty() || linha.contains("Disciplinas")) {
                    continue; //Se conter o cabeçalho continua lendo
                }

                String nome = linha.split(": ")[1];
                int cargaHoraria = Integer.parseInt(br.readLine().split(": ")[1]);
                String codigo = br.readLine().split(": ")[1];
                Disciplina disciplina = new Disciplina(nome, cargaHoraria, codigo);
                disciplinas.add(disciplina);

                br.readLine(); // Pular linha entre registros
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
            return; //caso de erro
        }

        // Verificar se há alunos e disciplinas carregados
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado. Cadastre um aluno primeiro.");
            return;
        }
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada. Cadastre uma disciplina primeiro.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        // Exibir lista de alunos e solicitar seleção
        System.out.println("Escolha o número do aluno:");
        for (int i = 0; i < alunos.size(); i++) { //exibe a lista até ate o tamanho dela mesma
            System.out.println((i + 1) + ". " + alunos.get(i).getNome());
        }
        int alunoIndex;
        while (true) {
            System.out.print("Número do aluno: ");
            alunoIndex = scanner.nextInt() - 1; //le o que ele coloca e diminui 1 pra ter o indice certo
            scanner.nextLine();
            if (alunoIndex >= 0 && alunoIndex < alunos.size()) { //ve se o numero inserido esta dentro da lista
                break;
            } else {
                System.out.println("Opção inválida. Escolha um número da lista.");
            }
        }
        Aluno alunoSelecionado = alunos.get(alunoIndex);

        // Exibir lista de disciplinas e solicitar seleção
        System.out.println("Escolha o número da disciplina:");
        for (int i = 0; i < disciplinas.size(); i++) { //exibe a lista enquanto tiver linhas
            System.out.println((i + 1) + ". " + disciplinas.get(i).getNome());
        }
        int disciplinaIndex;
        while (true) {
            System.out.print("Número da disciplina: ");
            disciplinaIndex = scanner.nextInt() - 1; //escolhe o numero da disciplina e diminui um pra ter o indice certo
            scanner.nextLine();
            if (disciplinaIndex >= 0 && disciplinaIndex < disciplinas.size()) {
                break; //se estiver dentro da lista ele le
            } else { //se não, ele considera invalido
                System.out.println("Opção inválida. Escolha um número da lista.");
            }
        }
        Disciplina disciplinaSelecionada = disciplinas.get(disciplinaIndex);

        // Solicitar valor da nota
        double valorNota;
        while (true) {
            System.out.print("Informe o valor da nota (0-10): ");
            try {
                valorNota = scanner.nextDouble();
                scanner.nextLine();
                if (valorNota >= 0 && valorNota <= 10) {
                    break; //se estiver no requisito ele le e termina
                } else { //se estiver fora do escopo apresenta erro
                    System.out.println("Nota inválida. Insira uma nota entre 0 e 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um número.");
                scanner.nextLine(); //se nao for um valor inteiro ou double, da erro também
            }
        }

        // Solicitar data da nota
        System.out.print("Informe a data da nota (dd/mm/yyyy): ");
        String dataNota = scanner.nextLine();

        // Criar e adicionar nota ao aluno
        Nota novaNota = new Nota(alunoSelecionado, disciplinaSelecionada, valorNota, dataNota);
        alunoSelecionado.getNotas().add(novaNota);


        Salvar.salvarNotas(alunos); //salva no bloco de notas
    }


    private static void listarAlunos() {
        String caminhoArquivo1 = "Alunos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo1))) {
            String linha;
            System.out.println("\nConteúdo do arquivo:");
            while ((linha = br.readLine()) != null) { //enqunto a linha for diferente de nulo ele exibe
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void listarProfessores() {
        String caminhoArquivo2 = "Professores.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo2))) {
            String linha;
            System.out.println("\nConteúdo do arquivo:");
            while ((linha = br.readLine()) != null) { //enqunto a linha for diferente de nulo ele exibe
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void listarDisciplinas() {
        String caminhoArquivo3 = "Disciplinas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo3))) {
            String linha;
            System.out.println("\nConteúdo do arquivo:");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void listarTurmas() {
        String caminhoArquivo4 = "Turmas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo4))) {
            String linha;
            System.out.println("\nConteúdo do arquivo:");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }


    private static void quantidadeAlunos() {
        String caminhoArquivo1 = "Alunos.txt";  // Caminho para o seu arquivo
        int contadorLinhas = 0;
        int conta = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo1))) {
            String linha;

            // Lê o arquivo linha por linha
            while ((linha = br.readLine()) != null) { //enquanto nao for linha nula ele le
                contadorLinhas++;  // Incrementa o contador para cada linha lida
            }

            conta = contadorLinhas / 12;
            System.out.println("Numero de alunos cadastrados: " + conta);

        } catch (IOException e) {
            e.printStackTrace(); //diagnosticar o erro se ocorrer
        }
    }

    public static void media() {
        String caminhoArquivoNotas = "Notas.txt";
        Scanner scanner = new Scanner(System.in);

        // Lista para armazenar os nomes dos alunos para exibir ao usuário
        List<String> nomesAlunos = new ArrayList<>();

        // Carregar nomes dos alunos do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoNotas))) {
            String linha;
            String nomeAtual = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nomeAtual = linha.split(":")[1].trim();
                    if (!nomesAlunos.contains(nomeAtual)) {
                        nomesAlunos.add(nomeAtual); // Adiciona o nome do aluno se não estiver na lista
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de notas: " + e.getMessage());
            return;
        }

        // Exibir opções de alunos
        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < nomesAlunos.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, nomesAlunos.get(i));
        }

        // Solicitar escolha do usuário
        System.out.print("Escolha o número do aluno para gerar o relatório: ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        if (escolha < 1 || escolha > nomesAlunos.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        String nomeAlunoEscolhido = nomesAlunos.get(escolha - 1);

        // Ler novamente o arquivo para calcular a média do aluno escolhido
        double somaNotas = 0;
        int quantidadeNotas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoNotas))) { //abrir e ler o arquivo
            String linha; //armazenar cada linha
            boolean alunoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:")) { //verifica se contem um nome
                    String nome = linha.split(":")[1].trim(); //extrai o dado depois do ":"
                    alunoEncontrado = nome.equals(nomeAlunoEscolhido); //verifica se o nome encontrado é igual ao escolhido
                } else if (alunoEncontrado && linha.startsWith("Nota:")) {
                    double nota = Double.parseDouble(linha.split(":")[1].trim()); //extrai a nota em double
                    somaNotas += nota;
                    quantidadeNotas++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de notas: " + e.getMessage());
            return;
        }

        // Exibir relatório do aluno selecionado
        if (quantidadeNotas > 0) {
            double media = somaNotas / quantidadeNotas;
            System.out.printf("Relatório para o aluno: %s%n", nomeAlunoEscolhido);
            System.out.printf("Total de Notas: %d%n", quantidadeNotas);
            System.out.printf("Média: %.2f%n", media);
        } else {
            System.out.printf("Nenhuma nota encontrada para o aluno %s.%n", nomeAlunoEscolhido);
        }
    }

    public static void alunoComMaiorNota() {
        String caminhoArquivoNotas = "Notas.txt";

        String alunoMaiorNota = null;
        double maiorNota = -1; // Valor inicial para a maior nota

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoNotas))) {
            String linha;
            String nomeAtual = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nomeAtual = linha.split(":")[1].trim();
                } else if (linha.startsWith("Nota:")) {
                    double nota = Double.parseDouble(linha.split(":")[1].trim());

                    // Verifica se essa nota é maior que a maior nota registrada até agora
                    if (nota > maiorNota) {
                        maiorNota = nota;
                        alunoMaiorNota = nomeAtual;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de notas: " + e.getMessage());
            return;
        }

        // Exibe o aluno com a maior nota
        if (alunoMaiorNota != null) {
            System.out.printf("Aluno com a maior nota:%nNome: %s%nNota: %.2f%n", alunoMaiorNota, maiorNota);
        } else {
            System.out.println("Nenhuma nota encontrada no arquivo.");
        }
    }

    public static void listarAlunosPorDisciplina() {
        String caminhoArquivoNotas = "Notas.txt";
        Set<String> disciplinas = new HashSet<>(); // Para armazenar disciplinas únicas como String

        // Primeira leitura para identificar disciplinas
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoNotas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Disciplina:")) {
                    String disciplina = linha.split(":")[1].trim();
                    disciplinas.add(disciplina); // Adiciona a disciplina ao conjunto
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de notas: " + e.getMessage());
            return;
        }

        // Exibe disciplinas disponíveis
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina encontrada.");
            return;
        }

        System.out.println("Disciplinas disponíveis:");
        disciplinas.forEach(disciplina -> System.out.println("- " + disciplina));

        // Recebe a escolha da disciplina
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da disciplina para ver os alunos: ");
        String disciplinaEscolhida = scanner.nextLine().trim();

        System.out.printf("Alunos cadastrados na disciplina %s:%n", disciplinaEscolhida);

        // Segunda leitura para listar alunos da disciplina escolhida, sem duplicatas
        Set<String> alunosExibidos = new HashSet<>(); // Para evitar duplicatas
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoNotas))) {
            String linha;
            String nomeAluno = null;
            String disciplinaAtual = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nomeAluno = linha.split(":")[1].trim();
                } else if (linha.startsWith("Disciplina:")) {
                    disciplinaAtual = linha.split(":")[1].trim();
                } else if (linha.startsWith("Nota:")) {
                    if (disciplinaAtual != null && disciplinaAtual.equalsIgnoreCase(disciplinaEscolhida)) {
                        if (!alunosExibidos.contains(nomeAluno)) {
                            System.out.println("- " + nomeAluno);
                            alunosExibidos.add(nomeAluno); // Marca o aluno como exibido
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de notas: " + e.getMessage());
        }
    }

    public static void listarProfessoresETurmas() {
        String caminhoArquivoTurmas = "Turmas.txt";
        Set<String> professores = new HashSet<>();

        // Primeira leitura para identificar os professores
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoTurmas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Professor Responsável:")) {
                    String professor = linha.split(":")[1].trim();
                    professores.add(professor);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de turmas: " + e.getMessage());
            return;
        }

        // Exibe professores disponíveis
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor encontrado.");
            return;
        }

        System.out.println("Professores disponíveis:");
        professores.forEach(professor -> System.out.println("- " + professor));

        // Recebe a escolha do professor
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do professor para ver as turmas: ");
        String professorEscolhido = scanner.nextLine().trim();

        System.out.printf("Turmas do professor %s:%n", professorEscolhido);

        // Segunda leitura para listar turmas do professor escolhido
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoTurmas))) {
            String linha;
            boolean turmaEncontrada = false;
            String codigo = null, disciplina = null, anoLetivo = null, professor = null;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Codigo:")) {
                    codigo = linha.split(":")[1].trim();
                } else if (linha.startsWith("Disciplina:")) {
                    disciplina = linha.split(":")[1].trim();
                } else if (linha.startsWith("Ano Letivo:")) {
                    anoLetivo = linha.split(":")[1].trim();
                } else if (linha.startsWith("Professor Responsável:")) {
                    professor = linha.split(":")[1].trim();

                    // Exibe turma se o professor for o escolhido
                    if (professor.equalsIgnoreCase(professorEscolhido)) {
                        System.out.printf("Código: %s | Disciplina: %s | Ano Letivo: %s%n", codigo, disciplina, anoLetivo);
                        turmaEncontrada = true;
                    }
                }
            }

            if (!turmaEncontrada) {
                System.out.printf("Nenhuma turma encontrada para o professor %s.%n", professorEscolhido);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de turmas: " + e.getMessage());
        }
    }
}
