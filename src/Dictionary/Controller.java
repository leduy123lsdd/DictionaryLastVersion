package Dictionary;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;

public class Controller implements Initializable {

    /**
     * ham chay dau tien khi class khoi tao
     */
    public Controller(){
        System.out.println("runing");
    }

    /**
     * duong dan data
     */
    private String filePathEtoV = "Data/anhviet109K.index";
    private String dataPathEtoV = "Data/anhviet109K.dict";
    private String filePathVtoE = "Data/vietanh.index";
    private String dataPathVtoE = "Data/vietanh.dict";
    private TranslateEV test;
    private String[] word;

    /**
     * thuoc tinh lien ket voi fxml file
     */
    @FXML private TextField textField;
    @FXML public CheckBox cbEtoV,cbVtoE;
    @FXML private TextArea textArea;
    @FXML private ImageView Speaker;
    @FXML private ListView<String> listView;

    /**
     * nut search kich hoat
     * @param event
     */
    public void searchButton(ActionEvent event) {
        searchContent();
    }
    private void searchContent() {
        String key = textField.getText();
        test.englishToVietnameseOn(key);

        System.out.println(test.map);
        textArea.clear();
        if(test.founded == false) {
            textArea.setStyle("-fx-font-size: 30");
            if(cbEtoV.isSelected()){
                textArea.setText("\nNo value.\nOr changes kind of translate.");
            } else {
                textArea.setText("\nKhông có dữ liệu.\nHoặc hãy chuyển đổi kiểu dịch.");
            }
        } else {
            textArea.setStyle("-fx-font-size: 20");
            textArea.setText("\n" + test.map.get(textField.getText()));
        }
    }

    /**
     * bo check khi 1 trong 2 tuy chon ngon ngu duoc chon
     * @param e
     */
    public void checkEventEtoV(ActionEvent e){
        if(cbEtoV.isSelected()) {
            cbVtoE.setSelected(false);
            test = new TranslateEV(filePathEtoV,dataPathEtoV);

        }
    }
    public void checkEventVtoE(ActionEvent e){
        if(cbVtoE.isSelected()) {
            cbEtoV.setSelected(false);
            test = new TranslateEV(filePathVtoE,dataPathVtoE);
        }
    }

    /**
     * an enter de kich hoat tim kiem
     * @param ae
     */
    public void onEnter(ActionEvent ae){ searchButton(ae); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialized");
        if(cbEtoV.isSelected()) {
            test = new TranslateEV(filePathEtoV,dataPathEtoV);
        }
        if(cbVtoE.isSelected()) { test = new TranslateEV(filePathVtoE,dataPathVtoE); }
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 20");
        Speaker.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Speaker pressed ");
            TextToSpeech test = new TextToSpeech();
            test.speak(textField.getText(),1.0f,false,false);
            event.consume();
        });
        Speaker.setImage(new Image("Dictionary/icons8-speaker-50.png"));

        textField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                searchContent();

            }
        });

    }

}
