/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

/**
 *
 * @author Darwin W. Diaz Simon
 */
import java.util.Scanner;
public class Buscaminas{
   
    Scanner scan=new Scanner(System.in);
    
    public static void main(String arg[]){
    
        Scanner scan=new Scanner(System.in);
        int filas=10;
        int columnas=10;
        int numMinas;
        int contador;
        int c=0;
        Celda[][] buscaminas;
        System.out.println("Buscaminas");
       
        numMinas=10;
     
        System.out.println("El numero de minas es: "+numMinas);
        System.out.println("a jugar!!!");
        buscaminas=new Celda[columnas][filas];
        buscaminas=new Buscaminas().llenarTablero(buscaminas,0,0,columnas,filas);
        buscaminas=new Buscaminas().colocarMinas(buscaminas,numMinas,columnas,filas);
        buscaminas=new Buscaminas().minasAlrededor(buscaminas,0,0,columnas,filas);
        buscaminas=new Buscaminas().juego(buscaminas,columnas,filas,numMinas);
    }
    public Celda[][] llenarTablero(Celda[][] buscaminas,int i,int j,int c,int f){

      

        if(j<f){
            if(i<c){
                buscaminas[i][j]=new Celda();
                buscaminas=llenarTablero(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                buscaminas=llenarTablero(buscaminas,i,++j,c,f);
            }
        }
        return buscaminas;
    }
    public Celda[][] colocarMinas(Celda[][] buscaminas,int n,int c,int f){


        int azar1=(int)(Math.random()*(c-1));
        int azar2=(int)(Math.random()*(f-1));
        if(n>0){
            if(buscaminas[azar1][azar2].verMina()==false){
                buscaminas[azar1][azar2].colocarMina();
                n--;
            }
            buscaminas=colocarMinas(buscaminas,n,c,f);
        }
        return buscaminas;
    }
    public Celda[][] minasAlrededor(Celda[][] buscaminas,int i,int j,int c,int f){


        if(j<f){
            if(i<c){
                if(buscaminas[i][j].verMina()==true){
                    if(i>0){
                        buscaminas[i-1][j].aumentarMinas();
                        if(j>0){
                            buscaminas[i-1][j-1].aumentarMinas();
                        }
                        if(j<f-1){
                            buscaminas[i-1][j+1].aumentarMinas();
                        }
                    }
                    if(i<c-1){
                        buscaminas[i+1][j].aumentarMinas();
                        if(j>0){
                            buscaminas[i+1][j-1].aumentarMinas();
                        }
                        if(j<f-1){
                            buscaminas[i+1][j+1].aumentarMinas();
                        }
                    }
                    if(j>0){
                        buscaminas[i][j-1].aumentarMinas();
                    }
                    if(j<f-1){
                        buscaminas[i][j+1].aumentarMinas();
                    }
                }
                buscaminas=minasAlrededor(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                buscaminas=minasAlrededor(buscaminas,i,++j,c,f);
            }
        }
        return buscaminas;
    }
    public void imprimir(Celda[][] buscaminas,int i,int j,int c,int f){

       

        if(j<f){
            if(i<c){
                System.out.print(buscaminas[i][j]+" ");
                imprimir(buscaminas,++i,j,c,f);
            }
            else{
                i=0;
                System.out.println("");
                imprimir(buscaminas,i,++j,c,f);
            }
        }
    }
    public Celda[][] juego(Celda[][] buscaminas,int columnas,int filas,int contador){


        imprimir(buscaminas,0,0,columnas,filas);
        System.out.println("Ingrese el numero de fila y columna que desea explorar"+"n");
        System.out.print("Ingrese el numero de la fila: ");
        int f=scan.nextInt();
        System.out.print("Ingrese el numero de la columna: ");
        int c=scan.nextInt();
        if(f<=filas&&c<=columnas){
            if(buscaminas[c-1][f-1].verRevelado()==false){
                buscaminas[c-1][f-1].cambiarEstado();
                contador--;
            }
            if(contador==0){
                System.out.println("GANASTE!!!FELICIDADES!!!");
            }
            else{
                if(buscaminas[c-1][f-1].verMina()==true){
                    imprimir(buscaminas,0,0,columnas,filas);
                    System.out.println("BOOOOOOOOM!!!!!"+"perdiste el juego!!!");
                }
                else{
                    juego(buscaminas,columnas,filas,contador);
                }
            }
        }
        else{
            juego(buscaminas,columnas,filas,contador);
        }
        return buscaminas;
    }
}

