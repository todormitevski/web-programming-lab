package mk.finki.ukim.mk.lab.model.exceptions;

public class ProductionNotFoundException extends RuntimeException{
    public ProductionNotFoundException() {
        super("Production not found exception");
    }
}
