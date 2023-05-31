package aedagencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AedAgencia {

    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        TadCandidato candidatos = initCandidato();
        menu(candidatos);

    }

    private static void menu(TadCandidato tadCandidato) {
        int opcao = 0;

        do {
            System.out.println("=========================================");
            System.out.println("*****   AGÊNCIA DE RELACIONAMENTO    ****");
            System.out.println("(1) Menu Gerenciamento");
            System.out.println("(2) Menu Candidato");
            System.out.println("(3) Sair");
            System.out.println("=========================================");

            System.out.print("Escolha uma opcao: ");
            opcao = scn.nextInt();

            while ((opcao > 3) || (opcao <= 0)) {
                System.out.print("!!! OPCAO INVALIDA !!! ");
                opcao = scn.nextInt();
            }

            if (opcao == 2) {
                menu_candidato(tadCandidato);
            } else if (opcao == 1) {
                menuGerencia(tadCandidato);
            }
        } while (opcao != 3);
    }

    private static void menuGerencia(TadCandidato tadCandidato) {
        int opcao = 0;
        System.out.println("=========================================");
        System.out.println("*******       GERENCIAMENTO       *******");
        System.out.println("(1) Inserir/Cadastrar grau de interesse de um candidato");
        System.out.println("(2) Listar o numero e a lista dos nomes de potenciais companheiros de um candidato");
        System.out.println("(3) Listar o nome do candidato com maior numero de potenciais companheiros");
        System.out.println("(4) Listar nome dos candidatos sem companheiros");
        System.out.println("(5) Listar nome de candidatos com interesse em uma determinada area");
        System.out.println("(6) Listar nome de candidatos que residem em um determinado estado");
        System.out.println("(7) Sair");
        System.out.println("=========================================");

        System.out.print("Escolha uma opcao: ");
        opcao = scn.nextInt();

        while ((opcao > 7) || (opcao <= 0)) {
            System.out.print("Escolha uma opcao valida ( 1 ate 7): ");
            opcao = scn.nextInt();
        }

        switch (opcao) {
            case 1:

                System.out.println("Informe o id do candidato(a): ");
                int idd = scn.nextInt();
                System.out.println("Informe o grau de interesse desejado: ");
                int grauu = scn.nextInt();
                do {
                    System.out.println("Grau de interesse IVALIDO! ");
                    System.out.println("Informe o grau de interesse desejado: ");
                    grauu = scn.nextInt();
                } while (grauu < 0);

                Candidato cands = tadCandidato.get(idd);
                cands.setGrauInteresse(grauu);
                break;
            case 2:
                System.out.println("Informe o id do candidato(a): ");
                int id = scn.nextInt();
                Candidato cand = tadCandidato.get(id);

                if (cand.getGrauInteresse() == 0) {
                    System.out.println("Informe o grau de interesse desejado: ");
                    int grau = scn.nextInt();
                    cand.setGrauInteresse(grau);
                }

                TadGenerica<String> aux = TadCandidato.nCompanheiros(tadCandidato, cand);

                System.out.println("O numero de potenciais companheiros é:  " + aux.size());
                System.out.println("Os nommes dos potenciais companheiros são:  " + aux.toString());

                break;

            case 3:
                long maior = 0;
                String nomeMaior = null;
                for (int i = 0; i < tadCandidato.getSize(); i++) {
                    Candidato candd = tadCandidato.get(i);
                    TadGenerica<String> auxx = TadCandidato.nCompanheiros(tadCandidato, candd);
                    if (auxx.size() >= maior) {
                        nomeMaior = candd.getNome();
                        maior = auxx.size();
                    } else {
                        maior = maior;
                        nomeMaior = nomeMaior;
                    }

                }
                System.out.println("O candidato com maior numero de companheiros é :  " + nomeMaior);

                break;
            case 4:
                System.out.println("Os candidatos sem companheiros sao: ");
                for (int i = 0; i < tadCandidato.getSize(); i++) {
                    Candidato candd = tadCandidato.get(i);
                    TadGenerica<String> auxx = TadCandidato.nCompanheiros(tadCandidato, candd);
                    if (auxx.size() == 0) {
                        System.out.println(candd.getNome());
                    }
                }
                break;
            case 5:
                System.out.println("Escolha entre: ESPORTES - ARTES - MUSICA - "
                        + " CINEMA - TECNOLOGIA - ANIMAIS - GASTRONOMIA - CIÊNCIAS)");
                System.out.print("Informe a area que gostaria de checar:  ");
                String areac = scn.next();
                if (areac.equals("ESPORTES") || areac.equals("MUSICA") || areac.equals("CINEMA") || areac.equals("ARTES")
                        || areac.equals("CINEMA") || areac.equals("ANIMAIS") || areac.equals("GASTRONOMIA")
                        || areac.equals("CIENCIAS") || areac.equals("TECNOLOGIA")) {

                    TadGenerica<String> nomes = tadCandidato.nCandidatosIntesseArea(areac, tadCandidato);

                    if (nomes.size() == 0) {
                        System.out.println("Nenhum candidato tem interesse nesta area.");

                    } else {
                        System.out.println("Os candidatos com interesse nessa area são:  ");

                        for (int i = 0; i < nomes.size(); i++) {
                            System.out.println(nomes.get(i));
                        }
                    }
                } else {
                    System.out.println("Area inválida (Escolha entre: ESPORTES, ARTES, MUSICA,"
                            + " CINEMA, TECNOLOGIA, ANIMAIS, GASTRONOMIA, CIÊNCIAS)");

                    throw new IllegalArgumentException("Area iválida (Escolha entre: ESPORTES, ARTES, MUSICA,"
                            + " CINEMA, TECNOLOGIA, ANIMAIS, GASTRONOMIA, CIÊNCIAS)");
                }
                break;
            case 6:
                System.out.println("Informe a sigla do estado de sua escolha: ");
                String sigla = scn.next();
                System.out.println("Os candidatos que residem neste estado são: ");

                for (int i = 0; i < tadCandidato.listar_mesmo_estado(sigla).size(); i++) {
                    System.out.println(tadCandidato.listar_mesmo_estado(sigla).get(i));
                }
                break;
        }
    }

    private static void menu_candidato(TadCandidato tadCandidato) {
        int op = 0;
        System.out.println("=========================================");
        System.out.println("*******       MENU CANDIDATO      *******");
        System.out.println("(1) Verificar potencial companheiro");
        System.out.println("(2) Verificar estado de residencia");
        System.out.println("(3) Cadastrar Candidato");
        System.out.println("(4) Excluir Candidato");
        System.out.println("(5) Listar todos os Candidatos");
        System.out.println("(6) Sair");
        System.out.println("=========================================");

        System.out.print("Escolha uma opcao: ");
        op = scn.nextInt();
        while ((op > 7) || (op <= 0)) {
            System.out.print("Escolha uma opcao valida (1 ate 6): ");
            op = scn.nextInt();
        }

        switch (op) {
            case 1:
                boolean potencial = false;
                System.out.println("Informe o id do primeiro candidato");
                int id1 = scn.nextInt();
                System.out.println("Informe o id do segundo candidato");
                int id2 = scn.nextInt();
                System.out.println("Informe o grau de interesse desejado");
                int grau = scn.nextInt();
                Candidato cand1 = tadCandidato.get(id1);
                cand1.setGrauInteresse(grau);
                Candidato cand2 = tadCandidato.get(id2);
                cand2.setGrauInteresse(grau);

                potencial = tadCandidato.verificaPotencialCandidato(cand1, cand2);
                if (potencial) {
                    System.out.println("Os candidatos " + cand1.getNome() + " e " + cand2.getNome() + " SAO potenciais companheiros.");
                } else {
                    System.out.println("Os candidatos " + cand1.getNome() + " e " + cand2.getNome() + " NAO são potenciais companheiros.");
                }
                break;
            case 2:
                boolean reside = false;
                System.out.println("Informe a sigla do estado:");
                String sigla = scn.next();
                System.out.println("Informe o id do candidato:");
                long id = scn.nextInt();

                Candidato cand = tadCandidato.get(id);

                reside = tadCandidato.reside(cand, sigla);

                if (reside) {
                    System.out.println("O candidato " + cand.getNome() + " reside nesse estado.");
                } else {
                    System.out.println("O candidato" + cand.getNome() + " não reside nesse estado.");
                }
                break;
            case 3:
                
                System.out.println("Informe o nome do candidato:");
                String nomeC = scn.next();
                System.out.println("Informe o estado do candidato:");
                String estadoC = scn.next();
                System.out.println("Informe o sexo do candidato:");
                String sexoC = scn.next();
                System.out.println("Informe o sexo de interesse do candidato:");
                String sexoInteresseC = scn.next();
                System.out.println("Em quantas areas o candidato tem interesse?");
                int qtdAreaInteresseC = scn.nextInt();
                System.out.println("Informe quais areas o candidato tem interesse");
                System.out.println("Escolha entre: ESPORTES - ARTES - MUSICA - "
                        + " CINEMA - TECNOLOGIA - ANIMAIS - GASTRONOMIA - CIÊNCIAS)");

                TadGenerica<String> areaInteresseC = new TadGenerica<>();
                for (int x = 0; x < qtdAreaInteresseC; x++) {
                    String areaInteresse = scn.next();
                    areaInteresseC.add(areaInteresse);
                }
                Candidato c = new Candidato(nomeC, estadoC, sexoC, sexoInteresseC, areaInteresseC);
                tadCandidato.add(c);
                break;
            case 4:
                System.out.println("Informe o id do candidato que deseja excluir: ");
                long idE = scn.nextInt();
                Candidato candE = tadCandidato.get(idE);
                tadCandidato.excluir(candE);

                //Tad.candidato.excluir():
                break;
            case 5:
                System.out.println(tadCandidato.toString());

                break;
        }
    }

    static TadCandidato initCandidato() throws IOException {
        // pega o arquivo
        Path arquivo = Paths.get("C:/Users/barro/Documents/NetBeansProjects/Trabalho AED/AedAgencia-main (1)/AedAgencia-main/AedAgencia/src/aedagencia/candidatos.txt");

        // pega as linhas
        List<String> linhas = Files.readAllLines(arquivo);

        TadCandidato lista = new TadCandidato();
        for (int i = 0; i < linhas.size(); i++) {
            String[] v = linhas.get(i).split(",");
            String nome = v[0];
            String estado = v[1];
            String sexo = v[2];
            String sexoInteresse = v[3];
            int NdeAreas = Integer.parseInt(v[4]);
            TadGenerica<String> areaInteresse = new TadGenerica<>();
            for (int x = 6; x <= NdeAreas + 4; x++) {
                areaInteresse.add(v[x]);
            }

            Candidato l = new Candidato(nome, estado, sexo, sexoInteresse, areaInteresse);
            lista.add(l);
        }
        return lista;
    }

}
