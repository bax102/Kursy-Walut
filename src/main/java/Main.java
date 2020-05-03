import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> tablicaWalut = Arrays.asList("USD", "EUR", "CHF", "GBP");

        String linkWalut = "http://api.nbp.pl/api/exchangerates/tables/a?format=json";

        String linkWalut2 = "http://api.nbp.pl/api/exchangerates/tables/A/2019-11-21?format=json";

        Gson gson = new Gson();
        Currency currenciesGsonObject = gson.fromJson(CurrencyLinkToday(linkWalut), Currency.class);

        System.out.println("currency table: " + currenciesGsonObject.table);
        System.out.println("number: " + currenciesGsonObject.no);
        System.out.println("effectiveDate: " + currenciesGsonObject.effectiveDate);
        System.out.println("currenciesGsonObject.rates.length: " + currenciesGsonObject.rates.length);

        Currency currenciesGsonMonthAgo = gson.fromJson(CurrencyLinkMonthAgo(linkWalut2), Currency.class);

        int rate4 = 0;

        for (Rate rate : currenciesGsonObject.rates) {
            for (String currency : tablicaWalut) {
                if (rate.code.equals(currency)) {
                    int wartoscWPLN = (int) (100 / rate.mid);
                    System.out.println("------");
                    System.out.println("currency: " + rate.currency);
                    System.out.println("code: " + rate.code);
                    System.out.println("mid: " + rate.mid);
                    System.out.println("Za sto złotych kupiłbym tej waluty: " + wartoscWPLN + " " + rate.code);
                    rate4 = (int) rate.mid;
                }
            }
        }

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        System.out.println("currency table: " + currenciesGsonMonthAgo.table);
        System.out.println("number: " + currenciesGsonMonthAgo.no);
        System.out.println("effectiveDate: " + currenciesGsonMonthAgo.effectiveDate);
        System.out.println("currenciesGsonObject.rates.length: " + currenciesGsonMonthAgo.rates.length);

        for (Rate rate2 : currenciesGsonMonthAgo.rates) {
            for (String currency : tablicaWalut) {
                if (rate2.code.equals(currency)) {
                    int wartoscWPLN = (int) (100 / rate2.mid);
                    System.out.println("------");
                    System.out.println("currency: " + rate2.currency);
                    System.out.println("code: " + rate2.code);
                    System.out.println("mid: " + rate2.mid);
                    System.out.println("Za sto złotych kupiłbym tej waluty: " + wartoscWPLN + " " + rate2.code);
                    if ((rate2.mid - rate4) > 0) {
                        System.out.println("Gdybym sprzedał dzisiaj to co kupiłem wtedy to zarobiłbym: " + (rate2.mid - rate4) + " PLN");
                    } else {
                        System.out.println("Gdybym sprzedał dzisiaj to co kupiłem wtedy to byłym na minusie: " + (rate2.mid - rate4) + " PLN");
                    }
                }
            }
        }
    }

    public static String CurrencyLinkToday(String linkWalut) throws IOException {

        URL URLwalut = new URL(linkWalut);

        Scanner ScannerWalut = new Scanner(URLwalut.openStream());

        String currenciesRates = ScannerWalut.nextLine();

        String currenciesRatesStripped = currenciesRates.substring(1, currenciesRates.length() - 1);

        return currenciesRatesStripped;
    }

    public static String CurrencyLinkMonthAgo(String linkWalut2) throws IOException {

        URL URLwalut = new URL(linkWalut2);

        Scanner ScannerWalut = new Scanner(URLwalut.openStream());

        String currenciesRates = ScannerWalut.nextLine();

        String currenciesRatesStripped = currenciesRates.substring(1, currenciesRates.length() - 1);

        return currenciesRatesStripped;
    }
}


