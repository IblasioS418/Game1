/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;

import com.is.game.DTO.Game;
import com.is.game.DTO.Round;
import com.is.game.GameApplication;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 *
 * @author ibby4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameApplication.class)
public class GameDaoImplTest {

    @Autowired
    RoundDaoImpl roundDao;

    @Autowired
    GameDaoImpl gameDao;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        List<Round> rounds = roundDao.getAll();
        for (Round round : rounds) {
            roundDao.Delete(round.getRoundId());
        }
        List<Game> games = gameDao.getAll();
        for (Game game : games) {
            gameDao.Delete(game.getGameId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Create method, of class GameDaoImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create");
        GameDaoImpl instance = gameDao;
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");

        newGame = instance.Create(newGame);
        Game fromDao = gameDao.getById(newGame.getGameId());
        assertEquals(newGame, fromDao);

    }

    /**
     * Test of getAll method, of class GameDaoImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");

        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);

        Game newGame2 = new Game();
        newGame2.setGameStatus("In Progress");
        newGame2.setAnswer("1246");
        newGame2 = gameDao.Create(newGame2);

        List<Game> games = gameDao.getAll();

        assertEquals(2, games.size());
        assertTrue(games.contains(newGame));
        assertTrue(games.contains(newGame2));
    }

    /**
     * Test of getById method, of class GameDaoImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
       
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);

        Game fromDao = gameDao.getById(newGame.getGameId());
        assertEquals(newGame, fromDao);

    }

    /**
     * Test of Update method, of class GameDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");

        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);

        Game fromDao = gameDao.getById(newGame.getGameId());

        assertEquals(newGame, fromDao);

        newGame.setGameStatus("Finished");
        gameDao.Update(newGame);

        assertNotEquals(newGame, fromDao);
        fromDao = gameDao.getById(newGame.getGameId());
        assertEquals(newGame, fromDao);

    }

    /**
     * Test of associateRounds method, of class GameDaoImpl.
     */
    @Test
    public void testAssociateRounds() {
        System.out.println("associateRounds");
        
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);
        
        Round newRound = new Round();
        newRound.setGameId(newGame.getGameId());
        newRound.setGuess("2134");
        newRound = roundDao.Create(newRound);
        
        Round newRound2 = new Round();
        newRound2.setGameId(newGame.getGameId());
        newRound2.setGuess("2134");
        newRound2 = roundDao.Create(newRound);
       
        List<Round> Rounds = roundDao.getAll();
       
        newGame = gameDao.associateRounds(newGame, Rounds);
        assertEquals(newGame.getRounds().size() , Rounds.size());
        
    }

}
