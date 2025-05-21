import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "0468c38e6adcf22edea734a6";
        String baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/";

        while (true) {
            System.out.println("\n====== Bem-vindo ao Conversor de Moedas ======");
            System.out.println("Escolha uma opção de conversão:");
            System.out.println("1. USD para BRL");
            System.out.println("2. BRL para USD");
            System.out.println("3. EUR para BRL");
            System.out.println("4. BRL para EUR");
            System.out.println("5. USD para EUR");
            System.out.println("6. EUR para USD");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();

            String baseCurrency = "", targetCurrency = "";
            switch (opcao) {
                case "1":
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case "2":
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case "3":
                    baseCurrency = "EUR";
                    targetCurrency = "BRL";
                    break;
                case "4":
                    baseCurrency = "BRL";
                    targetCurrency = "EUR";
                    break;
                case "5":
                    baseCurrency = "USD";
                    targetCurrency = "EUR";
                    break;
                case "6":
                    baseCurrency = "EUR";
                    targetCurrency = "USD";
                    break;
                case "0":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            try {
                System.out.print("Digite o valor para converter: ");
                double amount = Double.parseDouble(scanner.nextLine());

                URL url = new URL(baseUrl + baseCurrency);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(reader);

                Gson gson = new Gson();
                Map<String, Object> response = gson.fromJson(br, new TypeToken<Map<String, Object>>() {}.getType());

                if (response.get("result").equals("success")) {
                    Map<String, Double> conversionRates = (Map<String, Double>) response.get("conversion_rates");
                    if (conversionRates.containsKey(targetCurrency)) {
                        double rate = conversionRates.get(targetCurrency);
                        double convertedAmount = amount * rate;
                        System.out.printf("Resultado: %.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
                    } else {
                        System.out.println("Moeda alvo não encontrada.");
                    }
                } else {
                    System.out.println("Erro ao consultar a API.");
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
