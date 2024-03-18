package org.example.viewplan2;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.viewplan2.database.NetworkService;
import org.example.viewplan2.models.Humans;
import org.example.viewplan2.models.Material;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox vBox;

    private ObservableList<GridPane> gridPanes = FXCollections.observableArrayList();
    private ObservableList<ObservableList<Circle>> listOfListOfCircles = FXCollections.observableArrayList();

    private Label label;

    public void initialize(){
        System.out.println("ASd");
        findAllGridPlanes(vBox);
        System.out.println(gridPanes.size());
    }

    public void findAllGridPlanes(Parent parent){
        for (Node node : parent.getChildrenUnmodifiable()){
            if (node instanceof GridPane){
                gridPanes.add((GridPane) node);
            }else if (node instanceof Parent){
                findAllGridPlanes((Parent) node);
            }
        }
    }

    public Circle createCircle(int i){
        Circle circle = new Circle();
        circle.setRadius(8);
        switch (i){
            case 1:
                circle.setFill(Color.RED);
                break;
            case 0:
                circle.setFill(Color.GREEN);
                break;
        }
        return circle;
    }

    public void addCircle(Circle circle, ObservableList<Circle> circles){
        circles.add(circle);
    }

    public void addCircleInGrid(int room){
        int vertical = 2;
        int horizontal = 3;
        int index = 0;

        for (int i = 0; i < vertical; i++){
            for (int j = 0; j < horizontal; j++){
                if (index < listOfListOfCircles.get(room).size()){
                    gridPanes.get(room).add(listOfListOfCircles.get(room).get(index), i, j);
                }
                else if (index == listOfListOfCircles.get(room).size()){
                    break;
                }
                index++;
            }
        }
    }

    public void start (){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NetworkService.getInctance().getApi().getHumans().enqueue(new Callback<List<Humans>>() {
                    @Override
                    public void onResponse(Call<List<Humans>> call, Response<List<Humans>> response) {
                        List<Humans> humansList = response.body();
                            Platform.runLater(() -> {
                                listOfListOfCircles.clear();
                                for (int i = 0; i < gridPanes.size(); i++){
                                    listOfListOfCircles.add(FXCollections.observableArrayList());
                                }
                                for (Humans humans : humansList){
                                    if (humans.getRole().equals("Doctor")){
                                        addCircle(createCircle(1), listOfListOfCircles.get(humans.getRoom()));
                                    }
                                    else if (humans.getRole().equals("Patient")){
                                        addCircle(createCircle(0), listOfListOfCircles.get(humans.getRoom()));
                                    }
                                }

                                for (GridPane gridPane : gridPanes){
                                    gridPane.getChildren().clear();
                                }
                                for (int i = 0; i < gridPanes.size(); i ++){
                                    addCircleInGrid(i);
                                }
                                System.out.println("Done");
                            });

                    }

                    @Override
                    public void onFailure(Call<List<Humans>> call, Throwable throwable) {

                    }
                });
            }
        }, 0, 3000);
    }



}