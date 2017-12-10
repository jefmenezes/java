/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jeferson
 */
public class QuestaoTM {
    
    private final Questao questao;
    private final IntegerProperty id;
    private final StringProperty resposta;
    private ObjectProperty<Disciplina> disciplina;
    private ObjectProperty<Area> area;

    public QuestaoTM(Questao questao) {
        this.questao = questao;
        this.id = new SimpleIntegerProperty(questao.getId());
        this.resposta = new SimpleStringProperty(questao.getResposta());
        this.disciplina = new SimpleObjectProperty<>(questao.getDisciplina());
        this.area = new SimpleObjectProperty<>(questao.getDisciplina().getArea());
    }
    
    public int getId(){
        return this.id.get();
    }
    
    public String getResposta(){
        return this.resposta.get();
    }
    
    public Disciplina getDisciplina(){
        return this.disciplina.get();
    }
    
    public Area getArea(){
        return this.area.get();
    }
    
    public Questao getQuestao(){
        return this.questao;
    }
    
    public void setDisciplina(Disciplina d){
        questao.setDisciplina(d);
        disciplina = new SimpleObjectProperty<>(questao.getDisciplina());
        area = new SimpleObjectProperty<>(questao.getDisciplina().getArea());
    }
}
