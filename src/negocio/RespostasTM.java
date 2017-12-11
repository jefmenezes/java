/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jeferson
 */
public class RespostasTM {

    private final Respostas respostas;
    private final StringProperty nome;
    private ObjectProperty<Respostas>  resp;
//    private List<String> columnames = new ArrayList<>();
//    private TableColumn[] columns;

    public RespostasTM(Respostas respostas){
        this.respostas = respostas;
        nome = new SimpleStringProperty(respostas.getAluno().getNome());
        resp = new SimpleObjectProperty<>(respostas);
//        int nc = respostas.getQuestoes().size()+1;
//        columns = new TableColumn[nc];
//        for(int i=1; i<nc; i++){
//            columnames.add("" + i);
//            TableColumn col = new TableColumn(columnames.get(i-1));
//            col.setCellValueFactory((Callback) resp.get().getQuestao(i-1));
////            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<Questao>>() {
////                public ObservableValue<Questao> call(CellDataFeatures<ObservableList, String> param) {
////                    return new SimpleStringProperty(param.getValue().get(j).toString());
////                }
////            });
//        }
    }
    
    public String getNome(){
        return nome.get();
    }
    
    public Respostas getRespostas(){
        return resp.get();
    }
    
    public void corrige(List<QuestaoTM> gabarito){
        for(QuestaoTM q : gabarito){
            Questao x = resp.get().getQuestao(q.getId());
            if(x==null)
                return;
            if(x.getResposta().equals(q.getResposta()))
                x.setCerto(true);
            else
                x.setCerto(false);
            x.setDisciplina(q.getDisciplina());
        }
        resp = new SimpleObjectProperty<>(respostas);
    }

//    public TableColumn[] getColumns() {
//        return columns;
//    }
    
    
    
}
