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
public class Disciplina implements Serializable{
    
    private String id;
    private Area area;

    public Disciplina(String id, Area a) {
        this.id = id;
        this.area = a;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return this.id;
    }
    
    
    
}
