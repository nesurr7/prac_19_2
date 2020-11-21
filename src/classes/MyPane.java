package classes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class MyPane extends JPanel {
    private int HEIGHT = MyWindow.SCRN_HEIGHT;
    private int WIDTH = MyWindow.SCRN_WIDTH;
    private JTextField inputTextfield = new JTextField(50);
    private JTextArea outputTextfield = new JTextArea("Выходные данные",21,50);
    private String outputString = "";
    private JLabel label = new JLabel("      Введите искомые данные");
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu jMenuSearch = new JMenu("Поиск");
    private JMenu jMenuSort = new JMenu("Сортировка");
    private Student[] students = new Student[]{new Student(2,4.5f , "VVV") , new Student(1,2.3f , "BBB") , new Student(14,1f,"VCD")};
    Comparator<Student> comparatorID = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return Integer.compare(o1.ID , o2.ID);
        }
    };

    Comparator<Student> comparatorGPA = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return Float.compare(o1.GPA , o2.GPA);
        }
    };

    public MyPane() {
        setLayout(null);
        inputTextfield.setBounds((WIDTH - 200) / 2, (HEIGHT - 250) / 2, 200, 50);
        outputTextfield.setBounds((WIDTH - 200) / 2, (HEIGHT - 120) / 2, 200, 100);
        label.setBounds((WIDTH - 200) / 2, (HEIGHT - 350) / 2, 200, 50);
        jMenuBar.setBounds(MyWindow.SCRN_WIDTH / 2 - 128, 100, 253, 50);
        add(inputTextfield);
        add(outputTextfield);
        add(label);
        jMenuSearch.add(new JMenuItem("Бинарный поиск (по ID)")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student[] temp = Sort.mergeSort(students , 1 , comparatorID);
                int pos = Search.binarySearch(temp , new Student(Integer.parseInt(inputTextfield.getText()), -1f, null), students.length - 1);
                outputTextfield.setText(temp[pos].toString());
            }
        });
        jMenuSearch.add(new JMenuItem("Поиск ФИО")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outputTextfield.setText(students[Search.fioSearch(students , inputTextfield.getText())].toString());
                } catch (ArrException arrException) {
                    outputTextfield.setText("Элемент не найден");
                }
            }
        });
        jMenuSearch.add(new JMenuItem("Линейный поиск (по ID)")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextfield.setText(students[Search.linearSearch(students,new Student(Integer.parseInt(inputTextfield.getText()) , -1f , null))].toString());
            }
        });
        jMenuSort.add(new JMenuItem("Быстрая сортировка (по ID) по убыванию")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students = Sort.quickSort(students , 0 ,students.length-1 , comparatorID);
                for(Student temp:students){
                    outputString=outputString+temp+"\n";
                }
                outputTextfield.setText(outputString);
                outputString = "";
            }
        });
        jMenuSort.add(new JMenuItem("Cортировка слиянием (по GPA) по возрастанию")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students = Sort.mergeSort(students , 1 , comparatorGPA);
                for(Student temp:students){
                    outputString=outputString+temp+"\n";
                }
                outputTextfield.setText(outputString);
                outputString = "";
            }
        });
        jMenuBar.add(jMenuSort);
        jMenuBar.add(jMenuSearch);
        add(jMenuBar);

    }
}