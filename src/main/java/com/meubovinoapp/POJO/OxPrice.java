package com.meubovinoapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OxPrice implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Animal ox;

    public static Double ibovespaData = 5.45;

    public static void main(String[] args) {
        // Crie um Timer para agendar a atualização diária
        Timer timer = new Timer();

        // Agende uma tarefa para atualizar o valor todos os dias às 00:00
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Chame uma função para buscar o último valor do Ibovespa
                Double novoValor = buscarUltimoValorDoIbovespa();

                if (novoValor != null) {
                    ibovespaData = novoValor;
                    System.out.println("Valor atualizado para: " + ibovespaData);
                } else {
                    System.out.println("Não foi possível atualizar o valor do Ibovespa.");
                }
            }
        }, 0, 24 * 60 * 60 * 1000); // Atualizar a cada 24 horas (86400000 milissegundos)
    }

    // Função para buscar o último valor do Ibovespa (substitua com sua lógica real)
    private static Double buscarUltimoValorDoIbovespa() {
        // Implemente a lógica para buscar o valor do Ibovespa aqui
        // Por exemplo, você pode fazer uma solicitação HTTP para um serviço que forneça esse valor em tempo real.
        // Retorne o valor ou null se não conseguir obter os dados.
        return 6.0; // Exemplo de valor fixo para fins de demonstração
    }
}

