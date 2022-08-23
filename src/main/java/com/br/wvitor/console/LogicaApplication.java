package com.br.wvitor.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@SpringBootApplication
public class LogicaApplication {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int i;
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println("=====[Olá Turma 2 JAVA IBM]=====");
		String[] nome = new String[5];
		System.out.println("Informe 5 Nomes");
		for (i = 0; i < 5; i++) {
			System.out.print("Informe o " + (i + 1) + "º " + "nome: ");
			nome[i] = reader.readLine();
		}

		System.out.println("");
		System.out.println("Os nomes digitados foram:");
		System.out.println(Arrays.toString(nome));
		// SpringApplication.run(LogicaApplication.class, args);

	}

}
