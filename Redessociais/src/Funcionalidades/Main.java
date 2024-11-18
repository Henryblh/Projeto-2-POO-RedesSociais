package Funcionalidades;

import Erros.NumeroDeAmigosInvalidoException;
import Erros.RedeSocialNaoEncontradaException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            // Criando instâncias do usuário e suas redes sociais
            Usuario usuario = new Usuario("Henrique Bicalho", "henrique.bicalho@ges.inatel.br",
                    Arrays.asList("minecraft123321", "pipipipopopo"), Arrays.asList(500, 300));

            System.out.println("Nome do Usuário: " + usuario.getNome());
            System.out.println("Email do Usuário: " + usuario.getEmail());
            System.out.println("Redes sociais do Usuário:");

            // Executando as ações de todas as redes sociais do usuário
            usuario.executarAcoesRedesSociais();
        } catch (NumeroDeAmigosInvalidoException e) {
            System.out.println("Erro ao criar o usuário: " + e.getMessage());
        } catch (RedeSocialNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}