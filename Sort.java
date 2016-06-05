/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author VMARIBL
 */
public class Sort {

    private List<Integer> aleatorio=new ArrayList();
    private List<Integer> ordenado=new ArrayList();
    private List<Integer> inverso=new ArrayList();
    private List<Integer> repetido=new ArrayList();
    private List<Integer> test;
    
    public double [][] resultados=new double [7][4];
    public String [][] texto=new String[9][7];
    
    double duracion;
    int size =1000;
    int repe=100;
    boolean ok=false;
    
    public void RunTimes(){
        
        for (int j=0; j<4; j++){
            switch (j){
                case 0:{Crea_Random();  test=crear_test_ArrayList(aleatorio);break;}
                case 1:{Crea_Orden(); test=crear_test_ArrayList(ordenado); break;}
                case 2:{Crea_Inverso(); test=crear_test_ArrayList(inverso); break;}
                case 3:{Crea_Repe(); test=crear_test_ArrayList(repetido); break;}
            }
            for (int med=0; med<repe;med++){
                for (int i=0; i<7; i++){
                    duracion=System.nanoTime(); 
                    switch (i){
                        case 0: {BubbleSort(test); break;} 
                        case 1: {SelectionSort(test); break;}
                        case 2: {InsertionSort(test); break;}
                        case 3: {QuickSort(test,0,test.size()-1); break;}
                        case 4: {test=MergeSort(test);break;}
                        case 5: {
                            if (j==1){
                                busquedaBinaria (test, 0, test.size(), 2);
                            } break;
                        }
                        case 6: {BusqSeq(test); break;}
                    }
                    if (med!=repe-1) {
                        if (i==5 && j!=1){
                            resultados[i][j]=0;
                        }
                        else{
                        resultados[i][j]=resultados[i][j]+(System.nanoTime()-duracion);
                        }
                    }
                    else {
                        if (i==5 && j!=1){
                            resultados[i][j]=0;
                        }
                        else{
                            resultados[i][j]=((resultados[i][j]+resultados[i][j])/med/1000000);
                        }
                    }
                    ok=RunTests(test,j);
                    if (ok){
                        texto[i+1][6]="ORDEN OK";
                    }
                    else{
                        texto[i+1][6]="FALLO EN ORDEN";
                    }
                    switch (j){
                        case 0:{test=crear_test_ArrayList(aleatorio); break;}
                        case 1:{test=crear_test_ArrayList(ordenado); break;}
                        case 2:{test=crear_test_ArrayList(inverso); break;}
                        case 3:{test=crear_test_ArrayList(repetido); break;}
                    }
                }
            }
        }
        
        Calcular_mas_rapido();
        imprimir();
    }
    
    public boolean RunTests(List<Integer> array, int tipo){
       if (array.size()!=aleatorio.size()){
           ok=false;
       }
       else{
           ok=true;
           switch (tipo){
                case 0:
                case 1:
                case 2:{//descendente
                    int i, j;
                    for(i=0; i<size;i++){
                        if (i!=size-1){
                            if (array.get(i)<array.get(i+1)){
                                ok=false;}
                        }
                    }break;
                }
                case 3:{//repetido
                    int i, j;
                    for(i=0; i<size;i++){
                        if (i!=size-1){
                            if (array.get(i)!=array.get(i+1)){
                                ok=false;}
                        }
                    }break;
                }
                default: {
                    ok=false; }
            }
        }
        return ok;
    }
    
    // CREAR LOS DIFERENTES ARRAYSLIST

    private List<Integer> crear_test_ArrayList(List<Integer> array){
        test=new ArrayList();
        for (int k=0; k<array.size(); k++){
            test.add(k,array.get(k));
        }
        return test;
    }

