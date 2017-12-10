/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import negocio.Aluno;
import negocio.AlunoTM;
import negocio.Area;
import negocio.CSVParse;
import negocio.Disciplina;
import negocio.Pdf;
import negocio.Questao;
import negocio.QuestaoTM;
import negocio.Respostas;
import negocio.RespostasTM;

/**
 * FXML Controller class
 *
 * @author jeferson
 */
public class MainController implements Initializable {

    @FXML
    private TableView<AlunoTM> alunoTV;
    @FXML
    private TableColumn matriculaTC;
    @FXML
    private TableColumn nomeTC;
    private final ListProperty<AlunoTM> alunos = new SimpleListProperty();
    private HashMap<Integer, Aluno> alns = new HashMap<>();

    @FXML private TableView<RespostasTM> respostasTV;
    @FXML private TableColumn respostasNomeTC;
    @FXML private Spinner<Integer> questoesSP;
    SpinnerValueFactory<Integer> valueFactory;
    private int nq = 60;
    @FXML private Spinner matriculaSP;
    private final ListProperty<RespostasTM> respostas = new SimpleListProperty<>();
    
    
    @FXML private TableView<QuestaoTM> gabaritoTV;
    @FXML private TableColumn questaoIdTC;
    @FXML private TableColumn questaoRespTC;
    @FXML private TableColumn questaoDiscTC;
    @FXML private TableColumn questaAreaTC;
    private final ListProperty<QuestaoTM> gabarito = new SimpleListProperty<>();
    
    @FXML private ComboBox<Disciplina> disciplinaCB;
    private ObservableList<Disciplina> disciplinas = FXCollections.observableArrayList();
    
    private List<Respostas> resps = new ArrayList<>();
    
    private String csvSeparador = ",";
    
    private ArrayList<Area> areas = new ArrayList<>();
    
    
    
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarAreas();
        
        alunos.set(FXCollections.observableArrayList());
        alunoTV.itemsProperty().bind(alunos);
        matriculaTC.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("cod"));
        nomeTC.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));

        respostasTV.itemsProperty().bind(respostas);
        respostasNomeTC.setCellValueFactory(new PropertyValueFactory<RespostasTM, String>("nome"));
//        System.out.println(""+Color.YELLOW);

        gabaritoTV.itemsProperty().bind(gabarito);
        gabaritoTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        questaoIdTC.setCellValueFactory(new PropertyValueFactory("id"));
        questaoRespTC.setCellValueFactory(new PropertyValueFactory("resposta"));
        questaoDiscTC.setCellValueFactory(new PropertyValueFactory("disciplina"));
        questaAreaTC.setCellValueFactory(new PropertyValueFactory("area"));
        
        
        disciplinaCB.setItems(disciplinas);
        
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, nq, 1);
        questoesSP.setValueFactory(valueFactory);
        questoesSP.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                nq = (int)newValue;
            }
        });
    }
    
    public void criarAreas(){
        Area a1 = new Area("Linguagens");
        areas.add(a1);
        Disciplina d1 = new Disciplina("Português", a1);
        a1.addDisciplina(d1);
        disciplinas.add(d1);
        Disciplina d2 = new Disciplina("Inglês", a1);
        a1.addDisciplina(d2);
        disciplinas.add(d2);
        Disciplina d3 = new Disciplina("Espanhol", a1);
        a1.addDisciplina(d3);
        disciplinas.add(d3);
        Disciplina d4 = new Disciplina("Artes", a1);
        a1.addDisciplina(d4);
        disciplinas.add(d4);
        Disciplina d5 = new Disciplina("Educação Física", a1);
        a1.addDisciplina(d5);
        disciplinas.add(d5);
        Area a2 = new Area("Ciências Humanas");
        areas.add(a2);
        Disciplina d6 = new Disciplina("Geografia", a2);
        a2.addDisciplina(d6);
        disciplinas.add(d6);
        Disciplina d7 = new Disciplina("História", a2);
        a2.addDisciplina(d7);
        disciplinas.add(d7);
        Disciplina d8= new Disciplina("Filosofia", a2);
        a2.addDisciplina(d8);
        disciplinas.add(d8);
        Disciplina d9 = new Disciplina("Sociologia", a2);
        a2.addDisciplina(d9);
        disciplinas.add(d9);
        Area a3 = new Area("Ciências da Natureza e Matemática");
        areas.add(a3);
        Disciplina d10 = new Disciplina("Biologia", a3);
        a3.addDisciplina(d10);
        disciplinas.add(d10);
        Disciplina d11 = new Disciplina("Física", a3);
        a3.addDisciplina(d11);
        disciplinas.add(d11);
        Disciplina d12 = new Disciplina("Matemática", a3);
        a3.addDisciplina(d12);
        disciplinas.add(d12);
        Disciplina d13 = new Disciplina("Química", a3);
        a3.addDisciplina(d13);
        disciplinas.add(d13);
    }
    
    @FXML
    private void setDisciplinaQuestao(){
        ObservableList<QuestaoTM> selected = gabaritoTV.getSelectionModel().getSelectedItems();
        Disciplina d = disciplinaCB.getSelectionModel().getSelectedItem();
        for(QuestaoTM qtm : selected){
            qtm.setDisciplina(d);
//            Questao q = qtm.getQuestao();
//            q.setDisciplina(d);
//            System.out.println(q.getDisciplina().toString());
        }
        gabaritoTV.refresh();
    }

    public void openClicked() {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if (file != null) {
            CSVParse parse = new CSVParse();
            alns.clear();
            for(Aluno a : parse.parse(file)){
                alns.put(a.getCod(), a);
                alunos.set(getAlunoTM(new ArrayList<Aluno>(alns.values())));
            }
        }
    }

    private ObservableList<AlunoTM> getAlunoTM(ArrayList<Aluno> alns) {
        ObservableList<AlunoTM> result = FXCollections.observableArrayList();
        for (Aluno a : alns) {
            result.add(new AlunoTM(a));
        }
        return result;
    }

    public void exportPDFClicked() {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(null);
        if (f != null) {
            Pdf pdf = new Pdf(new ArrayList<Aluno>(alns.values()), f);
        }
    }

