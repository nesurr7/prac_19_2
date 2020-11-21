package classes;

public class Student implements Comparable<Student>{
    int ID;
    float GPA;
    String fio;
    Student(int ID , float GPA,String fio){
        this.ID=ID;
        this.GPA = GPA;
        this.fio=fio;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID == student.ID;
    }

    @Override
    public int compareTo(Student o) {
        if (this.equals(o)) return 0;
        else if (o.ID < this.ID) return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return ID + "|" + GPA + "|" + fio;
    }
}
