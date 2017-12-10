/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeferson
 */
public class Area implements Serializable{
    
    private String id;
    private final List<Disciplina> disciplinas;

    public Area(String id) {
        this.id = id;
        this.disciplinas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    public void addDisciplina(Disciplina d){
        this.disciplinas.add(d);
    }
    
    public void remDisciplina(Disciplina d){
        this.disciplinas.remove(d);
    }

    @Override
    public String toString() {
        return this.id;
    }
    
    
    
}
