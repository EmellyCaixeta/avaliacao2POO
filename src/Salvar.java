import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Salvar {

    public static void salvarAluno(List<Aluno> alunos) {
        try (FileWriter writer = new FileWriter("Alunos.txt")) { //cria ou escreve em um arquivo existente
            // Cabeçalho principal
            writer.write("------------------- Alunos -----------------------\n");

            for (Aluno aluno : alunos) { //extrai os dados dos gets de alunos
                writer.write("Nome: " + aluno.getNome() + "\n");
                writer.write("Data de Nascimento: " + aluno.getDataNascimento() + "\n");
                writer.write("Telefone: " + aluno.getTelefone() + "\n");
                writer.write("Rua: " + aluno.endereco.getRua() + "\n");
                writer.write("Numero: " + aluno.endereco.getNumero() + "\n");
                writer.write("Cidade: " + aluno.endereco.getCidade() + "\n");
                writer.write("Estado: " + aluno.endereco.getEstado() + "\n");
                writer.write("Cep: " + aluno.endereco.getCep() + "\n");
                writer.write("Matrícula: " + aluno.getMatricula() + "\n");
                writer.write("Ano de Ingresso: " + aluno.getAnoIngresso() + "\n");
                writer.write("-------------------------------------------------\n\n"); // Separar os dados de cada pessoa
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void salvarProfessores(List<Professor> professores) {
            try (FileWriter writer = new FileWriter("Professores.txt")) {
                writer.write("------------------- Professores ----------------------- \n");

                for (Professor professor : professores) {
                    writer.write("Nome: " + professor.getNome() + "\n");
                    writer.write("Data de Nascimento: " + professor.getDataNascimento() + "\n");
                    writer.write("Telefone: " + professor.getTelefone() + "\n");
                    writer.write("Rua: " + professor.endereco.getRua() + "\n");
                    writer.write("Numero: " + professor.endereco.getNumero() + "\n");
                    writer.write("Cidade: " + professor.endereco.getCidade() + "\n");
                    writer.write("Estado: " + professor.endereco.getEstado() + "\n");
                    writer.write("Cep: " + professor.endereco.getCep() + "\n");
                    writer.write("Area de Formação: " + professor.getAreaDeFormacao() + "\n");
                    writer.write("Ano de Admissão: " + professor.getAnoDeAdmissao() + "\n");
                    writer.write("-------------------------------------------------\n\n"); // Separar os dados de cada pessoa
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (FileWriter writer = new FileWriter("Disciplinas.txt")) {
            writer.write("------------------- Disciplinas ----------------------- \n");

            for (Disciplina disciplina : disciplinas) {
                writer.write("Nome: " + disciplina.getNome() + "\n");
                writer.write("Carga Horaria: " + disciplina.getCargaHoraria() + "\n");
                writer.write("Codigo: " + disciplina.getCodigo() + "\n");
                writer.write("---------------------------------------------------\n"); // Linha separadora
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}

    public static void salvarTurmas(List<Turma> turmas) {
        try (FileWriter writer = new FileWriter("Turmas.txt")) {
            writer.write("------------------- Turmas ----------------------- \n");

            for (Turma turma : turmas) {
                writer.write("Codigo: " + turma.getCodigo() + "\n");
                writer.write("Disciplina: " + turma.getDisciplina() + "\n");
                writer.write("Ano Letivo: " + turma.getAnoLetivo() + "\n");
                writer.write("Professor Responsável: " + turma.getProfessor() + "\n");
                writer.write("-------------------------------------------------\n\n"); // Separar os dados
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void salvarNotas(List<Aluno> alunos) {
        try (FileWriter writer = new FileWriter("Notas.txt", true)) { // Escreve o cabeçalho do relatório de notas somente na primeira vez
            File file = new File("Notas.txt");
            if (file.length() == 0) { 
                writer.write("------------------- Notas ----------------------- \n"); // Verifica se o arquivo está vazio para adicionar o cabeçalho
            }

            // Itera sobre cada aluno
            for (Aluno aluno : alunos) {
                // Para cada aluno, escreve as notas de cada disciplina
                for (Nota nota : aluno.getNotas()) {
                    // Escreve o nome do aluno, nome da disciplina e a nota
                    writer.write("Nome: " + aluno.getNome() + "\n");
                    writer.write("Disciplina: " + nota.getDisciplina().getNome() + "\n");
                    writer.write("Nota: " + nota.getValor() + "\n");
                    writer.write("-------------------------------------------------\n"); // Separar as notas de cada aluno
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerar relatório de notas: " + e.getMessage()); // mensagem de erro
        }
    }

}
