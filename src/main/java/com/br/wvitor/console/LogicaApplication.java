package com.br.wvitor.console;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.wvitor.console.interfaces.IEntidade;
import com.br.wvitor.console.models.Aluno;
import com.br.wvitor.console.models.Escola;
import com.br.wvitor.console.models.Professor;
import com.br.wvitor.console.service.PersistenciaCSV;
import com.br.wvitor.console.service.PersistenciaJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@SpringBootApplication
public class LogicaApplication {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args)
			throws IOException, InterruptedException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		var a = new Aluno();
		a.setNome("Renata Gonçalves");
		var notas = new ArrayList<Float>();
		float nota = 9;
		notas.add(nota);
		a.setNotas(notas);
		// a.salvar(new PersistenciaCSV(a.getClass()));
		// a.salvar(new PersistenciaJson(a.getClass()));
		new PersistenciaCSV(a.getClass()).salvar(a);
		new PersistenciaJson(a.getClass()).salvar(a);

		var prof = new Professor();
		prof.setNome("Daiane dos Santos");
		prof.salvar();

		var escola = new Escola();
		escola.setNome("Daiane dos Santos");
		escola.setEndereco("Rua das Nações Unidas, 123 - São Paulo/SP");
		new PersistenciaJson(escola.getClass()).salvar(escola);

		while (true) {
			limparTela();

			System.out.println("=====[Cadastro de Alunos]=====");
			System.out.println("Qual Opção Deseja Executar?:");
			System.out.println("1 - Cadastrar Aluno");
			System.out.println("2 - Mostrar Relatório");
			System.out.println("3 - Sair");

			int opcao = 0;
			try {
				opcao = Integer.parseInt(reader.readLine());
			} catch (Exception e) {
			}

			limparTela();

			var sair = false;
			switch (opcao) {
				case 1:
					cadastroAluno();
					break;
				case 2:
					mostrarAlunos();
					break;
				case 3:
					sair = true;
					break;
				default:
					opcaoInvalida();
					break;
			}
			if (sair)
				break;
		}
	}

	private static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private static void opcaoInvalida() throws InterruptedException {
		mensagem("Opção Inválida!");
	}

	private static void mostrarAlunos() throws InterruptedException {
		if (Aluno.all().size() == 0) {
			mensagem("Nenhum Aluno cadastrado!");
			return;
		}
		System.out.println("========= [Relatório de Alunos] =========");
		for (IEntidade entidade : Aluno.all()) {
			var aluno = (Aluno) entidade;
			System.out.println("Nome: " + aluno.getNome());
			String notas = " ";
			for (float nota : aluno.getNotas()) {
				notas += nota + ", ";
			}
			System.out.println("Notas: " + notas);
			System.out.println("Média: " + String.format("%.2f", aluno.media()));
			System.out.println("Situação: " + aluno.situacao());
			System.out.println("------------------------------------------");
		}
		espera(8);
		limparTela();
	}

	private static void cadastroAluno()
			throws IOException, InterruptedException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		var aluno = new Aluno();
		System.out.println("Informe o Nome do Aluno(a)");
		aluno.setNome(reader.readLine());

		capturaNotasAluno(aluno);
		aluno.salvar(new PersistenciaJson(aluno.getClass()));
		mensagem("Aluno cadastrado com sucesso!");
	}

	private static void capturaNotasAluno(Aluno aluno) throws NumberFormatException, IOException, InterruptedException {
		System.out.println("Informe a nota do(a) " + aluno.getNome());
		if (aluno.getNotas() == null)
			aluno.setNotas(new ArrayList<Float>());
		try {
			aluno.getNotas().add(Float.parseFloat(reader.readLine()));
		} catch (Exception e) {
			mensagem("Nota Inválida!");
			capturaNotasAluno(aluno);
		}

		try {
			System.out.println("Digite 1 para cadastrar mais notas ou 0 para finalizar o cadastro");
			int opcao = Integer.parseInt(reader.readLine());
			if (opcao == 1)
				capturaNotasAluno(aluno);
			return;

		} catch (Exception e) {
			mensagem("Opção Inválida! Tente novamente!");
			capturaNotasAluno(aluno);
		}
	}

	private static void mensagem(String string) throws InterruptedException {
		limparTela();
		System.out.println(string);
		espera(2);
		limparTela();
	}

	private static void espera(int secconds) throws InterruptedException {
		Thread.sleep(secconds * 1000);
	}

	// SpringApplication.run(LogicaApplication.class, args);

}
