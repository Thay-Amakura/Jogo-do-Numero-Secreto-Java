import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class jogodeAdivinhacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int recordeFacil = Integer.MAX_VALUE;
        String recordistaFacil = "";
        int recordeMedio = Integer.MAX_VALUE;
        String recordistaMedio = "";
        int recordeDificil = Integer.MAX_VALUE;
        String recordistaDificil = "";
        
        try {
            File arquivo = new File("ranking.txt");
            if (arquivo.exists()) {
                Scanner leitor = new Scanner(arquivo);
                recordistaFacil = leitor.next();
                recordeFacil = leitor.nextInt();
                recordistaMedio = leitor.next();
                recordeMedio = leitor.nextInt();
                recordistaDificil = leitor.next();
                recordeDificil = leitor.nextInt();
                leitor.close();
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o ranking.😭");
        }
        
        boolean executando = true;
        
        while (executando) {
            System.out.println("============= 🎮Jogo do Número Secreto🎮 =============");
            System.out.println("\n Menu Principal ");
            System.out.println("1 - Jogar");
            System.out.println("2 - Ver Ranking");
            System.out.println("3 - Sair");
            int menu = scanner.nextInt();
            
            switch (menu) {
                case 1:
                    System.out.print("\nDigite seu Nome: ");
                    scanner.nextLine();
                    String nomeJogador = scanner.nextLine();
                    
                    System.out.println("Escolha seu nível de dificukdade🤔:");
                    System.out.println("1 - Fácil");
                    System.out.println("2 - Médio");
                    System.out.println("3 - Difícil");
                    int opcao = scanner.nextInt();
                    
                    int limiteMaximo;
                    String nomeNivel;
                    int limiteTentativas;
                    
                    switch (opcao) {
                        case 1:
                            limiteMaximo = 100;
                            nomeNivel = "Fácil";
                            break;
                        case 2:
                            limiteMaximo = 500;
                            nomeNivel = "Médio";
                            break;
                        case 3:
                            limiteMaximo = 1000;
                            nomeNivel = "Dicícil";
                            break;
                        default:
                            System.out.println("❌Opção inválida! Usando nível Médio.");
                            limiteMaximo = 500;
                            nomeNivel = "Médio";
                    }
                    limiteTentativas = limiteMaximo / 10;
                    int numeroSecreto = random.nextInt(limiteMaximo) + 1;
                    int palpite = 0;
                    int tentativas = 0;
                    
                    System.out.println("\n Adivinhe o número secreto entre 1 e " + limiteMaximo + ". Você tem" + limiteTentativas + " tentativas!😉");
                    
                    while (palpite != numeroSecreto && tentativas < limiteTentativas) {
                        System.out.print("Escolha um número: ");
                        palpite = scanner.nextInt();
                        tentativas ++;
                        
                        if (palpite < numeroSecreto) {
                            System.out.println("O número Secreto é maior.👆");
                        } else if (palpite > numeroSecreto) {
                            System.out.println("O número secreto é menor.👇");
                        }
                    }
                    
                    if (palpite == numeroSecreto) {
                        System.out.println("Parabéns, " + nomeJogador + "! Você acertou com " + tentativas + "tentativas.");
                        int pontuacao = (limiteMaximo * 10)/tentativas;
                        System.out.println("sua prontuação é: " + pontuacao + "pontos!");
                        
                             if (limiteMaximo == 100 && tentativas < recordeFacil) {
                            recordeFacil = tentativas;
                            recordistaFacil = nomeJogador;
                            System.out.println("🥇 Novo recorde no nível Fácil!");
                        } else if (limiteMaximo == 500 && tentativas < recordeMedio) {
                            recordeMedio = tentativas;
                            recordistaMedio = nomeJogador;
                            System.out.println("🥇 Novo recorde no nível Médio!");
                        } else if (limiteMaximo == 1000 && tentativas < recordeDificil) {
                            recordeDificil = tentativas;
                            recordistaDificil = nomeJogador;
                            System.out.println("🥇 Novo recorde no nível Difícil!");
                        }
                    } else {
                        System.out.println("💥 Você perdeu! O número secreto era " + numeroSecreto + ".");
                    }

                    // Salva ranking atualizado
                    try {
                        PrintWriter escritor = new PrintWriter("ranking.txt");
                        escritor.println(recordistaFacil + " " + recordeFacil);
                        escritor.println(recordistaMedio + " " + recordeMedio);
                        escritor.println(recordistaDificil + " " + recordeDificil);
                        escritor.close();
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar o ranking.");
                    }
                    break;

                case 2:
                    System.out.println("\n===== 💪 RANKING DE TENTATIVAS =====");
                    System.out.println("Fácil:   " + (recordeFacil == Integer.MAX_VALUE ? "Nenhum recorde ainda" : recordistaFacil + " - " + recordeFacil + " tentativas"));
                    System.out.println("Médio:   " + (recordeMedio == Integer.MAX_VALUE ? "Nenhum recorde ainda" : recordistaMedio + " - " + recordeMedio + " tentativas"));
                    System.out.println("Difícil: " + (recordeDificil == Integer.MAX_VALUE ? "Nenhum recorde ainda" : recordistaDificil + " - " + recordeDificil + " tentativas"));
                    break;

                case 3:
                    System.out.println("👋 Obrigado por jogar! Até a próxima.👋");
                    executando = false;
                    break;

                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

}
