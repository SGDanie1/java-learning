package com.daniel.jl.mergetask;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MergeTask01 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    DynamicArray storage = new DynamicArray();
    String answer = "y";
    String answer2 = "y";
    int chosenVar = 0;
    System.out.println("Введите массив целых чисел: ");
    int[] parsedArray0 = readArrayParseInt();
    storage.add(parsedArray0);
    System.out.println("Введите ещё один массив целых чисел: ");
    int[] parsedArray1 = readArrayParseInt();
    storage.add(parsedArray1);
    System.out.println("Желаете ввести ещё один массив?(y-да/n-нет)");
    answer = in.nextLine();
    int count = 2;
    if (Objects.equals(answer, "y")) {
      do {
        count++;
        System.out.println("Введите ещё один массив целых чисел: ");
        int[] parsedArrayCycle = readArrayParseInt();
        storage.add(parsedArrayCycle);
        System.out.println("Желаете ввести ещё один массив?(y-да/n-нет)");
        answer = in.nextLine();

      } while (Objects.equals(answer, "y"));
    }
    for (int i = 0; i < storage.data.length; i++)
      System.out.print(Arrays.toString(storage.data[i]));
    System.out.println();
    System.out.println("Продолжить работу с массивами?(y-да/n-нет)");
    answer2 = in.nextLine();
    if (Objects.equals(answer2, "y")) {
      Scanner in1 = new Scanner(System.in);
      String answer3 = "y";
      do {
        System.out.println("Выберите желаемую операцию(от 1 до 6): ");
        System.out.println("1. Слияние сегментов;");
        System.out.println("2. Поиск наименьшего значения;");
        System.out.println("3. Поиск наибольшего значения;");
        System.out.println("4. Поиск среднего арифметического;");
        System.out.println("5. Поиск медианы;");
        System.out.println("6. Сортировка;");
        chosenVar = in.nextInt();
        switch (chosenVar) {
          case 1:
            DynamicArrayOne mergedNew = merge(storage, count);
            //for (int i = 0; i < mergedNew.dataOneD.length; i++)
              //System.out.print(Arrays.toString(new int[]{mergedNew.dataOneD[i]}));
            System.out.print(Arrays.toString(mergedNew.dataOneD));
            System.out.println();
            break;
          case 2:
            Analytic.getMaxMinAverage(storage);
            System.out.println("Минимальное значение в массиве= " + Analytic.min + " и находится в " + Analytic.segmentOfMin + " сегменте.");
            break;
          case 3:
            Analytic.getMaxMinAverage(storage);
            System.out.println("Максимальное значение в массиве= " + Analytic.max + " и находится в " + Analytic.segmentOfMax + " сегменте.");
            break;
          case 4:
            Analytic.getMaxMinAverage(storage);
            System.out.println("Среднее арифметическое = " + Analytic.average);
            break;
          case 5:
            mergedNew = merge(storage, count);
            DynamicArrayOne storageSorted=sortBubble(mergedNew);
            Analytic.getMdn(storageSorted);
            if(storageSorted.dataOneD.length%2!=0) {
              System.out.println("Медианой массива является: " + Analytic.mdn);
            }else {
              System.out.println("Медиана не может быть определена однозначно и приблизительно равна " + Analytic.mdn);
            }
            break;
          case 6:
            mergedNew = merge(storage, count);
            DynamicArrayOne sortedArray = sortBubble(mergedNew);
            System.out.println("Отсортированный массив: ");
            System.out.print(Arrays.toString(sortedArray.dataOneD));
            break;
          default:
            System.out.println("Для выбора необходимо ввести цифры от 1 до 4 ");
        }
        System.out.println("Продолжить работу с массивами?");
        answer3 = in1.nextLine();
      } while (Objects.equals(answer3, "y"));
    }
  }

  public static int[] readArrayParseInt() {
    Scanner in = new Scanner(System.in);
    String arrayInLine = in.nextLine();
    if (Objects.equals(arrayInLine, "")) {
      System.out.println("Вы ввели пустой массив. Попробуйте ещё раз.");
      return readArrayParseInt();
    } else {
      String[] readedArray = arrayInLine.split(" ");
      int[] parsedArray = new int[readedArray.length];
      for (int i = 0; i < readedArray.length; i++) {
        parsedArray[i] = Integer.parseInt(readedArray[i]);
      }
      return parsedArray;
    }
  }

  public static class DynamicArray {
    int[][] data = {};

    void add(int[] elem) {
      add(data.length, elem);
    }

    void remove() {
      remove(data.length - 1);
    }

    void add(int index, int[] elem) {
      int[][] tmp = new int[data.length + 1][];
      System.arraycopy(data, 0, tmp, 0, index);
      System.arraycopy(data, index, tmp, index + 1, data.length - index);
      tmp[index] = elem;
      this.data = tmp;
    }

    void remove(int index) {
      int[][] tmp = new int[data.length - 1][];
      System.arraycopy(data, 0, tmp, 0, index);
      System.arraycopy(data, index + 1, tmp, index, data.length - index - 1);
      this.data = tmp;
    }
  }

  public static class DynamicArrayOne {
    int[] dataOneD = {};

    void add(int elem) {
      add(dataOneD.length, elem);
    }

    void remove() {
      remove(dataOneD.length - 1);
    }

    void add(int index, int elem) {
      int[] tmp = new int[dataOneD.length + 1];
      System.arraycopy(dataOneD, 0, tmp, 0, index);
      System.arraycopy(dataOneD, index, tmp, index + 1, dataOneD.length - index);
      tmp[index] = elem;
      this.dataOneD = tmp;
    }

    void remove(int index) {
      int[] tmp = new int[dataOneD.length - 1];
      System.arraycopy(dataOneD, 0, tmp, 0, index);
      System.arraycopy(dataOneD, index + 1, tmp, index, dataOneD.length - index - 1);
      this.dataOneD = tmp;
    }
  }

  public static DynamicArrayOne merge(DynamicArray storage, int count) {
    DynamicArrayOne merged = new DynamicArrayOne();
    for (int i = 0; i < count; i++) {
      for (int k = 0; k < storage.data[i].length; k++)
        merged.add(storage.data[i][k]);
    }
    return merged;
  }

  public static class Analytic {
    static int max = 0;
    static int segmentOfMax = 0;
    static int min = 0;
    static int segmentOfMin = 0;
    static double average = 0.0;
    static int mdn=0;

    public static void getMaxMinAverage(DynamicArray storage) {
      min = storage.data[0][0];
      int countMain = 0;
      int tmpResult = 0;
      for (int i = 0; i < storage.data.length; i++) {
        int count = 0;
        for (int k = 0; k < storage.data[i].length; k++) {

          if (max <= storage.data[i][k]) {
            max = storage.data[i][k];
            segmentOfMax = i;
          }
          if (min >= storage.data[i][k]) {
            min = storage.data[i][k];
            segmentOfMin = i;
          }
          tmpResult += storage.data[i][k];
          count++;
        }
        countMain += count;
      }
      average = (double) tmpResult / countMain;
    }
    public static int getMdn(DynamicArrayOne arr) {
      if (arr.dataOneD.length % 2 != 0) {
        mdn = arr.dataOneD[arr.dataOneD.length / 2];
      } else {
        mdn = (arr.dataOneD[arr.dataOneD.length / 2] + arr.dataOneD[(arr.dataOneD.length / 2) - 1]) / 2;
      }
      return mdn;
    }
  }

  public static int[] mergeTwo(int[] arr0, int[] arr1) {

    int lngth = arr0.length + arr1.length;
    int[] arr2 = new int[lngth];
    for (int i = 0, j = 0, k = 0; k < lngth; k++) {
      if (i >= arr0.length) {
        arr2[k] = arr1[j];
        j++;
      } else if (j >= arr1.length) {
        arr2[k] = arr1[i];
        i++;
      }
      if ((i < arr0.length) && (j < arr1.length)) {
        if (arr0[i] < arr1[j]) {
          arr2[k] = arr0[i];
          i++;
        } else {
          arr2[k] = arr1[j];
          j++;
        }
      }
    }
    System.out.print(Arrays.toString(arr2));
    return arr2;
  }

  public static DynamicArrayOne sortBubble(DynamicArrayOne arr) {
    for (int barrier = arr.dataOneD.length - 1; barrier >= 0; barrier--) {
      for (int index = 0; index < barrier; index++) {
        if (arr.dataOneD[index] > arr.dataOneD[index + 1]) {
          int tmp = arr.dataOneD[index];
          arr.dataOneD[index] = arr.dataOneD[index + 1];
          arr.dataOneD[index + 1] = tmp;
        }
      }
    }
    return arr;
  }


  //слияние двух массивов
  /*public static int[] merge(DynamicArray storage, int indexA,int indexB) {
    int[] mergedArray = new int[storage.data[indexA].length+storage.data[indexB].length];

    for(int i=0;i<1;i++) {
      for (int k = 0,j=0,l=0; k < mergedArray.length; k++){
       if(j>=storage.data[indexA].length) {
         mergedArray[k] = storage.data[indexB][l];
         l++;
       }else{
         mergedArray[k]=storage.data[indexA][j];
         j++;
       }
       }
    }
    return mergedArray;
  }*/

  //Слияние избранных по индексу массивов
 /* public static DynamicArrayOne merge(DynamicArray storage, int... index) {
    DynamicArrayOne merged = new DynamicArrayOne();
    for (int i = 0; i < index.length; i++) {
      for (int k = 0; k < storage.data[index[i]].length; k++)
        merged.add(storage.data[index[i]][k]);
    }
    return merged;
  }*/

 /* public static int max(DynamicArray storage) {
    int max = storage.data[0][0];
    int segmentOfMax = 0;
    for (int i = 0; i < storage.data.length; i++) {
      for (int k = 0; k < storage.data[i].length; k++) {
        if (max <= storage.data[i][k]) {
          max = storage.data[i][k];
          segmentOfMax=i;
        }
      }
    }
    System.out.println("Максимальное значение в массиве= " + max + " и находится в " + segmentOfMax + " сегменте.");
    return max;
  }

  public static int min(DynamicArray storage) {
    int min = storage.data[0][0];
    int segmentOfMin = 0;
    for (int i = 0; i < storage.data.length; i++) {
      for (int k = 0; k < storage.data[i].length; k++) {
        if (min >= storage.data[i][k]) {
          min = storage.data[i][k];
          segmentOfMin = i;
        }
      }
    }
    System.out.println("Минимальное значение в массиве= " + min + " и находится в " + segmentOfMin + " сегменте.");
    return min;
  }*/

}

