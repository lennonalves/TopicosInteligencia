/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RBC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lennon
 */

/* current time news */

public class Principal {
    static int max = 2000;
    
    public static void main(String[] args) throws IOException {        
        /* declaracoes */
        String data = null;
        Casos novocaso = new Casos();
        /* vetores fixos */
        ArrayList<Casos> listacasos = new ArrayList(); /* array de casos */
        double[][] diferencas = new double[max][2] /* vetor com somas das diferenças */;
        ArrayList<Casos> filtro = new ArrayList(); /* array com filtros */
        
        /* inicialicacao */
        listacasos.clear();
        
        /* funcoes */
        lerArquivo(data, listacasos);
        imprimeLista(listacasos);
        //insereCaso(novocaso);
        //filtragem(listacasos, filtro);
        //comparaSequencial(listacasos, novocaso, diferencas); /* listacasos ou filtro */
        //ordenaVetor(listacasos, diferencas); /* listacasos ou filtro */
        //listaSimilar(listacasos, diferencas);
        casoAleatorio(listacasos, 10 /* quantidade de casos */);
    }
    
    public static void imprimeLista(ArrayList<Casos> listacasos) {
        for (Casos caso : listacasos) {  
            System.out.println(caso.toString());
        }
    }
    
    public static void lerArquivo(String data, ArrayList<Casos> listacasos) throws FileNotFoundException, IOException {
        File file = new File("C:/Users/lennon/Documents/NetBeansProjects/TopicosInteligencia/src/RBC/cmc.data.testes.txt");
        BufferedReader reader;
        try (FileReader fileReader = new FileReader(file)) {
            reader = new BufferedReader(fileReader);
            while((data = reader.readLine()) != null) {
                /* imprime linha */
                //System.out.println(data);
                
                /* separa instancia */
                String[] corte = data.split(",");
                
                /* aplica valores ao caso */
                Casos casolido = new Casos();
                casolido.setIdademulher(Integer.parseInt(corte[0]));
                casolido.setEducacaomulher(Integer.parseInt(corte[1]));
                casolido.setEducacaohomem(Integer.parseInt(corte[2]));
                casolido.setNumerocriancas(Integer.parseInt(corte[3]));
                casolido.setReligiaomulher(Integer.parseInt(corte[4]));
                casolido.setEmpregomulher(Integer.parseInt(corte[5]));
                casolido.setOcupacaohomem(Integer.parseInt(corte[6]));
                casolido.setQualidadevida(Integer.parseInt(corte[7]));
                casolido.setExposicaomidia(Integer.parseInt(corte[8]));
                casolido.setMetodo(Integer.parseInt(corte[9]));
                
                /* verifica valor do atributo esta dentro dos parametros */
                if (validaCaso(casolido) == true) {
                    listacasos.add(casolido);
                } else {
                    System.out.println("Caso inválido, leitura não realizada.");
                }
            }
            System.out.println("Leitura de " + listacasos.size() + " caso(s) realizada com sucesso.");
        }
        reader.close();
    }
    
    public static void insereCaso(Casos novocaso) {
        /* declaracoes locais */
        Scanner scanner = new Scanner( System.in );
        boolean casook = false;
        
        do {
            System.out.println("Insira um novo caso.");

            System.out.println("Idade da esposa: ");
            novocaso.setIdademulher(scanner.nextInt());

            System.out.println("Educação da esposa: [1] Baixa [2] Média [3] Regular [4] Alta ");
            novocaso.setEducacaomulher(scanner.nextInt());

            System.out.println("Educação do marido: [1] Baixa [2] Média [3] Regular [4] Alta ");
            novocaso.setEducacaohomem(scanner.nextInt());

            System.out.println("Número de crianças nascidas: ");
            novocaso.setNumerocriancas(scanner.nextInt());

            System.out.println("Religião da esposa: [0] Não Islã [1] Islã ");
            novocaso.setReligiaomulher(scanner.nextInt());

            System.out.println("Esposa trabalhando: [0] Sim [1] Não ");
            novocaso.setEmpregomulher(scanner.nextInt());

            System.out.println("Ocupação do marido: [1, ..., 4] ");
            novocaso.setOcupacaohomem(scanner.nextInt());

            System.out.println("Índice de qualidade de vida: [1] Baixa [2] Média [3] Regular [4] Alta ");
            novocaso.setQualidadevida(scanner.nextInt());

            System.out.println("Exposição de mídia: [0] Boa [1] Ruim ");
            novocaso.setExposicaomidia(scanner.nextInt());

            System.out.println("Método contraceptivo usado: [1] Não usado [2] Longo tempo [3] Curto tempo ");
            novocaso.setMetodo(scanner.nextInt());
            
            /* imprimir verificacao do caso inserido */
            if (validaCaso(novocaso) == true) {
                System.out.println("Caso inserido: " + novocaso);
                casook = true;
            } else  {
                System.out.println("Caso inválido, leitura não realizada.");
                casook = false;
            }
        } while (casook == false);
    }

