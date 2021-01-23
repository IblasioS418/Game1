/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DTO;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ibby4
 */
public class Game {
    int GameId;
    String GameStatus;
    String Answer;
    private List<Round> Rounds;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.GameId;
        hash = 61 * hash + Objects.hashCode(this.GameStatus);
        hash = 61 * hash + Objects.hashCode(this.Answer);
        hash = 61 * hash + Objects.hashCode(this.Rounds);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.GameId != other.GameId) {
            return false;
        }
        if (!Objects.equals(this.GameStatus, other.GameStatus)) {
            return false;
        }
        if (!Objects.equals(this.Answer, other.Answer)) {
            return false;
        }
        if (!Objects.equals(this.Rounds, other.Rounds)) {
            return false;
        }
        return true;
    }
    
    

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(String GameStatus) {
        this.GameStatus = GameStatus;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public List<Round> getRounds() {
        return Rounds;
    }

    public void setRounds(List<Round> Rounds) {
        this.Rounds = Rounds;
    }
    
    
}
