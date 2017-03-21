package com.hayukleung.recyclerview.ItemTouchHelper;

import java.io.Serializable;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.ItemTouchHelper
 * ItemTouchData.java
 *
 * by hayukleung
 * at 2017-03-20 18:00
 */

public class ItemTouchData implements Serializable {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
