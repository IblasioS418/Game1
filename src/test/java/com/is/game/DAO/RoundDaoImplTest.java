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

/**
 *
 * @author ibby4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameApplication.class)
public class RoundDaoImplTest {

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
        //        
        List<Game> games = gameDao.getAll();
        for (Game game : games) {
            gameDao.Delete(game.getGameId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAll method, of class RoundDaoImpl.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        
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
        newRound2 = roundDao.Create(newRound2);

        List<Round> Rounds = roundDao.getAll();

        assertEquals(2, Rounds.size());
//        assertTrue(Rounds.contains(newRound));
//        assertTrue(Rounds.contains(newRound2));
    }

    /**
     * Test of Create method, of class RoundDaoImpl.
     */
    @Test
    public void testCreate() {
        System.out.println("Create");
        
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);

        Round newRound = new Round();
        newRound.setGameId(newGame.getGameId());
        newRound.setGuess("2134");
        newRound = roundDao.Create(newRound);

        Round fromDao = roundDao.getById(newRound.getRoundId());

        assertEquals(newRound, fromDao);
    }

    /**
     * Test of Update method, of class RoundDaoImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");
        
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);
        
        Round newRound = new Round();
        newRound.setGameId(newGame.getGameId());
        newRound.setGuess("2134");
        newRound = roundDao.Create(newRound);

        Round fromDao = roundDao.getById(newRound.getRoundId());

        assertEquals(newRound, fromDao);

        newRound.setGuess("3241");
        roundDao.Update(newRound);

        assertNotEquals(newRound, fromDao);
        fromDao = roundDao.getById(newRound.getRoundId());
        assertEquals(newRound, fromDao);

    }

    /**
     * Test of getAllbyGame method, of class RoundDaoImpl.
     */
    @Test
    public void testGetAllbyGame() {
        System.out.println("getAllbyGame");

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
        newRound2.setGuess("3154");
        newRound2 = roundDao.Create(newRound);

        List<Round> Rounds = roundDao.getAll();

        List<Round> fromDao = roundDao.getAllbyGame(newGame.getGameId());

        assertEquals(Rounds.size(), fromDao.size());
//        assertTrue(fromDao.contains(newRound));
//        assertTrue(fromDao.contains(newRound2));
    }

    /**
     * Test of getById method, of class RoundDaoImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        
        Game newGame = new Game();
        newGame.setGameStatus("In Progress");
        newGame.setAnswer("1234");
        newGame = gameDao.Create(newGame);
        
        Round newRound = new Round();
        newRound.setGameId(newGame.getGameId());
        newRound.setGuess("2134");
        newRound = roundDao.Create(newRound);

        Round fromDao = roundDao.getById(newRound.getRoundId());
        assertEquals(newRound, fromDao);
    }

}
