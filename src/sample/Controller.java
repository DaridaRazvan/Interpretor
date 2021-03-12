package sample;

import Model.ADT.Dictionary;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.Value.Value;
import View.Command;
import View.TextMenu;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    String programId;
    Command c;
    Interpretor interpretor = new Interpretor();
    TextMenu menu = interpretor.start();
    boolean pressed = false;

    @FXML
    TextField stateId;

    @FXML
    ListView<String> listView;

    @FXML
    ListView<String> outputView;

    @FXML
    ListView<String> executionStackView;

    @FXML
    ListView<String> programStatesView;

    @FXML
    ListView<String> fileTableView;

    @FXML
    TableView<Map.Entry<Integer,Value>> heapTableView;

    @FXML
    TableView<Map.Entry<String,Value>> symbolTableView;

    @FXML
    TableColumn<Map.Entry<String,Value>,String> name_symtbl, value_symtbl;

    @FXML
    TableColumn<Map.Entry<Integer,Value>,String> adress_heap, value_heap;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void initialize(){
        listView.setItems(getPrograms());

        // To set selection model
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Select item at index = 1
        listView.getSelectionModel().selectIndices(1);

        // Focus
        listView.getFocusModel().focus(2);

        name_symtbl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        value_symtbl.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().toString()));

        adress_heap.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().toString()));
        value_heap.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().toString()));

        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        //alert.setContentText("Program is over");

    }

    public void onMousePressed(){
        //System.out.println("Mouse pressed!");
        int selectedId=programStatesView.getSelectionModel().getSelectedIndex();
        String id = programStatesView.getItems().get(selectedId);
        System.out.println(id);

        List<ProgramState> pList = c.getList();

        for(ProgramState pState : pList){
            //System.out.println(pState.getProgramStateId().toString());
            if(pState.getProgramStateId().toString().equals(id)){
                //System.out.println("asd");

                stateId.setText(id.toString());

                ObservableList<String> list = FXCollections.observableArrayList();
                list.add(pState.getExecutionStack().toString());
                executionStackView.setItems(list);

                symbolTableView.getItems().clear();
                Map<String, Value> symbtbl = pState.getSymbolTable().getDictionary();

                for(Map.Entry<String,Value> pair : symbtbl.entrySet())
                    symbolTableView.getItems().add(pair);

                break;
            }
        }
    }

    private ObservableList<String> getPrograms(){

        ObservableList<String> commands = menu.printMenu();

        return commands;
    }

    public void PressedRun(){
        int selectedId=listView.getSelectionModel().getSelectedIndex();
        String id = listView.getItems().get(selectedId);

        String[] parts = id.split(" ");
        id = parts[0];

        programId = id;
        System.out.println(programId);

        c = menu.getCommand(programId);
    }

    public void OneStep(){
        ProgramState state;

        try {
            if (pressed == false) {
                List<ProgramState> pList = c.getList();
                state = pList.get(0);
                c.execute(true);
                //state = c.execute(true);
                pressed = true;
            } else {
                //state = c.execute(false);
                List<ProgramState> pList = c.getList();
                state = pList.get(0);
                c.execute(false);
            }

            ObservableList<String> obsList = FXCollections.observableArrayList();
            obsList.add(state.getOutputConsole().toString());
            outputView.setItems(obsList);

            ObservableList<String> obsList2 = FXCollections.observableArrayList();
            obsList2.add(state.getExecutionStack().toString());
            executionStackView.setItems(obsList2);

            ObservableList<String> obsList3 = FXCollections.observableArrayList();
            obsList3.add(state.getFileTable().toString());
            fileTableView.setItems(obsList3);

            symbolTableView.getItems().clear();
            Map<String, Value> symbtbl = state.getSymbolTable().getDictionary();

            for(Map.Entry<String,Value> pair : symbtbl.entrySet())
                symbolTableView.getItems().add(pair);

            heapTableView.getItems().clear();
            Map<Integer, Value> heaptbl = state.getHeap().getContent();

            for(Map.Entry<Integer,Value> pair: heaptbl.entrySet())
                heapTableView.getItems().add(pair);

            AtomicInteger id = state.getProgramStateId();
            stateId.setText(id.toString());

            int lastId = state.getLastProgramState().get();
            ObservableList<String> obslist4 = FXCollections.observableArrayList();
            for(int number = 1; number <= lastId; number++){
                obslist4.add( String.valueOf(number));
            }
            programStatesView.setItems(obslist4);

        }
        catch(Exception e){
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
