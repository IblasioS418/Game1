/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.service;

import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface GameService {
    Game createGame();
    Round createRound(Round round);
    void generateAnswer(Game model);
    String checkGuess(String ans, String gss);//exception if guess is incorrect format
    Game getGame(int id);// exception if game does not exist
    List<Game> getAllGames();
    List<Round> getRoundsByGame(int GameId);// exception if game does not exist
    void update(Round round, Game game);
}
