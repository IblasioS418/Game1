/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;

import com.is.game.DTO.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ibby4
 */
public class GameMapper implements RowMapper<Game>{

    @Override
    public Game mapRow(ResultSet rs, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("Gameid"));
        game.setGameStatus(rs.getString("Status"));
        game.setAnswer(rs.getString("Answer"));
        return game;
    }
    
}
