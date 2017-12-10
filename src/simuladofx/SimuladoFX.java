/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladofx;

import GUI.MainController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import negocio.Aluno;
import negocio.CSVParse;

/**
 *
 * @author jeferson
 */
public class SimuladoFX extends Application {
    
    @Override
    public void start(Stage primaryStage){
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                //System.out.println("Hello World!");
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Abrir");
//                File file =  fileChooser.showOpenDialog(primaryStage);
//                CSVParse parse = new CSVParse();
//                ArrayList<Aluno> alunos = new ArrayList<>(parse.parse(file));         
//                for(Aluno a : alunos){
//                    System.out.println(a);
//                }
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("Main.fxml"));            
            AnchorPane  pane = (AnchorPane) loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
