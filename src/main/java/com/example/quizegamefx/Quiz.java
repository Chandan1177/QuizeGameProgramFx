package com.example.quizegamefx;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class Quiz implements Initializable {
    ToggleGroup toggleGroup=new ToggleGroup();
    File file;
    public Stage stage;
    public RadioButton a_rd_btn,b_rd_btn,c_rd_btn,d_rd_btn;
    public Label question_lb,question_indx_lb,leftTime;
    public ProgressBar bar;
    int ind=0,time,right;
    String question,a,b,c,d,ans;
    JSONArray array,resultArray=new JSONArray();
    JSONObject report=new JSONObject();

    public Quiz() {
    }

    public void startQuiz(Stage stage, JSONArray array,int ind) {
        this.stage=stage;
        this.ind=ind;
        this.array=array;
        JSONObject object=(JSONObject) array.get(ind);
        JSONParser parser=new JSONParser();
        question=String.valueOf(object.get("q"));
        a=String.valueOf(object.get("a"));
        b=String.valueOf(object.get("b"));
        c=String.valueOf(object.get("c"));
        d=String.valueOf(object.get("d"));
        ans=String.valueOf(object.get("ans"));
        time= Integer.parseInt(String.valueOf(object.get("time"))) ;
        showQuestion(question,a,b,c,d,ans,time);
        report.put("tq",array.size());
    }

    public void showQuestion(String q,String a,String b,String c,String d,String ans,int time){
        prosessBar(time);
        question_lb.setText(q);
        a_rd_btn.setSelected(false);
        b_rd_btn.setSelected(false);
        c_rd_btn.setSelected(false);
        d_rd_btn.setSelected(false);
        a_rd_btn.setText(a);  a_rd_btn.setUserData(a);
        b_rd_btn.setText(b);  b_rd_btn.setUserData(b);
        c_rd_btn.setText(c);  c_rd_btn.setUserData(c);
        d_rd_btn.setText(d);  d_rd_btn.setUserData(d);
        question_indx_lb.setText((ind+1)+"/"+array.size());
    }

    public void prosessBar(int time){

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for(int i=0;i<time;i++){
                    PrintChar(time-i);
                    updateProgress(time-i, time);
                    sleep(1000);
                }
                sleep(1000);
                String u_ans="";
                if(toggleGroup.getSelectedToggle()!=null){u_ans= String.valueOf((toggleGroup.getSelectedToggle().getUserData()));}
                System.out.println(u_ans);
                JSONObject res=new JSONObject();
                file=new File("src/result.json");
                res.put("ind",ind+1);
                res.put("q",question);
                res.put("c_ans",ans);
                res.put("u_ans",u_ans);
                String check;
                if(ans.equals(u_ans)) {check="Right";right++;} else {check="Wrong";};
                res.put("result",check);
                report.replace("rq",report.get("rq"),right);
                resultArray.add(res);
                try {
                    FileWriter writer=new FileWriter(file);
                    writer.write(String.valueOf(resultArray));
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PrintChar(0);
                return null;
            }
        };
        bar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    private synchronized void PrintChar(int lefttime)
    {
        Platform.runLater(() -> {
            leftTime.setText(String.valueOf(lefttime));
            if(lefttime==0){ind++;startQuiz(stage,array,ind);}
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        report.put("rq",0);
        resultArray.add(report);
        a_rd_btn.setToggleGroup(toggleGroup);
        b_rd_btn.setToggleGroup(toggleGroup);
        c_rd_btn.setToggleGroup(toggleGroup);
        d_rd_btn.setToggleGroup(toggleGroup);
    }
}
