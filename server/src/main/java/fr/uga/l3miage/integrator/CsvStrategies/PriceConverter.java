package fr.uga.l3miage.integrator.CsvStrategies;


import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class PriceConverter extends AbstractBeanField<Double,String> {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException {
        String number = value.replace("€", "").trim();
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}