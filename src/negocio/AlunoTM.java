/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.time.LocalDate;
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
public class AlunoTM {
    
    private final Aluno aluno;
    private final IntegerProperty cod;
    private final StringProperty nome;
    private final ObjectProperty<LocalDate> nsc;
    private final StringProperty cpf;
    private final StringProperty rg;
    private final StringProperty fone;
    private final ObjectProperty<LocalDate> dtmat;

    public AlunoTM(Aluno aluno) {
        this.aluno = aluno;
        cod = new SimpleIntegerProperty(aluno.getCod());
        nome = new SimpleStringProperty(aluno.getNome());
        nsc = new SimpleObjectProperty<>(aluno.getNsc());
        cpf = new SimpleStringProperty(aluno.getCpf());
        rg = new SimpleStringProperty(aluno.getRg());
        fone = new SimpleStringProperty(aluno.getFone());
        dtmat = new SimpleObjectProperty<>(aluno.getDtmat());
    }

    public Aluno getAluno() {
        return aluno;
    }

    public int getCod() {
        return cod.get();
    }

    public String getNome() {
        return nome.get();
    }

    public LocalDate getNsc() {
        return nsc.get();
    }

    public String getCpf() {
        return cpf.get();
    }

    public String getRg() {
        return rg.get();
    }

    public String getFone() {
        return fone.get();
    }

    public LocalDate getDtmat() {
        return dtmat.get();
    }
    
    

    
    
    
}
