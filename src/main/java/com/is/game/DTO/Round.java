/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author ibby4
 */
public class Round {
    int RoundId;
    int GameId;
    String Guess;
    String Result;
    LocalDateTime TimeOfGuess;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.RoundId;
        hash = 53 * hash + this.GameId;
        hash = 53 * hash + Objects.hashCode(this.Guess);
        hash = 53 * hash + Objects.hashCode(this.Result);
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
        final Round other = (Round) obj;
        if (this.RoundId != other.RoundId) {
            return false;
        }
        if (this.GameId != other.GameId) {
            return false;
        }
        if (!Objects.equals(this.Guess, other.Guess)) {
            return false;
        }
        if (!Objects.equals(this.Result, other.Result)) {
            return false;
        }
        return true;
    }
 
    

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    
    public int getRoundId() {
        return RoundId;
    }

    public void setRoundId(int RoundId) {
        this.RoundId = RoundId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int GameId) {
        this.GameId = GameId;
    }



    public String getGuess() {
        return Guess;
    }

    public void setGuess(String Guess) {
        this.Guess = Guess;
    }

    public LocalDateTime getTimeOfGuess() {
        return TimeOfGuess;
    }

    public void setTimeOfGuess(LocalDateTime TimeOfGuess) {
        this.TimeOfGuess = TimeOfGuess;
    }
    
    
}
