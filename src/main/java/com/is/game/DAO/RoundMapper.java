/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;

import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author ibby4
 */
public class RoundMapper implements RowMapper<Round> {

    @Override
    public Round mapRow(ResultSet rs, int i) throws SQLException {
        Round round = new Round();
        round.setRoundId(rs.getInt("RoundId"));
        round.setTimeOfGuess(rs.getObject("TimeOfGuess", LocalDateTime.class));
        round.setGuess(rs.getString("guess"));
        round.setGameId(rs.getInt("GameId"));
        round.setResult(rs.getString("Result"));
        
        return round;
    }
}  
   

