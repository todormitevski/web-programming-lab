package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class ProductionRepository {
    public static List<Production> productionList = null;

    @PostConstruct
    public void init(){
        productionList = new ArrayList<>(5);
        IntStream.range(0, 4)
                .forEach(i ->{
                    String name = String.format("Name %d", i+1);
                    String country = String.format("Country %d", i+1);
                    String address = String.format("Address %d", i+1);
                    productionList.add(new Production(name,country,address));
                });
    }

    public List<Production> findAll(){
        return productionList;
    }


}
