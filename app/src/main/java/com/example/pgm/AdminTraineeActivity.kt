package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdminTraineeActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_trainee)

        rv   = findViewById(R.id.recyclerView)
        fabAdd   =   findViewById(R.id.addtrainee)
        toolBar   =   findViewById(R.id.toolbar)
        toolBar.title = "Trainees"
        setSupportActionBar(toolBar)
        val user = ArrayList<TraineeData>()
        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        rv.adapter = TraineeAdapter(this, user)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)



        val   oAEL = object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }

        }
        val menuItem = menu.findItem(R.id.search)
        val searchView  = menuItem?.actionView as SearchView

        searchView.queryHint = "Type"

        return super.onCreateOptionsMenu(menu)
    }

}