package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList


class AdminCoachActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    private lateinit var coach: ArrayList<CoachData>
    private lateinit var tempCoach: ArrayList<CoachData>
    private var flag:  Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_coach)
        rv   = findViewById(R.id.crecyclerView)
        fabAdd   =   findViewById(R.id.addcoach)
        toolBar   =   findViewById(R.id.ctoolbar)
       toolBar.title = "Coaches"
        setSupportActionBar(toolBar)

        fabAdd.setOnClickListener {
            navigateToAddCoach()
        }


            coach = ArrayList()
            tempCoach = ArrayList()
        coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)


        tempCoach.addAll(coach)

        rv.adapter = CoachAdapter(this, tempCoach)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)


        object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    return true
            }

        }
        val searchItem = menu.findItem(R.id.search)
        val searchView  = searchItem?.actionView as SearchView
        searchView.queryHint = "Type"

        searchView.setOnQueryTextListener (

            object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        @SuppressLint("NotifyDataSetChanged")
                        override fun onQueryTextChange(newText: String?): Boolean {

                            tempCoach.clear()
                            val searchText = newText!!.lowercase(Locale.getDefault())

                            if(searchText.isNotEmpty())
                            {
                                coach.forEach {

                                    if(it.name.lowercase(Locale.getDefault()).contains(searchText))
                                    {
                                        tempCoach.add(it)
                                    }
                                }
                                rv.adapter!!.notifyDataSetChanged()
                            }

                            else
                            {
                                tempCoach.clear()
                                tempCoach.addAll(coach)
                                rv.adapter!!.notifyDataSetChanged()

                            }
                            return true
                        }


                    }

        )

        return super.onCreateOptionsMenu(menu)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_by -> {
                flag = if (flag) {
                    tempCoach.sortBy {
                        it.name
                    }
                    rv.adapter!!.notifyDataSetChanged()

                    false

                } else{
                    tempCoach.sortByDescending{
                        it.name
                    }
                    rv.adapter!!.notifyDataSetChanged()

                    true

                }
            }
        }
        return super.onOptionsItemSelected(item)

    }
    private fun navigateToAddCoach() {
        startActivity(Intent(applicationContext, AddCoachActivity::class.java))

    }
}