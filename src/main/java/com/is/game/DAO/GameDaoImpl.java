/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;

import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ibby4
 */
@Repository
public class GameDaoImpl implements Dao<Game> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    RoundDaoImpl roundDao;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Game Create(Game model) {
        final String sql = "INSERT INTO Game(Status, Answer) VALUES (?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, model.getGameStatus());
            statement.setString(2, model.getAnswer());
            return statement;

        }, keyHolder);
        model.setGameId(keyHolder.getKey().intValue());

        return model;
    }

    @Override
    public List<Game> getAll() {
        final String sql = "SELECT Gameid, status, answer FROM Game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getById(int Gameid) {
        final String sql = "SELECT Gameid, Status, answer "
                + "FROM Game WHERE Gameid = ?;";

        return jdbcTemplate.queryForObject(sql, new GameMapper(), Gameid);
    }

    @Override
    public void Update(Game model) {
        final String sql = "UPDATE Game SET "
                + "Status = ?, "
                + "Answer = ?"
                + "WHERE gameId = ? ";
        jdbcTemplate.update(sql,
                model.getGameStatus(),
                model.getAnswer(),
                model.getGameId());
    }

    @Override

    public void Delete(int gameId) {
        final String sql = "DELETE FROM Game WHERE Gameid = ?;";
        jdbcTemplate.update(sql, gameId);
    }

    public Game associateRounds(Game game, List<Round> Rounds) {
        Rounds = roundDao.getAllbyGame(game.getGameId());
        game.setRounds(Rounds);
        return game;
    } 

}
