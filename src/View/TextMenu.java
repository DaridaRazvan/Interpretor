package View;

import Model.ADT.DictionaryInterface;
import Model.ADT.Dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Scanner;

public class TextMenu {
    private final DictionaryInterface<String, Command> commands;

    public TextMenu(){

        commands = new Dictionary<String, Command>();
    }

    public Command getCommand(String id){
        return commands.getElementWithKey(id);
    }

    public void addCommand(Command command){
        commands.add(command.getKey(),command);
    }

    public ObservableList<String> printMenu(){
        ObservableList<String> strings = FXCollections.observableArrayList();
        //"%4s : %s"
        for(String com : commands.getDictionary().keySet()){
            String line = String.format("%s : %s",commands.getElementWithKey(com).getKey(), commands.getElementWithKey(com).getDescription());
            strings.add(line);
        }
        return strings;
    }

//    public void show(){
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            printMenu();
//            System.out.println("Input command: ");
//            String key = scanner.nextLine();
//            Command command = commands.getElementWithKey(key);
//            if(command == null){
//                System.out.println("Invalid option");
//                continue;
//            }
//            command.execute();
//        }
//    }
}