    public static void comparaSequencial(ArrayList<Casos> listacasos, Casos novocaso, double[][] diferencas) {
        /* declaracoes locais */
        int i = 0;
        double diferencacomum = 0, diferencaeuclidiana = 0, soma = 0;
        
        for (Casos caso : listacasos) {
            /* inicializa variaveis */
            diferencacomum = 0; diferencaeuclidiana = 0; soma = 0;
            
            /* distancia euclidiana √((x1 – x2)² + (y1 – y2)²) */
            soma = Math.pow((double) (novocaso.getIdademulher() - caso.getIdademulher()), 2)
                    + Math.pow((double) (novocaso.getEducacaomulher() - caso.getEducacaomulher()), 2)
                    + Math.pow((double) (novocaso.getEducacaohomem() - caso.getEducacaohomem()), 2)
                    + Math.pow((double) (novocaso.getNumerocriancas() - caso.getNumerocriancas()), 2)
                    + Math.pow((double) (novocaso.getReligiaomulher() - caso.getReligiaomulher()), 2)
                    + Math.pow((double) (novocaso.getEmpregomulher() - caso.getEmpregomulher()), 2)
                    + Math.pow((double) (novocaso.getOcupacaohomem() - caso.getOcupacaohomem()), 2)
                    + Math.pow((double) (novocaso.getQualidadevida() - caso.getQualidadevida()), 2)
                    + Math.pow((double) (novocaso.getExposicaomidia() - caso.getExposicaomidia()), 2);
            diferencaeuclidiana = Math.sqrt(soma);
            
            /* diferenca comum */
            diferencacomum = Math.abs(novocaso.getIdademulher() - caso.getIdademulher())
                    + Math.abs(novocaso.getEducacaomulher() - caso.getEducacaomulher())
                    + Math.abs(novocaso.getEducacaohomem() - caso.getEducacaohomem())
                    + Math.abs(novocaso.getNumerocriancas() - caso.getNumerocriancas())
                    + Math.abs(novocaso.getReligiaomulher() - caso.getReligiaomulher())
                    + Math.abs(novocaso.getEmpregomulher() - caso.getEmpregomulher())
                    + Math.abs(novocaso.getOcupacaohomem() - caso.getOcupacaohomem())
                    + Math.abs(novocaso.getQualidadevida() - caso.getQualidadevida())
                    + Math.abs(novocaso.getExposicaomidia() - caso.getExposicaomidia());
            
            /* imprime diferenca comum */
            //System.out.println("Diferença comum: " + diferencacomum);
            
            /* imprime diferenca euclidiana */
            //System.out.println("Diferença euclidiana: " + diferencaeuclidiana);
            
            /* insere diferenca no vetor */
            diferencas[i][0] = (double) i; diferencas[i][1] = diferencaeuclidiana; i++;
        }
    }

    public static void listaSimilar(ArrayList<Casos> listacasos, double[][] diferencas) {
        System.out.println("Casos mais similares.");
        System.out.println("Caso [" + (int) diferencas[0][0] + "]: " + diferencas[0][1] + " (mais similar)");
        for (int i = 1; i < listacasos.size(); i++) {
            System.out.println("Caso [" + (int) diferencas[i][0] + "]: " + diferencas[i][1]);
        }
    }    
    
    public static void ordenaVetor(ArrayList<Casos> listacasos, double[][] diferencas) {
        /* arrays sort */
        //Arrays.sort(diferencas, Comparator.comparing((double[] arr) -> arr[0]));
        //Arrays.sort(diferencas, (double[] o1, double[] o2) -> Double.compare(o1[0], o2[0]));
        
        /* insertion sort */
        double index, chave;
        
        int i;
        for(int j = 1; j < listacasos.size(); j++) {
            chave = diferencas[j][1];
            index = diferencas[j][0];
            i = j-1;

            while((i >= 0) && (diferencas[i][1] >  chave)) {                   
                 diferencas[i+1][1] = diferencas[i][1];
                 diferencas[i+1][0] = diferencas[i][0];
                 i = i-1;
            }
            diferencas[i+1][1] = chave;
            diferencas[i+1][0] = index;
        }
    }
    
