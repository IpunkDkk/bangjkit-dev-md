package com.example.jeniskucing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCats: RecyclerView
    private val list = ArrayList<Cat>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvCats = findViewById(R.id.rv_cats)
        rvCats.setHasFixedSize(true)
        list.addAll(getListCats())
        showRecyclerList()
        this.setTitle("List Kucing")
    }

    private fun showRecyclerList() {
        rvCats.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListCatAdapter(list)
        rvCats.adapter = listCatAdapter
    }

    private fun getListCats(): Collection<Cat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataCaracter = resources.getStringArray(R.array.data_caracter)
        val listCat = ArrayList<Cat>()
        for (i in dataName.indices){
            val cat = Cat(dataName[i], dataDescription[i], dataPhoto[i], dataCaracter[i])
            listCat.add(cat)
        }
        return listCat
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_page, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val aboutActivity = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(aboutActivity)
        return super.onOptionsItemSelected(item)
    }
}