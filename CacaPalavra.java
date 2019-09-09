import java.util.*;
public class CacaPalavra{
   public static void main(String[]args){
      Scanner scanner = new Scanner(System.in);
      char[][] m = new char[15][15];
      int[][] m2 = new int[15][15];
      int i, j, c, J, I, cont, numOcorrencia, cont2;
      boolean charErrado;
      charErrado = false;
      cont = cont2 = numOcorrencia = c = 0;
      String palavra = "";
      m = criarMatrizChar(m); // inicializando a matriz e colocando em m;
      printMatrizChar(m);
      while(true){
         numOcorrencia = 0;
         cont = 0;
         c = 0;
         m = criarMatrizChar(m); // inicializando a matriz de caracteres e colocando em m;
         m2 = criarMatrizInt(m2); // inicializando a matriz de inteiros e colocando em m2;
         System.out.print("\nDigite a palavra a ser buscada: " );
         palavra = scanner.nextLine().toUpperCase();
         for(i = 0;i<m.length;i++){
            for(j = 0;j<m.length;j++){
               charErrado = false;
               c = 0;
               cont = 0;
               cont2 = 0; //contador de letras na palavra;
               if(m[i][j] == palavra.charAt(c)){  // se o caractere da matriz = a primeira letra da palavra
                  if(palavra.length() == 1){
                     m2[i][j] = 1;
                     numOcorrencia++;
                  }else{
                     if((j+1) < m.length){ //verificando se a posição à direita é nula 
                        for(J = j+1;cont < palavra.length()-1 && !charErrado;J++){ //for para ver se existe palavra à leste
                           if(J <m.length && m[i][J] == palavra.charAt(c+1)){
                              cont++;
                              c++;
                              cont2++;
                              if(cont2 == palavra.length()-1){ //verificando se a palavra ja foi encontrada e contando o numero de ocorrências
                                 numOcorrencia++; //numero que a palavra foi encontrada +1
                                 m2 = guardaPalavraH(i, j, palavra, m2);
                              }
                           }
                           else{
                              charErrado = true;
                              break;
                           }
                        }
                        I = i+1;
                        cont = 0;
                        cont2 = 0;
                        c = 0;
                        charErrado = false;
                        if((i+1) < m.length){ //verificando se a posição abaixo e à direita é nula
                           for(J = j+1;cont<palavra.length() - 1 && !charErrado;J++,I++){ //para ver se existe palavra à sudeste
                              if(I < m.length && J < m.length && m[I][J] == palavra.charAt(c+1)){
                                 c++;
                                 cont++;
                                 cont2++;
                                 if(cont2 == palavra.length()- 1){ //verificando se a palavra ja foi encontrada e contando o numero de ocorrências
                                    m2 = guardaPalavraD(i, j, palavra, m2);
                                    numOcorrencia++; //numero que a palavra foi encontrada +1
                                 }
                              }
                              else{
                                 charErrado = true;
                                 break;
                              }
                           }
                        }
                     }
                     cont2 = 0;
                     cont = 0;
                     c = 0;
                     charErrado = false;
                     if((i+1) < m.length){ //verificando se a posição abaixo é nula
                        for(I =i+1;cont < palavra.length()-1 && !charErrado;I++){ //para ver se existe palavra à sul
                           if(I <m.length && m[I][j] == palavra.charAt(c+1)){
                              c++;
                              cont++;
                              cont2++;
                              if(cont2 == palavra.length()-1){ //verificando se a palavra ja foi encontrada e contando o numero de ocorrências
                                 numOcorrencia++; //numero que a palavra foi encontrada +1
                                 m2 = guardaPalavraV(i, j, palavra, m2);
                              }
                           }
                           else{
                              charErrado = true;
                              break;
                           }
                        }
                     }
                  }    
                  if(m2[i][j] != 1){ //se essa posição na matriz de inteiros for diferente de 1
                     m[i][j] = '*'; //coloca asterisco na posição
                  }
               }else{// se o caractere da matriz for diferente da primeira letra da palavra 
                  if(m2[i][j] != 1){//se essa posição na matriz de inteiros for diferente de 1
                     m[i][j] = '*'; // coloca asterisco na posição;
                  }
               }
            }
         }
         if(numOcorrencia != 0){ //se o numero de Ocorrências da palavra for diferente de 0
            System.out.println("\n" + numOcorrencia + " ocorrencias para a palavra " + palavra);
            System.out.println("");
            printMatrizChar(m);
         }
         else{ //se nao houve ocorrencia da palavra
            System.out.println("\nNenhuma ocorrencia para a palavra " + palavra);
         }
      }
   }
   public static void printMatrizChar(char[][] matriz){ //printa Matriz de caracteres
      int i,j;
      for(i = 0;i<matriz.length;i++){
         for(j = 0;j<matriz[i].length;j++){
            System.out.print(matriz[i][j] + " ");
         }
         System.out.print("\n");
      }
   }
   public static void printMatrizInt(int[][] matriz){ // print Matriz de inteiros
      int i,j;
      for(i = 0;i<matriz.length;i++){
         for(j = 0;j<matriz[i].length;j++){
            System.out.print(matriz[i][j] + " ");
         }
         System.out.print("\n");
      }
   }
   public static int[][] guardaPalavraV(int i,int j, String palavra, int[][] matriz){// colocar em m2 o numero 1 nas posições do matriz que contém a palavra 
      for(int c = 0;c<palavra.length();c++,i++){
         matriz[i][j] = 1;
      }
      return matriz;
   }
   public static int[][] guardaPalavraH(int i,int j, String palavra, int[][] matriz){ // colocar em m2 o numero 1 nas posições do matriz que contém a palavra
      for(int c = 0;c<palavra.length();c++,j++){
         matriz[i][j] = 1;
      }
      return matriz;
   }
   public static int[][] guardaPalavraD(int i,int j, String palavra, int[][] matriz){ // colocar em m2 o numero 1 nas posições do matriz que contém a palavra
      for(int c = 0;c<palavra.length();c++,i++,j++){
         matriz[i][j] = 1;
      }
      return matriz;
   }
   public static int[][] criarMatrizInt(int[][] matriz){ //criar matriz de Inteiros
      for(int i = 0;i<matriz.length;i++){
         for(int j = 0; j < matriz.length;j++){
            matriz[i][j] = 0;
         }
      }
      return matriz;
   }
   public static char[][] criarMatrizChar(char[][] matriz){ // cria matriz de caracteres
      String letras ="YCYGWRPKHOABUVHSCIRFZBMCPMYCFPUAFRXTWLOTASMXCEJRAGSAVHGLRXGFKXZTAPCVJQMJYMGGCXQEWSIALAEOIVIFYFXVALPALHETALEKOUUTIGUANCOIVHIHZUAIFRDBALUARZHXCLCOGEEXVRUNBSTMUSICATLAAWRAUJABISSNORISCMPLENPALCOAHBETMFOTZMPTRESJRLFSIKUFPEQTAMLOJ";
      int c = 0;
      for(int i = 0;i<matriz.length;i++){
         for(int j = 0; j < matriz.length;j++){
            matriz[i][j] = letras.charAt(c);
            c++;
         }
      }
      return matriz;
   }
}