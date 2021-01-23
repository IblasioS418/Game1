/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;

import com.is.game.DTO.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ibby4
 */
@Repository
public class RoundDaoImpl implements Dao<Round> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RoundDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Round> getAll() {
        final String sql = "SELECT RoundId, GameId, Guess, TimeOfGuess, Result FROM Round;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    @Override
    public void Delete(int roundId) {
        final String sql = "DELETE FROM Round WHERE RoundId = ?;";
        jdbcTemplate.update(sql, roundId);
    }

    @Override
    public Round Create(Round model) {

        final String sql = "INSERT INTO Round(GameId, Guess, TimeOfGuess) VALUES (?,?,?);";

        model.setTimeOfGuess(LocalDateTime.now());

        jdbcTemplate.update(sql, model.getGameId(), model.getGuess(), model.getTimeOfGuess());

        model.setRoundId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        return model;

    }

    @Override
    public void Update(Round model) {
        final String sql = "UPDATE Round SET "
                + "Guess = ?, "
                + "Result = ? "
                + "WHERE roundId = ? ";
        jdbcTemplate.update(sql,
                model.getGuess(),
                model.getResult(),
                model.getRoundId());
    }

    public List<Round> getAllbyGame(int gameId) {
        final String sql = "SELECT RoundId, GameId, Guess, TimeOfGuess, Result FROM Round WHERE GameId = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    @Override
    public Round getById(int roundId) {
        final String sql = "SELECT Roundid, Gameid, Guess, TimeOfGuess, Result "
                + "FROM Round WHERE roundid = ?;";

        return jdbcTemplate.queryForObject(sql, new RoundMapper(), roundId);
    }
}
