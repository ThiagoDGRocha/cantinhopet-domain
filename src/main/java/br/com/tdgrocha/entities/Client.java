package br.com.tdgrocha.entities;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "clientSeq",
        sequenceName = "client_id_seq",
        allocationSize = 1,
        initialValue = 1)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSeq")
    private Integer id;

    private String nome;

    public Client() { }

    public Client(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
