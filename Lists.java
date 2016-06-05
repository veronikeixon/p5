/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author VMARIBL
 */
public class Lists {
    private List<Integer> array=new ArrayList();
    private List<Integer> linked=new LinkedList();
    private List<Integer> test;
    
    public double [][] resultados=new double [13][2];
    public String [][] texto=new String[14][4];
    
    double duracion;
    int size=1000;
    int media=1000;
    
    public void RunTimes(){
        Random ale = new Random();
        
        for (int i=0; i<size; i++) {
            int num=ale.nextInt(size-1);
            array.add(num);
            linked.add(num);
        }   

        for (int j=0; j<2; j++){
            switch (j){
                case 0:{crear_test_ArrayList(); break;}
                case 1:{crear_test_LinkedList(); break;}
            }
            for (int med=0; med<media;med++){
                for (int i=0; i<13; i++){
                    duracion=System.nanoTime(); 
                    switch (i){
                        case 0: {test.add(1); break;} 
                        case 1: {test.add(0,1); break;}
                        case 2: {test.add(test.size()/2,1); break;} 
                        case 3: {test.remove(test.size()-1); break;} 
                        case 4: {test.remove(0); break;}
                        case 5: {test.remove(test.size()/2); break;} 
                        case 6: {test.remove(test.lastIndexOf(test.get(test.size()-1))); break;} 
                        case 7: {test.remove(test.indexOf(test.get(0))); break;} 
                        case 8: {test.remove(test.get(test.size()/2)); break;} 
                        case 9: {test.clear(); break;} 
                        case 10:{test.contains(0); break;}
                        case 11:{
                            int buscar =0;
                            for(int m=0; m<test.size() && test.get(m)!=buscar; m++){
                                //test para encontrar el valor 0 (si existe)
                            } break;
                        }
                        case 12:{busquedaBinaria(test, 0, size-1, 0);
                            break;
                        }
                    }
                    if (med!=media-1){
                        resultados[i][j]=resultados[i][j]+(System.nanoTime()-duracion);
                    }
                    else{
                        resultados[i][j]=((resultados[i][j]+resultados[i][j])/media/1000000);
                        if (j==1){
                           if (resultados[i][j]<=resultados[i][j-1]){
                               texto[i+1][3]="LINKELIST";
                           }
                           else{
                               texto[i+1][3]="ARRAYLIST";
                           }
                        }
                    }
                    if (j==0){
                        crear_test_ArrayList();
                    }
                    else{
                        crear_test_LinkedList();
                    }
                }
            }
        }
        imprimir();
        //System.out.println(test);
    }
    
    private void imprimir (){
    texto[0][0]="TESTS                                            ";
    texto[0][1]="ARRAYLIST     ";
    texto[0][2]="LINKEDLIST   ";
    texto[0][3]="MAS RAPIDO   ";
    texto[1][0]="TEST  1 - Insertar elemento al final            ";
    texto[2][0]="TEST  2 - Insertar elemento al inicial          ";
    texto[3][0]="TEST  3 - Insertar elemento en el centro        ";
    texto[4][0]="TEST  4 - Borrar elemento final                 ";
    texto[5][0]="TEST  5 - Borrar elemento inicial               ";
    texto[6][0]="TEST  6 - Borrar elemento central               ";
    texto[7][0]="TEST  7 - Borrar valor de elemento final        ";
    texto[8][0]="TEST  8 - Borrar valor de elemento inicial      ";
    texto[9][0]="TEST  9 - Borrar valor de elemento central      ";
    texto[10][0]="TEST 10 - Vaciar lista                          ";
    texto[11][0]="TEST 11 - Buscar si existe elemento con funcion ";
    texto[12][0]="TEST 12 - Buscar si existe elemento sin funcion ";
    texto[13][0]="TEST 13 - Busqueda binaria de elemento          ";
    System.out.println(texto[0][0]+""+texto[0][1]+""+texto[0][2]+""+texto[0][3]);  
        for (int i=0; i<13;i++){
            System.out.print(texto[i+1][0]);
            System.out.printf(" %8f ",resultados[i][0]);
            System.out.print("    ");
            System.out.printf(" %8f ",resultados[i][1]);  
            System.out.print("    ");
            System.out.println(texto[i+1][3]);
            //System.out.println(texto[i+1][0]+" - "+resultados[i][0]+" - "+resultados[i][1]+" -> "+texto[i+1][3]);
        }
        System.out.println("");
        System.out.println("");
    }
    
    private List<Integer> crear_test_ArrayList(){
        test=new ArrayList();
        for (int k=0; k<array.size(); k++){
            test.add(k,array.get(k));
        }
        return test;
    }
    
    private List<Integer> crear_test_LinkedList(){
        test=new LinkedList();
        for (int k=0; k<linked.size(); k++){
            test.add(k,linked.get(k));
        }
        return test;
    }
    
    public boolean busquedaBinaria (List<Integer> test, int ini, int fin, int bus){
    boolean ok;
    if(ini <= fin){
        int m = ((fin - ini)/2) + ini;
        if(bus < (test.get(m))){
            ok = busquedaBinaria (test, ini, m-1, bus);
        }
        else if (bus > test.get(m)){
            ok = busquedaBinaria(test, m+1, fin, bus);
        }
        else {
            ok = true;
        }
    }else {
        ok = false;
    }
    return ok;
    }   
}