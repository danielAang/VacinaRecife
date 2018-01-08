package br.com.recife.vacina.vacinarecife.util;

import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.recife.vacina.vacinarecife.model.Record;

/**
 * Created by morae on 07/01/2018.
 */
public final class Utils {

    public static boolean getIdades(Record records, Long dtNascimento) {

        // Inicializa as variáveis
        List<String> idades = new ArrayList<>();
        String pttIdade = "\\d+";
        String pttMeses = "meses";
        String pttAnos = "anos";
        String pttNascer = "nascer";
        boolean retorno = false;
        String idade = records.getIdade();

        // Calcula a idade em anos e meses
        LocalDate dataNascimento = new LocalDate(dtNascimento);
        LocalDate dataAtual = new LocalDate(new Date().getTime());
        Years anos = Years.yearsBetween(dataNascimento, dataAtual);
        Months meses = Months.monthsBetween(dataNascimento, dataAtual);

        // Recupera as idades enviadas no json
        Pattern r = Pattern.compile(pttIdade);
        Matcher m = r.matcher(idade);
        while (m.find()) {
            idades.add(m.group());
        }
        // Compara a idade com os meses informado no json
        r = Pattern.compile(pttMeses);
        m = r.matcher(idade);
        while (m.find()) {
            return calculaMeses(idades, meses);
        }
        // Compara a idade com os anos informado no json
        r = Pattern.compile(pttAnos);
        m = r.matcher(idade);
        while (m.find()) {
            return calculaAnos(idades, anos);
        }
        // Compara para o caso "ao nascer"
        r = Pattern.compile(pttNascer);
        m = r.matcher(idade);
        while (m.find()) {
            return calculaNascer(meses);
        }
        return retorno;
    }

    private static boolean calculaNascer(Months meses) {
        if (meses.getMonths() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean calculaMeses(List<String> idades, Months meses) {
        Integer idade1, idade2;
        boolean retorno = false;
        try {
            idade1 = Integer.parseInt(idades.get(0));
        } catch (IndexOutOfBoundsException e) {
            idade1 = null;
        }
        try {
            idade2 = Integer.parseInt(idades.get(1));
        } catch (IndexOutOfBoundsException e) {
            idade2 = null;
        }
        if (idade1 != null && idade2 != null) {
            if (meses.getMonths() >= idade1) {
                retorno = true;
            } else {
                retorno = false;
            }
            if (retorno && meses.getMonths() <= idade2) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            if (meses.getMonths() == idade1) {
                retorno = true;
            } else {
                retorno = false;
            }
        }
        return retorno;
    }

    private static boolean calculaAnos(List<String> idades, Years anos) {
        Integer idade1, idade2;
        boolean retorno = false;
        try {
            idade1 = Integer.parseInt(idades.get(0));
        } catch (IndexOutOfBoundsException e) {
            idade1 = null;
        }
        try {
            idade2 = Integer.parseInt(idades.get(1));
        } catch (IndexOutOfBoundsException e) {
            idade2 = null;
        }
        if (idade1 != null && idade2 != null) {
            if (anos.getYears() >= idade1) {
                retorno = true;
            } else {
                retorno = false;
            }
            if (retorno && anos.getYears() <= idade2) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else if (idade1 == 60) {
            if (anos.getYears() >= idade1) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            if (anos.getYears() == idade1) {
                retorno = true;
            } else {
                retorno = false;
            }
        }
        return retorno;
    }

}
