package Funcionalidades;

public abstract class RedeSocial {
    protected String senha;
    protected int numAmigos;

    public RedeSocial(String senha, int numAmigos) {
        this.senha = senha;
        this.numAmigos = numAmigos;
    }

    // Métodos concretos
    public void curtirPublicacao() {
        System.out.println("Curtiu uma publicação!");
    }

    public void postarComentario() {
        System.out.println("Postou um comentário!");
    }

    // Métodos abstratos
    public abstract void postarFoto();
    public abstract void postarVideo();
}
