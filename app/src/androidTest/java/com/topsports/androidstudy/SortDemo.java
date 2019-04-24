package com.topsports.androidstudy;

/**
 * Date 2019/3/29
 * Time 14:20
 *
 * @author wentong.chen
 */
public class SortDemo {
    private static int[] Array = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 11};

    public static void main(String[] args) {
        //冒泡排序
        bubble(Array);
        printArray(Array, "冒泡排序结果是");
    }

    //冒泡排序升序
    public static void bubble(int[] array) {
        boolean flag = true;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = array.length - 1; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    swap(array, j, j-1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void printArray(int[] array, String pref) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i : array) {
            System.out.println(pref + ": =======" + i + "========");
        }

    }
}
