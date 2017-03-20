package com.hayukleung.recyclerview.diffutil;

import java.io.Serializable;

/**
 * RecyclerView
 * com.hayukleung.recyclerview.diffutil
 * DiffUtilItem.java
 *
 * by hayukleung
 * at 2017-03-19 22:17
 */

public class DiffUtilItem implements Serializable {

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

  @Override public boolean equals(Object obj) {
    if (null == obj) {
      return false;
    }
    if (!(obj instanceof DiffUtilItem)) {
      return false;
    }
    return id == ((DiffUtilItem) obj).id;
  }

  @Override public int hashCode() {
    return id;
  }
}
