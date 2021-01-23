/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.service;

import com.is.game.DAO.GameDaoImpl;
import com.is.game.DAO.RoundDaoImpl;
import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ibby4
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameDaoImpl gameDao;

    @Autowired
    RoundDaoImpl roundDao;

    @Override
    public void generateAnswer(Game model) {
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            list.add(j);
        }
        Collections.shuffle(list);
        String randomDigit = "";
        for (int j = 0; j < 4; j++) {
            randomDigit += list.get(j).toString();
        }
        model.setAnswer(randomDigit);
    }

    @Override
    public String checkGuess(String ans, String gss) {
        int exactMatches = 0;
        int partialMatches = 0;

        if(gss.length() > 4){
            throw new IncorrectGuessException();
        } else 
        for (int i = 0; i < gss.length(); i++){
            for (int j = i + 1; j < gss.length(); j++) {
                if (gss.charAt(i) == gss.charAt(j)) {
                    throw new GuessWithDuplicatesException();
                }
            }
        }
    
    
        
        String result = "";

        for (int i = 0; i < ans.length(); i++) {
            for (int j = 0; j < gss.length(); j++) {
                if (ans.charAt(i) == gss.charAt(j)) {
                    if (i == j){  exactMatches++;
                } else {
                    partialMatches++;
                }
                }
            }

        }
        if (exactMatches == 4) {
            result = "You Win!";
        } else {
            result = "e:" + exactMatches + ":p:" + partialMatches;
        }
        return result;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAll();
        List<Round> rounds = roundDao.getAll();
        
        games.forEach((gme) -> {
            gameDao.associateRounds(gme, rounds);
        });
        
        return games;
    }

    @Override
    public List<Round> getRoundsByGame(int GameId) {
//        List<Round> Rounds = roundDao.getAll();
//        List<Round> gameRounds = new ArrayList<>();
//        Rounds.stream()
//                .filter((i) -> (i.getGameId() == GameId)).forEachOrdered((i) -> {
//            gameRounds.add(i);
//        });
        return roundDao.getAllbyGame(GameId);
    }

    @Override
    public Game createGame() {
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        this.generateAnswer(newGame);
        gameDao.Create(newGame);

        return newGame;
    }
    @Override
    public Round createRound(Round round){

    
    return roundDao.Create(round);
    }
    
    @Override
    public Game getGame(int id) {
        Game game = gameDao.getById(id);
        List<Round> rounds = roundDao.getAllbyGame(id);
        game.setRounds(rounds);
        return game;
    }
    
    @Override
    public void update(Round round, Game game){
        roundDao.Update(round);
        gameDao.Update(game);
    }

}
