package oblig2_dat102;

import java.util.Arrays;
import java.util.Random;

public class oppgave2 {

    public static void insertionSort(Integer[] tabell) {
        int n = tabell.length;
        for (int i = 1; i < n; i++) {
            Integer k = tabell[i];
            int j = i - 1;
            while (j >= 0 && tabell[j] > k) {
                tabell[j + 1] = tabell[j];
                j--;
            }
            tabell[j + 1] = k;
        }
    }

    public static void selectionSort(Integer[] tabell) {
        int n = tabell.length;
        for (int i = 0; i < n - 1; i++) {
            int minste = i;
            for (int j = i + 1; j < n; j++) {
                if (tabell[j] < tabell[minste]) {
                    minste = j;
                }
            }
            int midlertidig = tabell[minste];
            tabell[minste] = tabell[i];
            tabell[i] = midlertidig;
        }
    }

    public static void quickSort(Integer[] tabell, int start, int slutt) {
        if (start < slutt) {
            int pivot = finnPivotOgdel(tabell, start, slutt);
            quickSort(tabell, start, pivot - 1);
            quickSort(tabell, pivot + 1, slutt);
        }
    }

    private static int finnPivotOgdel(Integer[] tabell, int start, int slutt) {
        Integer pivot = tabell[slutt];
        int i = start - 1;
        for (int j = start; j < slutt; j++) {
            if (tabell[j] <= pivot) {
                i++;
                int midlertidig = tabell[i];
                tabell[i] = tabell[j];
                tabell[j] = midlertidig;
            }
        }
        int midlertidig = tabell[i + 1];
        tabell[i + 1] = tabell[slutt];
        tabell[slutt] = midlertidig;
        return i + 1;
    }

    public static void mergeSort(Integer[] tabell, int venstre, int hoyre) {
        if (venstre < hoyre) {
            int midten = (venstre + hoyre) / 2;
            mergeSort(tabell, venstre, midten);
            mergeSort(tabell, midten + 1, hoyre);
            slåSammen(tabell, venstre, midten, hoyre);
        }
    }

    private static void slåSammen(Integer[] tabell, int venstre, int midten, int hoyre) {
        int n1 = midten - venstre + 1;
        int n2 = hoyre - midten;

        Integer[] venstreTabell = Arrays.copyOfRange(tabell, venstre, midten + 1);
        Integer[] hoyreTabell = Arrays.copyOfRange(tabell, midten + 1, hoyre + 1); 

        int i = 0, j = 0, k = venstre;
        while (i < n1 && j < n2) {
            if (venstreTabell[i] <= hoyreTabell[j]) {
                tabell[k] = venstreTabell[i];
                i++;
            } else {
                tabell[k] = hoyreTabell[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            tabell[k] = venstreTabell[i];
            i++;
            k++;
        }
        while (j < n2) {
            tabell[k] = hoyreTabell[j];
            j++;
            k++;
        }
    }

    public static Integer[] genererTabell(int storrelse) {
        Integer[] tabell = new Integer[storrelse];
        Random tilfeldig = new Random();
        for (int i = 0; i < storrelse; i++) {
            tabell[i] = tilfeldig.nextInt(1000000);
        }
        return tabell;
    }

    public static void maalSorteringsTid(Integer[] tabell, String metode, String kompleksitet) {
        Integer[] kopi = Arrays.copyOf(tabell, tabell.length);

        long startTid = System.currentTimeMillis();

        switch (metode) {
            case "Insertion Sort":
                insertionSort(kopi);
                break;
            case "Selection Sort":
                selectionSort(kopi);
                break;
            case "Quick Sort":
                quickSort(kopi, 0, kopi.length - 1);
                break;
            case "Merge Sort":
                mergeSort(kopi, 0, kopi.length - 1);
                break;
            default:
                System.out.println("Ukjent metode");
                return;
        }

        long sluttTid = System.currentTimeMillis();
        double tid = (sluttTid - startTid);

        System.out.println(metode + " | " + "Notasjon: " + kompleksitet + " | Tok: " + tid + " ms");
    }

    public static void main(String[] args) {
        int[] n = {32000, 64000, 128000}; 

        for (int nverdi : n) {
            Integer[] tabell = genererTabell(nverdi);

            System.out.println("\nN = " + nverdi);

        maalSorteringsTid(tabell, "Insertion Sort", "O(n^2)");
        maalSorteringsTid(tabell, "Selection Sort", "O(n^2)");
        maalSorteringsTid(tabell, "Merge Sort", "O(n log n)");
        maalSorteringsTid(tabell, "Quick Sort", "O(n log n)");
    }
        System.out.println("Når quicksort har tabell med alle like");
        Integer[] likTabell = new Integer[32000];
        Arrays.fill(likTabell, 42); 
        maalSorteringsTid(likTabell, "Quick Sort", "O(n log n)");
}
}

// quicksort og mergesort er O(n log n), insertion og selection er O(n^2)
// som en ser på utskriften er insertion og selection langt tregere enn 
// quicksort og merge når n blir større

//får stackoverflowerror når quicksort kjører med 320000 like tall,
// quicksort får O(n^2) når alle elementene er like fordi quicksort sorterer
// venstre og høyre side ved å se på hvilke elementer til venstre som er mindre
// eller lik pivoten, og til høyre de elementene som er større enn pivoten