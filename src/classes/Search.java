package classes;

public class Search {
    public static int linearSearch(Comparable[] array, Comparable target) {
        int len = array.length;
        for (int i = 0; i < len; ++i) {
            if (target.equals(array[i])) return  i;
        }
        return -1;
    }

    public static int fioSearch(Student[] array , String fio) throws ArrException {
        int len = array.length;
        for (int i = 0; i < len; ++i) {
            if (array[i].fio.equals(fio)) return  i;
        }
        throw new ArrException("Элемент не найден");
    }

    public static int binarySearch(Comparable[] array, Comparable target, int rPos) {
        if ((array.length == 1)) {
            if (target.equals(array[0])) return rPos;
            else return -1;
        }
        int centerPos = rPos / 2;
        int resTarget = target.compareTo(array[centerPos]);
        switch (resTarget) {
            case (0):
                return centerPos;
            case (1):
                System.arraycopy(array, centerPos + 1, array, 0, array.length - centerPos - 1);
                return binarySearch(array, target, array.length - centerPos - 1);
            case (-1):
                System.arraycopy(array, 0, array, 0, array.length - centerPos - 1);
                return binarySearch(array, target, array.length - centerPos - 1);
            default:
                return -1;
        }
    }
}
