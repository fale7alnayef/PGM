package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class AdminTraineeActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    private lateinit var trainee: ArrayList<TraineeData>
    private lateinit var tempTrainee: ArrayList<TraineeData>
    private var flag:  Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_trainee)

        rv   = findViewById(R.id.trecyclerView)
        fabAdd   =   findViewById(R.id.addtrainee)
        toolBar   =   findViewById(R.id.ttoolbar)
        toolBar.title = "Trainees"
        setSupportActionBar(toolBar)


        fabAdd.setOnClickListener {
            navigateToAddTrainee()
        }
        trainee = ArrayList()
        tempTrainee = ArrayList()
        trainee.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        trainee.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        trainee.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        trainee.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        trainee.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        trainee.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        trainee.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        trainee.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        trainee.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        trainee.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        trainee.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        trainee.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        trainee.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        trainee.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        trainee.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        trainee.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)


        tempTrainee.addAll(trainee)
        rv.adapter = TraineeAdapter(this, tempTrainee)

    }

    private fun navigateToAddTrainee() {
        startActivity(Intent(applicationContext, AddTraineeActivity::class.java))

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

                    tempTrainee.clear()
                    val searchText = newText!!.lowercase(Locale.getDefault())

                    if(searchText.isNotEmpty())
                    {
                        trainee.forEach {

                            if(it.name.lowercase(Locale.getDefault()).contains(searchText))
                            {
                                tempTrainee.add(it)
                            }
                        }
                        rv.adapter!!.notifyDataSetChanged()
                    }

                    else
                    {
                        tempTrainee.clear()
                        tempTrainee.addAll(trainee)
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
                    tempTrainee.sortBy {
                        it.name
                    }
                    rv.adapter!!.notifyDataSetChanged()

                    false

                } else{
                    tempTrainee.sortByDescending{
                        it.name
                    }
                    rv.adapter!!.notifyDataSetChanged()

                    true

                }
            }
        }
        return super.onOptionsItemSelected(item)

    }

}