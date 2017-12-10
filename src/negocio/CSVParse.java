/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeferson
 */
public class CSVParse {
    
    private List<Aluno> alunos;
    
    public List<Aluno> parse(File f){
        BufferedReader reader = null;
        try {
            alunos = new ArrayList<>();
            reader = new BufferedReader(new FileReader(f));
            while(reader.ready()){
                try{
                    String linha = reader.readLine();
                    String[] d = linha.split(",");
                    int cd = Integer.parseInt(d[0]);
                    String nm = d[1];
                    LocalDate nsc = parseDate(d[2]);
                    String cpf = d[3];
                    String rg = d[4];
                    String fone = d[5];
                    LocalDate mat = parseDate(d[6]);
                    Aluno a = new Aluno(cd, nm, nsc, cpf, rg, fone, mat);
                    alunos.add(a);
                }catch(NumberFormatException e){};
            }
            return alunos;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ArrayList<>();
    }
    
//    public List<Questao> parseGabarito(File f){
//        BufferedReader reader = null;
//        try {
//            List<Questao> questoes = new ArrayList<>();
//            reader = new BufferedReader(new FileReader(f));
//            while(reader.ready()){
//                try{
//                    String linha = reader.readLine();
//                    String[] d = linha.split(",");
//                    int cd = Integer.parseInt(d[0]);
//                    String nm = d[1];
//                    LocalDate nsc = parseDate(d[2]);
//                    String cpf = d[3];
//                    String rg = d[4];
//                    String fone = d[5];
//                    LocalDate mat = parseDate(d[6]);
//                    Aluno a = new Aluno(cd, nm, nsc, cpf, rg, fone, mat);
//                    alunos.add(a);
//                }catch(NumberFormatException e){};
//            }
//            return alunos;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException ex) {
//                Logger.getLogger(CSVParse.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return new ArrayList<>();
//    }
    
    private LocalDate parseDate(String dt){
        String[] c = dt.split("/");
        String d = c[2]+"-"+c[1]+"-"+c[0];
        return LocalDate.parse(d);
    }
    
}
