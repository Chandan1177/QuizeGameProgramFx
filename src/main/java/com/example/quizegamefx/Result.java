package com.example.quizegamefx;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Result {
    public VBox vbox;
    public Label resultlb;

    public void showResult(Stage s, JSONArray ar) throws FileNotFoundException {
        JSONArray arr=ar;
        JSONObject object1=(JSONObject) arr.get(0);
        int a=Integer.parseInt(String.valueOf(object1.get("rq")));
        int tq=Integer.parseInt(String.valueOf(object1.get("tq")));
       for(int i=1;i<arr.size();i++){
           JSONObject object=(JSONObject) arr.get(i);
           PrintChar(a,tq,Integer.parseInt(String.valueOf(object.get("ind"))), (String) object.get("q"), (String) object.get("result"), (String) object.get("c_ans"), (String) object.get("u_ans"));
       }
    }

    private synchronized void PrintChar(int rightqution,int totalquestion,int ind,String question,String result,String c_ans,String u_ans)
    {    resultlb.setText("Your result is : "+rightqution+"/"+totalquestion);
        HBox hBox1=new HBox();
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setPadding(new Insets(5,5,5,10));
        Text text=new Text(question);
        TextFlow textFlow=new TextFlow(text);
        textFlow.setStyle("-fx-background-color:#FFFFFF");
        Text text1=new Text(ind+"   ");
        TextFlow textFlow1=new TextFlow(text1);
        textFlow1.setStyle("-fx-background-color:#FFFFFF");
        hBox1.getChildren().addAll(textFlow1,textFlow);

        HBox c_answer=new HBox();
        c_answer.setAlignment(Pos.CENTER_LEFT);
        c_answer.setPadding(new Insets(5,5,5,10));
        Text cans=new Text("(correct ans)   "+c_ans);
        TextFlow textFlow2=new TextFlow(cans);
        textFlow2.setStyle("-fx-background-color:#FFFFFF");
        c_answer.getChildren().add(cans);

        HBox u_answer=new HBox();
        u_answer.setAlignment(Pos.CENTER_LEFT);
        u_answer.setPadding(new Insets(5,5,5,10));
        Text uans=new Text("(your ans)  "+u_ans);
        TextFlow textFlow3=new TextFlow(uans);
        textFlow3.setStyle("-fx-background-color:#FFFFFF");
        u_answer.getChildren().add(uans);
        Platform.runLater(() -> {
            vbox.getChildren().addAll(hBox1,c_answer,u_answer);
        });
    }
}