    private void Crea_Random(){
        Random ale = new Random();
        for (int i=0; i<size; i++) {
            int num=ale.nextInt(999);
            aleatorio.add(num);
        }  
    }
    private void Crea_Orden(){
        for (int i=0; i<size; i++) {
            ordenado.add(i);
        }  
    }
    private void Crea_Inverso(){
        for (int i=size; i>0; i--) {
            inverso.add(i);
        }  
    }
    private void Crea_Repe(){
        for (int i=0; i<size; i++) {
            repetido.add(1);
        }  
    }
     
    
    public void Calcular_mas_rapido(){
        double minimo;
        int fast =0;
        int temp;
        
        for (int i=0;i<7;i++){
            minimo = resultados[i][0];
            if (i==5){
                texto[i+1][5] = "Solo sobre A.Ordenados --> ";
                if(resultados[6][1] < resultados[5][1]){
                    fast=100;
                }
                else{
                    fast=101;
                }
            }
            else{
                for(int j=0; j<4; j++){    
                    if(resultados[i][j] < minimo){
                        minimo = resultados[i][j];
                        fast=j;
                    }
                }
            }
            switch (fast){
                case 0:texto[i+1][5] = "A.ALEATORIO"; break;
                case 1:texto[i+1][5] = "A.ORDENADO"; break;
                case 2:texto[i+1][5] = "A.INVERSO"; break;
                case 3:texto[i+1][5] = "A.REPETIDO"; break;
                case 100:texto[i+1][5] = texto[i+1][5] + "BUSQUEDA SECUENCIAL más rápida"; break;
                case 101:texto[i+1][5] = texto[i+1][5] + "BUSQUEDA BINARIA más rápida"; break;
            }
        } 
        
        for (int j=0;j<4;j++){
            minimo = resultados[0][j];
            for(int i=0; i<5; i++){
                if(resultados[i][j] < minimo){
                    minimo = resultados[i][j];
                    fast=i;
                }
            }
            switch (fast){
                case 0:texto[8][j+1] = "BUBBLESORT"; break;
                case 1:texto[8][j+1] = "SELECTSORT"; break;
                case 2:texto[8][j+1] = "INSERTSORT"; break;
                case 3:texto[8][j+1] = "QUICKSORT "; break;
                case 4:texto[8][j+1] = "MERGESORT "; break;
            }
        } 
    }

    
    //METODOS DE ORDENACION
    
    private static void BubbleSort(List<Integer> array){
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        int temp1,temp2;   //holding variable
        
        while (flag){
            flag=false;    //set flag to false awaiting a possible swap
            for(j=0; j < array.size()-1;  j++){
                if (array.get(j)<array.get(j+1)){  // change to > for ascending sort
                    temp1 = array.get(j);                //swap elements
                    temp2 = array.get(j+1);
                    array.remove(j);
                    array.add(j,temp2);
                    array.remove(j+1);
                    array.add(j+1,temp1);
                    flag = true;              //shows a swap occurred 
                }
            }
        }
    } 
    private static void SelectionSort (List<Integer> array){
        int i, j, first, temp1, temp2; 
        for (i=array.size()-1;i>0;i--){
            first = 0;   //initialize to subscript of first element
            for(j=1;j<= i;j++){   //locate smallest element between positions 1 and i.
                if(array.get(j)<array.get(first))         
                first = j;
            }
            temp1 = array.get(first);   //swap smallest found with element in position i.
            temp2 = array.get(i);
            array.remove(first);
            array.add(first, temp2);
            array.remove(i);
            array.add(i, temp1); 
        }           
    }
    public static void InsertionSort(List<Integer> array){
    int j, i;                     // the number of items sorted so far
    int key;                // the item to be inserted
    int temp; 

        for (j = 1; j < array.size(); j++){    // Start with 1 (not 0)
            key = array.get(j);
            for(i = j - 1; (i >= 0) && (array.get(i)< key); i--){   // Smaller values are moving up
                temp = array.get(i);
                array.remove(i+1);
                array.add(i+1,temp);
            }
            array.remove(i+1);
            array.add(i+1, key);    // Put the key in its proper location
        }
    }
    
    public static void QuickSort(List<Integer> array, int izq, int der) {
    int pivote=array.get(izq); // tomamos primer elemento como pivote
    int i=izq; // i realiza la búsqueda de izquierda a derecha
    int j=der; // j realiza la búsqueda de derecha a izquierda
    int aux, aux2;

        while(i<j){            // mientras no se crucen las búsquedas
            while(array.get(i)>=pivote && i<j) i++; // busca elemento menor que pivote
            while(array.get(j)<pivote) j--;         // busca elemento mayor que pivote
            if (i<j) {                      // si no se han cruzado                      
                aux= array.get(i);                  // los intercambia
                aux2=array.get(j);
                array.remove(i);
                array.add(i, aux2); 
                array.remove(j);
                array.add(j,aux);
            }
        }
        aux=array.get(j); // se coloca el pivote en su lugar de forma que tendremos
        array.remove(izq);
        array.add(izq,aux);
        array.remove(j);
        array.add(j,pivote); // los menores a su derecha y los mayores a su izquierda
        if(izq<j-1)
            QuickSort(array,izq,j-1); // ordenamos subarray izquierdo
        if(j+1 <der)
            QuickSort(array,j+1,der); // ordenamos subarray derecho
    }


    public List<Integer> MergeSort(List<Integer> array) {
        // base case
        if(array.size() <= 1)
            return array;

        int halfwayIndex = array.size() / 2;
        List<Integer> leftSortedSeq = MergeSort(array.subList(0, halfwayIndex));
        List<Integer> rightSortedSeq = MergeSort(array.subList(halfwayIndex, array.size()));
        return merge(leftSortedSeq, rightSortedSeq);
    }

