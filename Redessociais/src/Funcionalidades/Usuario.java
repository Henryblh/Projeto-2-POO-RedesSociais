package Funcionalidades;

import Erros.NumeroDeAmigosInvalidoException;
import Erros.RedeSocialNaoEncontradaException;
import java.util.List;
import java.util.Arrays;

public class Usuario {
    private String nome;
    private String email;
    private List<RedeSocial> redesSociais;

    public Usuario(String nome, String email, List<String> senhasRedesSociais, List<Integer> numAmigos) throws NumeroDeAmigosInvalidoException {
        if (senhasRedesSociais.size() < 2 || numAmigos.size() < 2) {
            throw new IllegalArgumentException("O usuário deve estar cadastrado em pelo menos 2 redes sociais.");
        }

        // Criando as redes sociais com as senhas e número de amigos
        redesSociais = Arrays.asList(
                new Facebook(senhasRedesSociais.get(0), numAmigos.get(0)),
                new Twitter(senhasRedesSociais.get(1), numAmigos.get(1))
        );

        // Valida se a rede social tem o método compartilhar
        boolean temCompartilhamento = redesSociais.stream().anyMatch(rede -> rede instanceof Compartilhamento);
        if (!temCompartilhamento) {
            throw new IllegalArgumentException("Pelo menos uma rede social deve permitir compartilhamento de posts.");
        }

        for (RedeSocial rede : redesSociais) {
            if (rede.numAmigos < 0) {
                throw new NumeroDeAmigosInvalidoException("O número de amigos na rede social " + rede.getClass().getSimpleName() + " é inválido.");
            }
        }

        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<RedeSocial> getRedesSociais() {
        return redesSociais;
    }

    public void executarAcoesRedesSociais() {
        try {
            for (RedeSocial rede : redesSociais) {
                System.out.println("Executando ações para: " + rede.getClass().getSimpleName());

                rede.postarFoto();
                rede.postarVideo();

                if (rede instanceof Facebook) {
                    ((Facebook) rede).fazStreaming();
                } else if (rede instanceof Twitter) {
                    ((Twitter) rede).compartilhar();
                } else if (rede instanceof GooglePlus) {
                    ((GooglePlus) rede).compartilhar();
                } else if (rede instanceof Instagram) {
                    rede.postarComentario();
                }
            }
        } catch (RedeSocialNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}