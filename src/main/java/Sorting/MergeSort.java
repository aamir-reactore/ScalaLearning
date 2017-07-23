package Sorting;

/**
 * Created by aamir on 23/7/17.
 */
public class MergeSort {
    public static void main(String[] args) throws InterruptedException {
        int[] array = {6, 5, 4, 3, 1, 2};
        mergeSort(array, 0, 5);
        for(int i=0;i<array.length;i++)
            System.out.print(array[i] + " ");
    }

    static void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    static void merge(int[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int[] temp = new int[high - low + 1];
        int k = 0;
        while (left <= mid && right <= high) {
            if (array[left] < array[right]) {
                temp[k] = array[left];
                left = left + 1;
            } else {
                temp[k] = array[right];
                right = right + 1;
            }
            k = k + 1;
        }
        if (left <= mid) {
            while (left <= mid) {
                temp[k] = array[left];
                k = k + 1;
                left = left + 1;
            }
        } else if (right <= high) {
            while (right <= high) {
                temp[k] = array[right];
                k = k + 1;
                right = right + 1;
            }
        }
        for (int m = 0; m < temp.length; m++) {
            array[low + m] = temp[m];
        }
    }
}
