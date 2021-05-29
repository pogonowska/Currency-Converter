package Tests;

import CurrencyConverter.CurrencyFileReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

public class CurrencyReaderTests {
    @Test
    public void inOutTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        List<String> currecnies = reader.readCurrencies();
        List<String> output = Arrays.asList("USD", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF",
                "PLN", "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "TRY", "AUD", "BRL",
                "CAD", "CNY", "HKD", "IDR", "ILS", "INR", "KRW", "MXN", "MYR", "NZD", "PHP",
                "SGD", "THB", "ZAR");
        Assert.assertEquals(output, currecnies);
    }

    @Test
    public void USDValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double USDvalue = reader.readRate("USD");
        Assert.assertEquals(1.2198, USDvalue, 0.0);
    }

    @Test
    public void ISKValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double ISKvalue = reader.readRate("ISK");
        Assert.assertEquals(146.70, ISKvalue, 0.0);
    }

    @Test
    public void RUBValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double RUBvalue = reader.readRate("RUB");
        Assert.assertEquals(89.5360, RUBvalue, 0.0);
    }

    @Test
    public void ILSValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double ILSvalue = reader.readRate("ILS");
        Assert.assertEquals(3.9619, ILSvalue, 0.0);
    }

    @Test
    public void PHPValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double PHPvalue = reader.readRate("PHP");
        Assert.assertEquals(58.575, PHPvalue, 0.0);
    }

    @Test
    public void NoExistingValueTest() throws FileNotFoundException {
        CurrencyFileReader reader = new CurrencyFileReader("src\\CurrencyConverter\\eurofxref-daily.xml");
        double ACBvalue = reader.readRate("ABC");
        Assert.assertEquals(-1, ACBvalue, 0.0);
    }

}