public class Currency {
    public String no;
    public String table;
    public String effectiveDate;
    public Rate[] rates;

    public Currency(Rate[] rates) {
        this.rates = rates;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }
}