    /**
     * Merge step
     * Running time O(n)
     * @param leftSortedSeq
     * @param rightSortedSeq
     * @return mergedSortedSequences
     */
    private List<Integer> merge(List<Integer> leftSortedSeq, List<Integer> rightSortedSeq) {

        if(leftSortedSeq.isEmpty())
            return rightSortedSeq;
        else if (rightSortedSeq.isEmpty())
            return leftSortedSeq;

        List<Integer> sortedSeq = new ArrayList<>();
        int lIdx = 0;
        int rIdx = 0;
        int leftSortedSize = leftSortedSeq.size();
        int rightSortedSize = rightSortedSeq.size();
        while(lIdx < leftSortedSize && rIdx < rightSortedSize) {

            Integer leftSmallestElem = leftSortedSeq.get(lIdx);
            Integer rightSmallestElem = rightSortedSeq.get(rIdx);
            if(leftSmallestElem < rightSmallestElem) {
                sortedSeq.add(leftSmallestElem);
                lIdx++;
            }
            else {
                sortedSeq.add(rightSmallestElem);
                rIdx++;
            }
        }

        // copy over remainder from both seqs
        sortedSeq.addAll(leftSortedSeq.subList(lIdx, leftSortedSize));
        sortedSeq.addAll(rightSortedSeq.subList(rIdx, rightSortedSize));
        return sortedSeq;
    }


    
    // BUSQUEDAS
    
    public boolean busquedaBinaria (List<Integer> test, int ini, int fin, int bus){
        boolean ok;
        if(ini <= fin){
            int m = ((fin - ini)/2) + ini;
            if(bus < (test.get(m))){
                ok = busquedaBinaria (test, ini, m-1, bus);
            }else if (bus > test.get(m)){
                ok = busquedaBinaria(test, m+1, fin, bus);
            }else {
                ok = true;
            }
        }else {
            ok = false;
        }
        return ok;
    }    
    
    public void BusqSeq (List<Integer> array){
        int buscar=0;
        for(int m=0; m<test.size() && test.get(m)!=buscar; m++){
                //test para encontrar el valor 0 (si existe)
        } 
    }
    
    
    
    
    //FORMATO DE SALIDA
    
    private void imprimir (){
    texto[0][0]="Método Ordenación       ";
    texto[0][1]="RANDOM m/s   ";
    texto[0][2]="ORDENADO m/s ";
    texto[0][3]="INVERSO m/s  ";
    texto[0][4]="REPETIDO m/s ";
    texto[0][5]="Fastest Array ";
    texto[0][6]="TEST DE ORDEN   ";
    texto[1][0]="BUBBLESORT                ";
    texto[2][0]="SELECTIONSORT             ";
    texto[3][0]="INSERTIONSORT             ";
    texto[4][0]="QUICKSORT                 ";
    texto[5][0]="MERGESORT                 ";
    texto[6][0]="BUSQUEDA BINARIA          ";
    texto[7][0]="BUSQUEDA SECUENCIAL       ";
    texto[8][0]="Fastest x Ordenacion       ";
    

    System.out.println(texto[0][0]+"  "+texto[0][1]+" "+texto[0][2]+"    "+texto[0][3]+"  "+texto[0][4]+"  "+texto[0][5]+"  "+texto[0][6]);  
        for (int i=0; i<5;i++){
            System.out.print(texto[i+1][0]);
            System.out.printf("%10.4f",resultados[i][0]);
            System.out.print("      ");
            System.out.printf("%10.4f",resultados[i][1]);  
            System.out.print("      ");
            System.out.printf("%10.4f",resultados[i][2]);  
            System.out.print("      ");
            System.out.printf("%10.4f",resultados[i][3]);  
            System.out.print("      ");
            System.out.print(texto[i+1][5]); 
            System.out.print("      ");
            System.out.printf(texto[i+1][6]);  
            System.out.println();
        }
        System.out.println (texto[8][0]+""+texto[8][1]+"      "+texto[8][2]+"      "+texto[8][3]+"      "+texto[8][4]);
        
        System.out.println("");

        System.out.println("BUSQUEDAS:");
        
        for (int i=6; i<8;i++){
            System.out.print(texto[i][0]);
            System.out.printf("%.8f",resultados[i-1][0]);
            System.out.print("       ");
            System.out.printf("%.8f",resultados[i-1][1]);  
            System.out.print("       ");
            System.out.printf("%.8f",resultados[i-1][2]);  
            System.out.print("       ");
            System.out.printf("%.8f",resultados[i-1][3]);  
            System.out.print("       ");
            System.out.print(texto[i][5]);  
            System.out.print("       ");
            System.out.println();
        }
         System.out.println();
         System.out.println();
    }
    
}
