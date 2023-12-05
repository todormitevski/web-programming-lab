package mk.finki.ukim.mk.lab.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GenerateStartingRows {
    @Autowired
    private MovieRepository jpaMovieRepository;
    @Autowired
    private ProductionRepository jpaProductionRepository;
    @Autowired
    private UserRepository jpaUserRepository;

    List<Production> pList = new ArrayList<>();
    List<Movie> mList = new ArrayList<>();
    Random rand = new Random();

    @PostConstruct
    @Transactional
    public void addProductions() {

        if(jpaProductionRepository.findAll().isEmpty()){
            for(int i=1;i<=3;i++){
                Production production = new Production(
                        String.format("prod %d", i),
                        String.format("country %d", i),
                        String.format("address %d", i)
                );
                pList.add(production);
            }
            jpaProductionRepository.saveAll(pList);
        }

        if(jpaMovieRepository.findAll().isEmpty()){
            for(int i=1;i<=5;i++){
                Production production = jpaProductionRepository.findAll()
                        .stream()
                        .findFirst()
                        .orElseThrow();
                Movie movie = new Movie(
                        String.format("title %d", i),
                        String.format("summary %d", i),
                        Math.round((rand.nextDouble() * 4 + 1) * 10) / 10.0,
                        production
                );
                mList.add(movie);
            }
            jpaMovieRepository.saveAll(mList);
        }

        if(jpaUserRepository.findAll().isEmpty()){
            User user = new User();
            user.setUsername("todorm");
            jpaUserRepository.save(user);
        }

        jpaProductionRepository.saveAll(pList);
        jpaMovieRepository.saveAll(mList);
    }
}