//    @FXML
//    public void openCSVClicked() {
//        FileChooser fc = new FileChooser();
//        File f = fc.showOpenDialog(null);
//        if (f != null) {
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(f));
//                ObservableList<RespostasTM> list = FXCollections.observableArrayList();
//                String linha = br.readLine();                
//                String[] colNames = linha.split(",");
//                while(br.ready()){
//                    linha = br.readLine();
//                    String rsp = linha.substring(0, linha.length()-11);
//                    //System.out.println(rsp);
//                    String mat = linha.substring(linha.length()-11);
//                    //System.out.println(mat);
//                    
//                    //String[] col = linha.split(",");
//                    //System.out.println(""+convertToInt(mat));
//                    Aluno a = alns.get(convertToInt(mat));
////                    if(a==null)
////                        System.out.println("Aluno null");
//                    if(a!=null){
//                        Respostas rps = new Respostas(alns.get(convertToInt(mat)));
//                        ArrayList<Questao> qsts = getQuestoes(rsp);
//                        for(Questao q : qsts)
//                            rps.addQuestao(q);
////                        addQuestoes(rps, rsp);
////                        System.out.println(rps.getQuestoes().size());
//                        resps.add(rps);
//                    }
//                }
//               // respostas.set(list);
////                TableColumn[] tcs = respostas.get(0).getColumns();
////                respostasTV.getColumns().addAll(tcs);
//               // int j = respostas.get(0).getRespostas().getQuestoes().size();
////                for(int i=0; i<j; i++){
////                    TableColumn<RespostasTM, String> col = new TableColumn();
////                    
////                    //col.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRespostas().getQuestao(i).getResposta()));
////                    col.setCellValueFactory(new Callback<CellDataFeatures<RespostasTM, String>, ObservableValue<String>>() {
////                        @Override
////                        public ObservableValue<String> call(CellDataFeatures<RespostasTM, String> c) {
////                            return new SimpleStringProperty(c.getValue().getRespostas().getQuestao(1).getResposta());
////                    
////                 
////                    }   
////                        
////});
////                    respostasTV.getColumns().add(col);
////                }
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        }
//    }
    
    @FXML
    public void openCSVClicked() {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            //List<Respostas> resps = parseRespostas(f);
            resps = parseRespostas(f);
            if (resps.size() > 0) {
                respostas.set(getRespostasTMList(resps));
                int ncol = nq;//resps.get(0).getQuestoes().size();
                for (int i = 0; i < ncol; i++) {
                    final int idx = i+1;
                    TableColumn<RespostasTM, String> col = new TableColumn("Q" + idx);
                    col.setCellFactory(new Callback<TableColumn<RespostasTM, String>, TableCell<RespostasTM, String>>() {
                        @Override
                        public TableCell<RespostasTM, String> call(TableColumn<RespostasTM, String> param) {
                            TableCell cell = new TableCell<RespostasTM, String>() {
                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    setText(empty ? null : getString());
                                    
                                    if (!empty) {
                                        RespostasTM rtm = getTableView().getItems().get(getIndex());

                                        //                                    RespostasTM rtm = this.getTableRow().getItem();
                                        Questao q = rtm.getRespostas().getQuestao(idx);
                                        if(q!=null){
                                            if (q.isCerto()) {
                                                setTextFill(Color.BLACK);
                                            } else {
                                                setTextFill(Color.RED);
                                            }
                                        }
                                        if (item == null || item.equals("")) {
                                            setText("N/R");
                                            setTextFill(Color.BLUE);
                                        }
                                    }
                                   
//                                    if(item!=null && item.equals("")){
//                                        String c = ""+Color.RED;
//                                        c = c.substring(2);
//                                        c = "#"+c;
//                                        setStyle("-fx-background-color:"+c);
//                                    }else{
//                                        String c = ""+Color.WHITE;
//                                        c = c.substring(2);
//                                        c = "#"+c;
//                                        setStyle("-fx-background-color:"+c);
//                                    }
                                    //setStyle("-fx-background-color: yellow");
                                }
                                private String getString() {
                                    return getItem() == null ? "" : getItem().toString();
                                }
                            };
                            cell.setAlignment(Pos.CENTER);
                            return cell;
                        }

                    });
                    col.setCellValueFactory(new Callback<CellDataFeatures<RespostasTM, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<RespostasTM, String> c) {
                            Questao q = c.getValue().getRespostas().getQuestao(idx);
                            if(q!=null)
                                return new SimpleStringProperty(q.getResposta());
                            else
                                return new SimpleStringProperty();
                    }   
                    });
                    //col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getRespostas().getQuestao(idx).getResposta()));
                    respostasTV.getColumns().add(col);
                }
            }
        }
    }
    
    @FXML
    public void importGabaritoClicked() {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            List<Questao> qst = parseQuestoes(f);
            ObservableList<QuestaoTM> qstl = FXCollections.observableArrayList();
            gabarito.set(qstl);
            List<QuestaoTM> qtml = new ArrayList<>();
            for (Questao q : qst) {
                if(q.getDisciplina()==null)
                    q.setDisciplina(areas.get(0).getDisciplinas().get(0));
                QuestaoTM qtm = new QuestaoTM(q);
                qstl.add(qtm);
            }
        }
    }
    
    @FXML void corrige(){
        for(RespostasTM rtm : respostas){
            rtm.corrige(gabarito.get());
        }
        respostasTV.refresh();
    }
    
    private List<Respostas> parseRespostas(File f) {
        List<Respostas> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String linha = br.readLine();
            if(linha.contains(";"))
                csvSeparador = ";";
            while (br.ready()) {
                linha = br.readLine();
                linha = linha.substring(linha.indexOf(csvSeparador));
//                String rsp = linha.substring(0, 2*nq);
//                System.out.println(rsp);
//                String mat = linha.substring(2*nq);
//                System.out.println(mat);
                
                Aluno a = alns.get(getMatricula(linha));
//                if(a==null)
//                    System.out.println("Aluno não encontrado: "+convertToInt(mat));
                if (a != null) {
                    Respostas rps = new Respostas(a);
                    ArrayList<Questao> qsts = getQuestoes(linha);
                    for (Questao q : qsts) {
                        rps.addQuestao(q);
                    }
                    result.add(rps);
                }
            }   
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private List<Questao> parseQuestoes(File f){
        List<Questao> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String linha = br.readLine();
            while (br.ready()) {
                linha = br.readLine();
                String rsp = linha.substring(0, linha.length() - 11);
                result = getQuestoes(rsp);
            }   
            return result;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private ObservableList<RespostasTM> getRespostasTMList(List<Respostas> l){
        ObservableList<RespostasTM> result = FXCollections.observableArrayList();
        for(Respostas r : l){
            result.add(new RespostasTM(r));
        }
        return result;
    }
    
    @FXML
    public void print(){
        for(Respostas r : resps){
            System.out.println(r.getAluno().getNome());
            for(Questao q : r.getQuestoes()){
                System.out.print(q.toString()+" ");
            }
            System.out.println();
        }
    }
    
    public int getMatricula(String txt){
        String[] mt = txt.split(csvSeparador);
        String result = "";
        for(int i=nq+1; i<mt.length; i++){
            result = result + mt[i];
        }
        result = result.replaceAll("[^A-J]", "");
        result = result.replace("A", "0");
        result = result.replace("B", "1");
        result = result.replace("C", "2");
        result = result.replace("D", "3");
        result = result.replace("E", "4");
        result = result.replace("F", "5");
        result = result.replace("G", "6");
        result = result.replace("H", "7");
        result = result.replace("I", "8");
        result = result.replace("J", "9");
        if(result.equals(""))
            return 0;
        //System.out.println("Matricula: "+result);
        return Integer.parseInt(result);
    }
    
    public ArrayList<Questao> getQuestoes(String txt) {
        ArrayList<Questao> result = new ArrayList<>();
        String[] qs = txt.split(csvSeparador);
       // int i = 0;
        for (int i=1; i<nq+1; i++){//String s : qs) {
//            if(i==nq)
//                return result;
            //System.out.print(s);
            Questao q = new Questao(i, qs[i]);
            result.add(q);
            //i++;
        }
        //System.out.println();
        return result;
    }

}
