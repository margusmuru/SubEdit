package defaultPackage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by margus@tablet on 10.11.2015.
 */
public class Main extends Application{

    Stage window;
    Button replaceButton, breakButton;
    TextArea sourceText, resultText;
    CheckBox toLowCase, breakLines;
    public static void main(String[] args){
        launch(args);

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Editor");
/*
        //runs when you press window close button
        window.setOnCloseRequest(e -> {
            //take over app close command.
            e.consume();
            closeProgram();
        });
*/
        sourceText = new TextArea();
        resultText = new TextArea();

        replaceButton = new Button("Convert to compact");
        replaceButton.setOnAction(event -> {
            //what to do when button is clicked
            replaceText();
        });
        breakButton = new Button("Break lines");
        breakButton.setOnAction(event -> {
            breakLines();
        });
        toLowCase = new CheckBox("To Lowercase");
        breakLines = new CheckBox("Unbreak Lines");
        //layout for the whole app
        VBox layout = new VBox();
        layout.getChildren().addAll(sourceText, replaceButton, toLowCase, breakLines, resultText);
        VBox.setVgrow(resultText, Priority.ALWAYS);
        //set scene
        Scene scene = new Scene(layout, 800, 600);

        window.setScene(scene);
        window.show();
    }


    @SuppressWarnings("Duplicates")
    private void replaceText(){
        String[] lines = sourceText.getText().split("\n");
        StringBuilder builder = new StringBuilder();

        int counter = 0;

        for(int i = 0; i < lines.length; i++){

            switch (counter)
            {
                case 0:
                    if (toLowCase.isSelected()){
                        builder.append(lines[i].toLowerCase() + "\b ");
                    }else{
                        builder.append(lines[i] + "\b ");
                    }

                    counter++;
                    break;
                case 1:
                    if (toLowCase.isSelected()){
                        builder.append(lines[i].toLowerCase() + "\n");
                    }else{
                        builder.append(lines[i] + "\n");
                    }

                    counter++;
                    break;
                default:
                    if (lines[i].length() > 0){
                        if  (counter == 2){
                            if (toLowCase.isSelected()){
                                lines[i] = lines[i].substring(0, 1).toUpperCase() + lines[i].substring(1,lines[i].length()).toLowerCase();
                            }

                            builder.append(lines[i]);
                            if (!breakLines.isSelected()){
                                builder.append("\n");
                            }else{
                                builder.append(" ");
                            }
                            counter++;
                        }else{
                            if (toLowCase.isSelected()){
                                builder.append(lines[i].toLowerCase() + " ");
                            }else{
                                builder.append(lines[i] + " ");
                            }

                        }
                    }else{
                        counter = 0;
                        builder.append("\n\n");
                    }
                    break;
            }


        }
        resultText.setText(builder.toString());
    }

    private void breakLines(){
        String[] lines = sourceText.getText().split("\n");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lines.length; i++){

        }
    }

}
