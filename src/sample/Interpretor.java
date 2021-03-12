package sample;

import Controller.Controller;
import Model.ADT.*;
import Model.Expresion.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Types.*;
import Model.Value.*;
import Repository.Repository;
import Repository.RepositoryInterface;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;

import java.io.BufferedReader;

public class Interpretor {

    public Interpretor(){ }

    public TextMenu start(){

        StackInterface<Statement> stack1 = new StackImp<>();

        Statement ex1= new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        stack1.push(ex1);
        ProgramState state1 = new ProgramState(stack1,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository1 = new Repository("log1.txt");
        Controller controller1 = new Controller(repository1);
        controller1.addProgramState(state1);
        controller1.setDisplayFlag(true);


        StackInterface<Statement> stack2 = new StackImp<>();
        Statement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        stack2.push(ex2);
        ProgramState state2 = new ProgramState(stack2,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository2 = new Repository("log2.txt");
        Controller controller2 = new Controller(repository2);
        controller2.addProgramState(state2);
        controller2.setDisplayFlag(true);


        StackInterface<Statement> stack3 = new StackImp<>();
        Statement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        stack3.push(ex3);
        ProgramState state3 = new ProgramState(stack3,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository3 = new Repository("log3.txt");
        Controller controller3 = new Controller(repository3);
        controller3.addProgramState(state3);
        controller3.setDisplayFlag(true);


        StackInterface<Statement> stack4 = new StackImp<>();
        Statement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("v",new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("v",new ValueExpression(new StringValue("asd"))),
                        new PrintStatement(new VariableExpression("v"))));
        stack4.push(ex4);
        ProgramState state4 = new ProgramState(stack4,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository4 = new Repository("log4.txt");
        Controller controller4 = new Controller(repository4);
        controller4.addProgramState(state4);
        controller4.setDisplayFlag(true);


        StackInterface<Statement> stack5 = new StackImp<>();
        Statement ex5 = new CompoundStatement(
                new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(
                new AssignmentStatement("varf", new ValueExpression((new StringValue("test.in")))), new CompoundStatement(
                new OpenFileStatement(new VariableExpression("varf")), new CompoundStatement(
                new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(
                new ReadFileStatement(new VariableExpression("varf"),"varc"), new CompoundStatement(
                new PrintStatement(new VariableExpression("varc")) , new CompoundStatement(
                new ReadFileStatement(new VariableExpression("varf"),"varc"), new CompoundStatement(
                new PrintStatement(new VariableExpression("varc")) , new CloseFileStatement(new VariableExpression("varf"))))))))));
        stack5.push(ex5);
        ProgramState state5 = new ProgramState(stack5,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository5 = new Repository("log5.txt");
        Controller controller5 = new Controller(repository5);
        controller5.addProgramState(state5);
        controller5.setDisplayFlag(true);


        StackInterface<Statement> stack6 = new StackImp<>();
        Statement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v",new RefType(new IntType())), new CompoundStatement(
                new AllocateHeapStatement("v",new ValueExpression(new IntValue(20))), new CompoundStatement(
                new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))), new CompoundStatement(
                new AllocateHeapStatement("a",new VariableExpression("v")), new CompoundStatement(
                new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        stack6.push(ex6);
        ProgramState state6 = new ProgramState(stack6,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository6 = new Repository("log6.txt");
        Controller controller6 = new Controller(repository6);
        controller6.addProgramState(state6);
        controller6.setDisplayFlag(true);


        StackInterface<Statement> stack7 = new StackImp<>();
        Statement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v",new RefType(new IntType())), new CompoundStatement(
                new AllocateHeapStatement("v",new ValueExpression(new IntValue(20))), new CompoundStatement(
                new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))), new CompoundStatement(
                new AllocateHeapStatement("a",new VariableExpression("v")), new CompoundStatement(
                new AllocateHeapStatement("v",new ValueExpression(new IntValue(30))),
                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
        stack7.push(ex7);
        ProgramState state7 = new ProgramState(stack7,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository7 = new Repository("log7.txt");
        Controller controller7 = new Controller(repository7);
        controller7.addProgramState(state7);
        controller7.setDisplayFlag(true);


        StackInterface<Statement> stack8 = new StackImp<>();
        Statement ex8 = new CompoundStatement(
                new VariableDeclarationStatement("v",new RefType(new IntType())), new CompoundStatement(
                new AllocateHeapStatement("v",new ValueExpression(new IntValue(20))), new CompoundStatement(
                new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))), new CompoundStatement(
                new AllocateHeapStatement("a",new VariableExpression("v")), new CompoundStatement(
                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))), new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))))))));
        stack8.push(ex8);
        ProgramState state8 = new ProgramState(stack8,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository8 = new Repository("log8.txt");
        Controller controller8 = new Controller(repository8);
        controller8.addProgramState(state8);
        controller8.setDisplayFlag(true);


        StackInterface<Statement> stack9 = new StackImp<>();
        Statement ex9 = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(
                new AssignmentStatement("v",new ValueExpression(new IntValue(4))), new CompoundStatement(
                new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)), BinaryExpression.OPERATION.MORE)
                        ,new CompoundStatement(new PrintStatement(new VariableExpression("v")),new AssignmentStatement("v",new ArithmeticExpression('-',new VariableExpression("v"),new ValueExpression(new IntValue(1)))))), new PrintStatement(new VariableExpression("v")))));
        stack9.push(ex9);
        ProgramState state9 = new ProgramState(stack9,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository9 = new Repository("log9.txt");
        Controller controller9 = new Controller(repository9);
        controller9.addProgramState(state9);
        controller9.setDisplayFlag(true);


        StackInterface<Statement> stack10 = new StackImp<>();
        Statement ex10 = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()), new CompoundStatement(
                new VariableDeclarationStatement("a",new RefType(new IntType())), new CompoundStatement(
                new AssignmentStatement("v",new ValueExpression(new IntValue(10))), new CompoundStatement(
                new AllocateHeapStatement("a", new ValueExpression(new IntValue(22))), new CompoundStatement(
                new ForkStatement(new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(30))),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),new CompoundStatement(
                                new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                        )))), new CompoundStatement(
                new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        stack10.push(ex10);
        ProgramState state10 = new ProgramState(stack10,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository10 = new Repository("log10.txt");
        Controller controller10 = new Controller(repository10);
        controller10.addProgramState(state10);
        controller10.setDisplayFlag(true);


        StackInterface<Statement> stack11 = new StackImp<>();
        Statement ex11 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),new AssignmentStatement("a",new ValueExpression(new IntValue(10))));
        stack11.push(ex11);
        ProgramState state11 = new ProgramState(stack11,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository11 = new Repository("log11.txt");
        Controller controller11 = new Controller(repository11);
        controller11.addProgramState(state11);
        controller11.setDisplayFlag(true);


        StackInterface<Statement> stack12 = new StackImp<>();
        Statement ex12 = new CompoundStatement(
                new ForStatement(
                "v",
                new ValueExpression(new IntValue(0)),
                new ValueExpression(new IntValue(5)),
                new ArithmeticExpression('+',new VariableExpression("v"),new ValueExpression(new IntValue(1))),
                new PrintStatement(new VariableExpression("v")))
                            , new NopStatement());
        stack12.push(ex12);
        ProgramState state12 = new ProgramState(stack12,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository12 = new Repository("log12.txt");
        Controller controller12 = new Controller(repository12);
        controller12.addProgramState(state12);
        controller12.setDisplayFlag(true);


        StackInterface<Statement> stack13 = new StackImp<>();
        Statement ex13 = new CompoundStatement( new VariableDeclarationStatement("a",new RefType()), new CompoundStatement(new AllocateHeapStatement("a",new ValueExpression(new IntValue(20))),new CompoundStatement(
                new ForStatement(
                        "v",
                        new ValueExpression(new IntValue(0)),
                        new ValueExpression(new IntValue(3)),
                        new ArithmeticExpression('+',new VariableExpression("v"),new ValueExpression(new IntValue(1))),
                        new ForkStatement(
                                new CompoundStatement(
                                        new PrintStatement(new VariableExpression("v")),
                                        //new RelationalExpression(new VariableExpression("v"),new ArithmeticExpression('*',new VariableExpression("v"),new ReadHeapExpression(new VariableExpression("a"))), BinaryExpression.OPERATION.EQUAL)
                                        new AssignmentStatement("v",new ArithmeticExpression('*',new VariableExpression("v"),new ReadHeapExpression(new VariableExpression("a"))))
                                ))
                ), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))));
        stack13.push(ex13);
        ProgramState state13 = new ProgramState(stack13,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository13 = new Repository("log13.txt");
        Controller controller13 = new Controller(repository13);
        controller13.addProgramState(state13);
        controller13.setDisplayFlag(true);

        StackInterface<Statement> stack14 = new StackImp<>();
        Statement ex14 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new VariableDeclarationStatement("c", new IntType()),
                                new CompoundStatement( new AssignmentStatement("a",new ValueExpression(new IntValue(1))),
                                        new CompoundStatement(new AssignmentStatement("b",new ValueExpression(new IntValue(2))),
                                                new CompoundStatement(new AssignmentStatement("c",new ValueExpression(new IntValue(5))),
                                                        new CompoundStatement(
                                                                new SwitchStatement(
                                                                        new ArithmeticExpression('*',new VariableExpression("a"),new ValueExpression(new IntValue(10))),
                                                                        new ArithmeticExpression('*',new VariableExpression("b"),new VariableExpression("c")),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("a")),new PrintStatement(new VariableExpression("b"))),
                                                                        new ValueExpression(new IntValue(10)),
                                                                        new CompoundStatement(new PrintStatement(new ValueExpression(new IntValue(100))),new PrintStatement(new ValueExpression(new IntValue(200)))),
                                                                        new PrintStatement(new ValueExpression(new IntValue(300)))),
                                                                new PrintStatement(new ValueExpression(new IntValue(300))))
                                                        ))))));
        stack14.push(ex14);
        ProgramState state14 = new ProgramState(stack14,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository14 = new Repository("log14.txt");
        Controller controller14 = new Controller(repository14);
        controller14.addProgramState(state14);
        controller14.setDisplayFlag(true);



        StackInterface<Statement> stack15 = new StackImp<>();
        Statement ex15 = new CompoundStatement(new VariableDeclarationStatement("b",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("c",new IntType()),
                        new CompoundStatement(new AssignmentStatement("b",new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                new ConditionalStatement("c",new VariableExpression("b"),new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("c")),
                                new CompoundStatement(new ConditionalStatement("c",new ValueExpression(new BoolValue(false)),new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                                        new PrintStatement(new VariableExpression("c"))))))));
        stack15.push(ex15);
        ProgramState state15 = new ProgramState(stack15,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository15 = new Repository("log15.txt");
        Controller controller15 = new Controller(repository15);
        controller15.addProgramState(state15);
        controller15.setDisplayFlag(true);


        StackInterface<Statement> stack16 = new StackImp<>();
        Statement ex16 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new WaitStatement(new IntValue(10)), new PrintStatement(new ArithmeticExpression('*',new VariableExpression("v"),new ValueExpression(new IntValue(10)))))));
        stack16.push(ex16);
        ProgramState state16 = new ProgramState(stack16,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository16 = new Repository("log16.txt");
        Controller controller16 = new Controller(repository16);
        controller16.addProgramState(state16);
        controller16.setDisplayFlag(true);

        StackInterface<Statement> stack17 = new StackImp<>();
        Statement ex17 = new CompoundStatement(new VariableDeclarationStatement("a",new RefType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("b",new RefType(new IntType())),
                        new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                                new CompoundStatement(new AllocateHeapStatement("a",new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(new AllocateHeapStatement("b", new ValueExpression(new IntValue(0))),
                                                new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(1))),
                                                        new CompoundStatement(new WriteHeapStatement("b",new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(new ConditionalStatement("b",new RelationalExpression(new ReadHeapExpression(new VariableExpression("a")),new ReadHeapExpression(new VariableExpression("b")), BinaryExpression.OPERATION.LESS),new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(new ConditionalStatement("v",new RelationalExpression(new ArithmeticExpression('-',new ReadHeapExpression(new VariableExpression("b")),new ValueExpression(new IntValue(2))),new ReadHeapExpression(new VariableExpression("a")), BinaryExpression.OPERATION.MORE), new ValueExpression(new IntValue(100)),new ValueExpression(new IntValue(200))),
                                                                                        new PrintStatement(new VariableExpression("v"))))))))))));
        stack17.push(ex17);
        ProgramState state17 = new ProgramState(stack17,new Dictionary<String, Value>(),new List<Value>(),new Heap<Value>(),null,new Dictionary<String, BufferedReader>());
        RepositoryInterface repository17 = new Repository("log17.txt");
        Controller controller17 = new Controller(repository17);
        controller17.addProgramState(state17);
        controller17.setDisplayFlag(true);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.addCommand(new RunExample("5",ex5.toString(),controller5));
        menu.addCommand(new RunExample("6",ex6.toString(),controller6));
        menu.addCommand(new RunExample("7",ex7.toString(),controller7));
        menu.addCommand(new RunExample("8",ex8.toString(),controller8));
        menu.addCommand(new RunExample("9",ex9.toString(),controller9));
        menu.addCommand(new RunExample("10",ex10.toString(),controller10));
        menu.addCommand(new RunExample("11",ex11.toString(),controller11));
        menu.addCommand(new RunExample("12",ex12.toString(),controller12));
        menu.addCommand(new RunExample("13",ex13.toString(),controller13));
        menu.addCommand(new RunExample("14",ex14.toString(),controller14));
        menu.addCommand(new RunExample("15",ex15.toString(),controller15));
        menu.addCommand(new RunExample("16",ex16.toString(),controller16));
        menu.addCommand(new RunExample("17",ex17.toString(),controller17));
        //menu.show();

        return menu;
    }
}

