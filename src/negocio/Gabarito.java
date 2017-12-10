/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author jeferson
 */
public class Gabarito implements Serializable {
    
    private final HashMap<Integer, Questao> respostas = new HashMap<>();
    
    public void addQuestao(Questao q){
        respostas.put(q.getId(), q);
    }
    
    public void remQuestao(int q){
        respostas.remove(q);
    }
    
    public void remQuestoes(){
        respostas.clear();
    }
    
    public Questao getQuestao(int q){
        return respostas.get(q);
    }
    
    public ArrayList<Questao> getQuestoes(){
        ArrayList<Questao> result = new ArrayList<>(respostas.values());
        Collections.sort(result);
        return result;
    }
    
}
