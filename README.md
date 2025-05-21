# Conversor de Moedas em Java

Este projeto é um conversor de moedas simples implementado em Java, utilizando a API gratuita [ExchangeRate-API](https://www.exchangerate-api.com/) para obter taxas de câmbio em tempo real.

## Funcionalidades
- Interface de texto via console
- Menu com 6 opções de conversões
- Conversão utilizando cotações atualizadas em tempo real
- Consumo da API usando `HttpURLConnection`
- Desserialização do JSON com `Gson`

## Tecnologias
- Java 11+
- Gson
- ExchangeRate-API

## Executando o projeto
1. Clone este repositório
2. Configure seu ambiente Java (JDK 11 ou superior)
3. Compile e execute o projeto:
   ```bash
   javac -cp gson-2.10.1.jar *.java
   java -cp .:gson-2.10.1.jar CurrencyConverter
   ```
4. Siga as instruções no console
