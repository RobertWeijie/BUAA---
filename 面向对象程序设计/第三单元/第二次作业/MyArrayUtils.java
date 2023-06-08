package src2;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class MyArrayUtils {

    public static boolean isArrayAContainedInArrayB(Object[] a, Object[] b) {
        Set<Object> setB = new HashSet<>();
        for (Object num : b) {
            setB.add(num);
        }

        for (Object num : a) {
            if (!setB.contains(num)) {
                return false;
            }
        }
        return true;
    }
    /*创建一个HashSet，将数组B中的元素添加到HashSet中，遍历数组A中的元素，如果在HashSet中不存在，则返回false，数组A中的所有元素都存在于数组B中，返回true*/

    public static <T> T[] insertAtBeginning(T[] array, T element) {
        T[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[0] = element;
        System.arraycopy(array, 0, newArray, 1, array.length);
        return newArray;
    }
//创建一个新的数组，长度比原数组多1，将新元素插入到新数组的第一个位置，将原数组的元素复制到新数组之后的位置，返回新数组。

    public static int[] removeElement(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }
//    创建一个新的整型数组，长度比原数组少1，将原数组的前index个元素复制到新数组，将原数组的后index个元素复制到新数组，返回新数组。

    public static <T> T[] removeElement(T[] array, int index) {
        T[] newArray = Arrays.copyOf(array, array.length - 1);
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }
//    创建一个新的泛型数组（可以是任意类型，例如 String、Object、Integer 等），长度比原数组少1，将原数组的前index个元素复制到新数组，将原数组的后index个元素复制到新数组，返回新数组。


    public static int[] insertElement(int[] array, int element) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    public static <T> T[] insertElement(T[] array, T element) {
        T[] newArray = Arrays.copyOf(array, array.length + 1);
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = element;
        return newArray;
    }

}
