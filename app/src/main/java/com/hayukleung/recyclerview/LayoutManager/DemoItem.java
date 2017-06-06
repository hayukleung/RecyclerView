package com.hayukleung.recyclerview.LayoutManager;

import java.io.Serializable;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.NestedScrolling
 * NestedScrollingItem.java
 *
 * by hayukleung
 * at 2017-03-21 11:05
 */

class DemoItem implements Serializable {

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
