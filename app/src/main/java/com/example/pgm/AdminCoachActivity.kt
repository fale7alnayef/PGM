package com.example.pgm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AdminCoachActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_coach)
        rv   = findViewById(R.id.recyclerView)
        fabAdd   =   findViewById(R.id.addcoach)
        toolBar   =   findViewById(R.id.toolbar)
       toolBar.title = "Coaches"
        setSupportActionBar(toolBar)
        val Coach = ArrayList<CoachData>()

        Coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        Coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        Coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        Coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        Coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        Coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        Coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        Coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        Coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        Coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        Coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        Coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        Coach.add(CoachData("ghassan","kl", R.drawable.download1,"0992347584","50000"))
        Coach.add(CoachData("ameer","kjhkhjl", R.drawable.download2,"0992334548","10000"))
        Coach.add(CoachData("ahmad","ds", R.drawable.download3,"09965467584","20000"))
        Coach.add(CoachData("saif","fssfaf", R.drawable.download4,"0997543904","70000"))
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        rv.adapter = CoachAdapter(this, Coach)



        /*rv.addItemDecoration(
           DividerItemDecoration(
                rv.context,
                DividerItemDecoration.VERTICAL
            )
        )
        val fab = findViewById<FloatingActionButton>(R.id.addcoach)

        fab.scaleType = ImageView.ScaleType.FIT_CENTER*/
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
   /* private fun hideFabWhileScrolling()
    {
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fabAdd.isShown) fabAdd.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) fabAdd.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    */

}