import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeAnalysis {

    // 🔹 Merge Sort (Ascending)
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        List<Trade> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
                temp.add(arr[i++]); // stable
            } else {
                temp.add(arr[j++]);
            }
        }

        while (i <= mid) temp.add(arr[i++]);
        while (j <= right) temp.add(arr[j++]);

        for (int k = 0; k < temp.size(); k++) {
            arr[left + k] = temp.get(k);
        }
    }

    // 🔹 Quick Sort (Descending)
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 🔹 Merge Two Sorted Arrays
    public static Trade[] mergeLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 🔹 Total Volume
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) sum += t.volume;
        return sum;
    }

    // 🔹 Main
    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Merge Sort
        Trade[] mergeArr = trades.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Ascending): " + Arrays.toString(mergeArr));

        // Quick Sort
        Trade[] quickArr = trades.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (Descending): " + Arrays.toString(quickArr));

        // Merge Two Lists
        Trade[] merged = mergeLists(mergeArr, mergeArr);
        System.out.println("Merged List Total Volume: " + totalVolume(merged));
    }
}