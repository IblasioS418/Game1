/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.controller;

import com.is.game.DAO.GameDaoImpl;
import com.is.game.DAO.RoundDaoImpl;
import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import com.is.game.service.GameService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ibby4
 */
@RestController
@RequestMapping("/api")
public class Controller {
    
    @Autowired
    GameService service;
    
    @Autowired
    GameDaoImpl dao;
    
    @Autowired
    RoundDaoImpl roundDao;
    
    @GetMapping("/game")
    public List<Game> all() {
        List<Game> gameList = service.getAllGames();
        for (Game G : gameList) {
            if (G.getGameStatus().equalsIgnoreCase("In Progress")) {
                G.setAnswer("Hidden");
            }
        }
        
        return gameList;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public String create() {
        Game newGame = service.createGame();
        String msg = "Game Id: " + newGame.getGameId();
        return msg;
    }
    
    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) {
        Round newRound = service.createRound(round);
        int gameId = newRound.getGameId();
        Game currentGame = service.getGame(gameId);
        String result = service.checkGuess(currentGame.getAnswer(), round.getGuess());
        if (result.equalsIgnoreCase("You Win!")) {
            currentGame.setGameStatus("Finished");
         
        }
        newRound.setResult(result);
        service.update(round, currentGame);
        
        return newRound;
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findById(@PathVariable int gameId) {
        Game found = service.getGame(gameId);
        if (found == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(found);
    }
    
    @GetMapping("/round/{gameId}")
    public List<Round> findroundById(@PathVariable int gameId) {
        List<Round> rounds = service.getRoundsByGame(gameId);
        
        rounds.stream()
                .sorted(Comparator.comparing(Round::getTimeOfGuess))
                .collect(Collectors.toList());
        return rounds;
    }

}
