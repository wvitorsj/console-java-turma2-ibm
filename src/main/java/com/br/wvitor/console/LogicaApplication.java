package com.br.wvitor.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LogicaApplication {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("=====[Olá Turma 2 JAVA IBM]=====");
		System.out.println("Informe 5 Nomes");
		List<String> nomes = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.print("Informe o " + (i + 1) + "º " + "nome: ");
			String nome = reader.readLine();
			nomes.add(nome);
		}
		System.out.println("Os nomes digitados foram:");
		for (int i = 0; i < nomes.size(); i++) {
			System.out.println("O Nome " + (i + 1) + " digitados foi: " + nomes.get(i));
		}
		// SpringApplication.run(LogicaApplication.class, args);
	}
}
