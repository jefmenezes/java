/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;

/**
 *
 * @author jeferson
 */
public class Questao implements Serializable, Comparable<Object> {
    
    private int id;
    private String resposta;
    private boolean certo = false;
    private Disciplina disciplina;

    public Questao(int id, String resposta) {
        this.id = id;
        this.resposta = resposta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isCerto() {
        return certo;
    }

    public void setCerto(boolean certo) {
        this.certo = certo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    @Override
    public int compareTo(Object o) {
        Questao q = (Questao) o;
        Integer i = this.getId();
        return i.compareTo(q.getId());
    }

    @Override
    public String toString() {
        return ""+id+": "+resposta;
    }
    
    
    
}
