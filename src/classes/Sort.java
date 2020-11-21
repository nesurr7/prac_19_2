package classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort {

    public static void sort(ArrayList<Student> arr , Comparator obj) {
        int len = arr.size();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (obj.compare(arr.get(i) , arr.get(j))==-1) {
                    Student temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
        }
    }

    public static Student[] mergeSort(Student[] students , int reverse , Comparator obj) {

        if (students == null) {
            return null;
        }

        if (students.length < 2) {
            return students;
        }

        Student[] temp1 = new Student[students.length / 2];
        System.arraycopy(students, 0, temp1, 0, students.length / 2);

        Student[] temp2 = new Student[students.length - students.length / 2];
        System.arraycopy(students, students.length / 2, temp2, 0, students.length - students.length / 2);

        temp1 = mergeSort(temp1,reverse , obj);
        temp2 = mergeSort(temp2,reverse , obj);


        return mergeArray(temp1, temp2,reverse , obj);
    }

    public static Student[] mergeArray(Student[] temp1, Student[] temp2, int reverse , Comparator obj) {

        Student[] arrayResult = new Student[temp1.length + temp2.length];
        int position1 = 0, position2 = 0;

        for (int i = 0; i < arrayResult.length; i++) {
            if (temp1.length == position1) {
                arrayResult[i] = temp2[position2];
                position2++;
            } else if (temp2.length == position2) {
                arrayResult[i] = temp1[position1];
                position1++;
            } else if (obj.compare(temp1[position1], temp2[position2]) == reverse) {
                arrayResult[i] = temp2[position2];
                position2++;
            } else {
                arrayResult[i] = temp1[position1];
                position1++;
            }
        }
        return arrayResult;
    }


    public static Student[] quickSort(Student[] array, int lowPos, int highPos , Comparator obj) {
        if (array.length == 0) return array;
        if (lowPos >= highPos) return array;
        int middle = lowPos + (highPos - lowPos);
        Student opora = array[middle];
        int i = lowPos, j = highPos;
        while (i <= j) {
            while (obj.compare(array[i],opora) == 1) {
                i++;
            }
            while (obj.compare(array[j],opora) == -1) {
                j--;
            }
            if (j >= i) {
                Student temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (lowPos < j)
            quickSort(array, lowPos, j , obj);
        if (highPos > i)
            quickSort(array, i, highPos , obj);
        return array;
    }
}
