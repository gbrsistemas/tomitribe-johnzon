package br.com.gbrsistemas.crvirtual.usuario;

public class Usuario {

    private Integer id;
    private String name;

    public Usuario(){

    }

    public Usuario(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