    public static void casoAleatorio(ArrayList<Casos> listacasos, int n) {
        /* declaracoes locais */
        Casos temp = new Casos(); /* caso temporário */
        int num = 0;
        
        for (int i = 0; i < n; i++) {
            /* gerador de numeros aleatorios */
            Random gerador = new Random(System.nanoTime());
        
            /* idade da mulher */
            num = gerador.nextInt(100) + 1;
            temp.setIdademulher(num);
            /* educacao da mulher */
            num = gerador.nextInt(4) + 1;
            temp.setEducacaomulher(num);
            /* educacao do marido */
            num = gerador.nextInt(4) + 1;
            temp.setEducacaohomem(num);
            /* numero de filhos */
            num = gerador.nextInt(11);
            temp.setNumerocriancas(num);
            /* religiao da mulher */
            num = gerador.nextInt(2);
            temp.setReligiaomulher(num);
            /* mulher trabalhando */
            num = gerador.nextInt(2);
            temp.setEmpregomulher(num);
            /* ocupação do marido */
            num = gerador.nextInt(4) + 1;
            temp.setOcupacaohomem(num);
            /* qualidade de vida */
            num = gerador.nextInt(4) + 1;
            temp.setQualidadevida(num);
            /* exposicao de midia */
            num = gerador.nextInt(2);
            temp.setExposicaomidia(num);
            /* metodo contraceptivo */
            num = gerador.nextInt(3) + 1;
            temp.setMetodo(num);
            
            /* imprime caso temporario */
            //System.out.println(temp);
            
            /* insere caso na lista */
            listacasos.add(temp);
        }
    }
    
    public static void filtragem(ArrayList<Casos> listacasos, ArrayList<Casos> filtro) {
        /* declaracoes locais */
        Scanner scanner = new Scanner( System.in );
        
        System.out.println("[1] Idade da Esposa\n"
                + "[2] Educação da Esposa (1 = Baixa / 2 = Média / 3 = Regular / 4 = Alta)\n"
                + "[3] Educação do Marido (1 = Baixa / 2 = Média / 3 = Regular / 4 = Alta)\n"
                + "[4] Número de Crianças Nascidas\n"
                + "[5] Religião da Mulher (0 = Não Islã / 1 = Islã)\n"
                + "[6] Mulher Trabalha ? (0 = Sim / 1 = Não)\n"
                + "[7] Ocupação do Marido (1, ..., 4)\n"
                + "[8] Qualidade de Vida (1 = Baixa / 2 = Média / 3 = Regular / 4 = Alta)\n"
                + "[9] Exposição de Mídia (0 = Boa / 1 = Ruim)");

        System.out.println("Informe o índice a ser filtrado: ");
        int indice = scanner.nextInt();
        System.out.println("Informe o valor chave para ser filtrado: ");
        int valor = scanner.nextInt();
        
        for (Casos caso : listacasos) {
            if (indice == 1) { /* idade da mulher */
                if (caso.getIdademulher() >= valor - 5 && caso.getIdademulher() <= valor + 5) {
                    filtro.add(caso);
                }
            } else if (indice == 2) { /* educacao da mulher */
                if (caso.getEducacaomulher() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 3) { /* educacao do homem */
                if (caso.getEducacaohomem() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 4) { /* numero de criancas */
                if (caso.getNumerocriancas() >= valor - 1 && caso.getNumerocriancas() <= valor + 1) {
                    filtro.add(caso);
                }
            } else if (indice == 5) { /* religiao da mulher */
                if (caso.getReligiaomulher() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 6) { /* mulher trabalhando? */
                if (caso.getEmpregomulher() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 7) { /* ocupacao do marido */
                if (caso.getOcupacaohomem() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 8) { /* qualidade de vida */
                if (caso.getQualidadevida() == valor) {
                    filtro.add(caso);
                }
            } else if (indice == 9) { /* exposicao de midia */
                if (caso.getExposicaomidia() == valor) {
                    filtro.add(caso);
                }
            }
        }
    }
    
    public static boolean validaCaso(Casos caso) {
        return caso.getIdademulher() >= 1 && caso.getIdademulher() <= 100
                && caso.getEducacaomulher() >= 1 && caso.getEducacaomulher() <= 4
                && caso.getEducacaohomem() >= 1 && caso.getEducacaohomem() <= 4
                && caso.getNumerocriancas() >= 0 && caso.getNumerocriancas() <= 20
                && caso.getReligiaomulher() >= 0 && caso.getReligiaomulher() <= 1
                && caso.getEmpregomulher() >= 0 && caso.getEmpregomulher() <= 1
                && caso.getOcupacaohomem() >= 1 && caso.getOcupacaohomem() <= 4
                && caso.getExposicaomidia() >= 0 && caso.getExposicaomidia() <= 1
                && caso.getMetodo() >= 1 && caso.getMetodo() <= 3;
    }
}