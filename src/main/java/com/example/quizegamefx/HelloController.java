package com.example.quizegamefx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelloController {
    public Stage stage;

    public void onJavaClicked(MouseEvent mouseEvent) throws IOException, ParseException {
        File file =new File("src/main/java/com/example/quizegamefx/java.json");
        Scanner sc=new Scanner(file);
        String str="";
        while(sc.hasNext()){str+=sc.nextLine();}
        JSONParser parser=new JSONParser();
        JSONArray array=(JSONArray) parser.parse(str);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Parent root1= loader.load();
        Scene scene = new Scene(root1);
        stage=HelloApplication.stg;
        stage.setScene(scene);
        stage.show();
        Quiz quiz=loader.getController();
        quiz.startQuiz(stage,array,0);
    }

    public void onDbClicked(MouseEvent mouseEvent) throws IOException, ParseException {
        File file =new File("src/db.json");
        Scanner sc=new Scanner(file);
        String str="";
        while(sc.hasNext()){str+=sc.nextLine();}
        JSONParser parser=new JSONParser();
        JSONArray array=(JSONArray) parser.parse(str);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Parent root1= loader.load();
        Scene scene = new Scene(root1);
        stage=HelloApplication.stg;
        stage.setScene(scene);
        stage.show();
        Quiz quiz=loader.getController();
        quiz.startQuiz(stage,array,0);
    }

    public void onNetworkingClicked(MouseEvent mouseEvent) throws IOException, ParseException {
        File file =new File("src/networking.json");
        Scanner sc=new Scanner(file);
        String str="";
        while(sc.hasNext()){str+=sc.nextLine();}
        JSONParser parser=new JSONParser();
        JSONArray array=(JSONArray) parser.parse(str);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Quiz.fxml"));
        Parent root1= loader.load();
        Scene scene = new Scene(root1);
        stage=HelloApplication.stg;
        stage.setScene(scene);
        stage.show();
        Quiz quiz=loader.getController();
        quiz.startQuiz(stage,array,0);

    }

    public void onCustomClicked(MouseEvent mouseEvent) {
    }

    public void onResult(MouseEvent mouseEvent) throws IOException, ParseException {
        File file=new File("src/result.json");
        Scanner sc=new Scanner(file);
        String str="";
        while(sc.hasNext()){str+=sc.nextLine();  }
        JSONParser parser=new JSONParser();
        JSONArray array=(JSONArray) parser.parse(str);
        FXMLLoader loader =new FXMLLoader(getClass().getResource("result.fxml"));
        Parent root1= loader.load();
        Scene scene = new Scene(root1);
        stage=HelloApplication.stg;
        stage.setScene(scene);
        stage.show();
        Result result=loader.getController();
        result.showResult(stage,array);
    }
}