package com.hayukleung.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hayukleung.recyclerview.LayoutManager.DemoFragment;

/**
 * RecyclerView
 * com.hayukleung.recyclerview
 * MainActivity.java
 *
 * by hayukleung
 * at 2017-03-19 21:51
 */

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.add(R.id.content, new DemoFragment());
      fragmentTransaction.commit();
    }
  }
}
