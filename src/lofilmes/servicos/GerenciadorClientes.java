package lofilmes.servicos;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import lofilmes.modelos.Cliente;

public class GerenciadorClientes {
	private Scanner scan = new Scanner(System.in);
	private Random random = new Random();
	private final long LIMITE_ID = 100000;
	private HashSet<Long> idsGerados = new HashSet<>();

	// a verificação do nome ou sobrenome vazios, quando cai nela, tá vindo antes
	// da linha 20. Ver como arrumar isso depois, mas a função em si tá funcionando
	// certinho!

	public Cliente criarCliente() {
		while (true) {
			System.out.println("Antes de continuar, digite seu nome:");
			String nome = scan.nextLine().trim();

			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			String sobrenome = scan.nextLine().trim();

			if (isVazio(nome, sobrenome)) {
				continue;
			}

			Long id = gerarIdUnico();
			return new Cliente(id, nome, sobrenome);
		}
	}

	public Cliente criarCliente2() {
		while (true) {
			System.out.println("Antes de continuar, digite seu nome:");
			String nome = scan.nextLine().trim();

			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			String sobrenome = scan.nextLine().trim();
			try {
				verificarSeEstaVazio(nome, sobrenome);

				Long id = gerarIdUnico();
				return new Cliente(id, nome, sobrenome);
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void verificarSeEstaVazio(String nome, String sobrenome) {
		if (nome.isEmpty() || sobrenome.isEmpty()) {
			throw new IllegalArgumentException("Nome e sobrenome não podem ser vazios. " + "(Nome: " + nome
					+ ", Sobrenome: " + sobrenome + "). Tente novamente.");
		}
	}

	private boolean isVazio(String nome, String sobrenome) {
		if (nome.isEmpty() || sobrenome.isEmpty()) {
			System.err.println("Nome e sobrenome não podem ser vazios. " + "(Nome: " + nome + ", Sobrenome: "
					+ sobrenome + "). Tente novamente.");
			return true;
		}
		return false;
	}

	private Long gerarIdUnico() {
		Long id;
		do {
			id = (long) (random.nextInt((int) LIMITE_ID) + 1);
		} while (idsGerados.contains(id));

		return id;
	}

}
