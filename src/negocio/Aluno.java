/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author jeferson
 */
public class Aluno implements Serializable, Comparable<Object>{
    private int cod;
    private String nome;
    private LocalDate nsc;
    private String cpf;
    private String rg;
    private String fone;
    private LocalDate dtmat;

    public Aluno(int cod, String nome, LocalDate nsc, String cpf, String rg, String fone, LocalDate dtmat) {
        this.cod = cod;
        this.nome = nome;
        this.nsc = nsc;
        this.cpf = cpf;
        this.rg = rg;
        this.fone = fone;
        this.dtmat = dtmat;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNsc() {
        return nsc;
    }

    public void setNsc(LocalDate nsc) {
        this.nsc = nsc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public LocalDate getDtmat() {
        return dtmat;
    }

    public void setDtmat(LocalDate dtmat) {
        this.dtmat = dtmat;
    }       

    @Override
    public String toString() {
        return this.cod + " - "+ this.nome+" - "+this.nsc+" - "+this.cpf+" - "+this.rg+" - "+this.fone+" - "+this.dtmat;
    }

    @Override
    public int compareTo(Object o) {
        Aluno a = (Aluno) o;
        return this.getNome().compareTo(a.getNome());
    }
    
    
    
}
