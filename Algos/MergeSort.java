package Algos;

public class MergeSort {
    
    int[] merge(int arr1[], int arr2[]){
        int sortedArr[] = new int[arr1.length + arr2.length];
        int i=0;
        int j=0;
        int k = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                sortedArr[k] = arr1[i];
                i++;
                k++;
            }
            else {
                sortedArr[k] = arr2[j];
                j++;
                k++;
            }
        }

        while(i < arr1.length) {
            sortedArr[k] = arr1[i];
            i++;
            k++;
        }

        while(j < arr2.length) {
            sortedArr[k] = arr2[j];
            j++;
            k++;
        }

        return sortedArr;
    }   

    int[] sort(int arr[]) {
        int len = arr.length;
        if(len == 1){
            return arr;
        }

       return  merge(sort(spliceArr(arr,0,len/2)), sort(spliceArr(arr,len/2,len)) );
    }

    int[] spliceArr(int arr[], int start, int end){

        int newarr[] = new int[end-start];
        int k = 0;
        for(int i = start; i<end; i++){
            newarr[k] = arr[i];
            k++;
        }

        return newarr;
    }

    public static void main(String[] args) {
        MergeSort mm = new MergeSort();

        int sortedarr[] = mm.sort(new int[]{6,5,3,8,7,9,10,45,23,90,12,112,67});

        for(int i=0 ;i < sortedarr.length; i++){
            System.out.print(sortedarr[i] + " ");
        }
    }

}
