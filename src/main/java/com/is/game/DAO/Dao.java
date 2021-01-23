/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.is.game.DAO;
import java.util.List;

/**
 *
 * @author ibby4
 */
public interface Dao<T> {
  T Create(T model); 
  List<T> getAll();
  T getById(int id);
  void Update(T model);
  void Delete(int id);
}
