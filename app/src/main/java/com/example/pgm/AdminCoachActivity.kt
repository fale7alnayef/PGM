package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class AdminCoachActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    private lateinit var coach: ArrayList<CoachData>
    private lateinit var tempCoach: ArrayList<CoachData>
    private var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_coach)


        rv = findViewById(R.id.crecyclerView)
        fabAdd = findViewById(R.id.addcoach)
        toolBar = findViewById(R.id.ctoolbar)
        toolBar.title = "Coaches"
        setSupportActionBar(toolBar)

        coach = ArrayList()

        val Token = "Bearer " + Data.Token
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.110:8000/api/admin/show_all_coaches"

        val jsonObject = object : JsonObjectRequest(Method.POST, url, null, {
            try {
                val coacharray = it.getJSONArray("coaches")
                for (i in 0..coacharray.length() - 1) {
                    val first_name = coacharray.getJSONObject(i).getString("first_name")
                    val last_name = coacharray.getJSONObject(i).getString("last_name")
                    val email = coacharray.getJSONObject(i).getString("email")
                    val phone_number = coacharray.getJSONObject(i).getString("phone_number")
                    val birthday = coacharray.getJSONObject(i).getString("birthday")
                    val id = coacharray.getJSONObject(i).getString("id")
                    coach.add(
                        CoachData(
                            "$first_name $last_name",
                            "whatever",
                            R.drawable.download1,
                            phone_number,
                            "5000",
                            id
                        )
                    )
                    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

                    rv.adapter = CoachAdapter(this, coach)
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }, {
            Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_LONG).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", Token)
                return headers
            }
        }
        queue.add(jsonObject)

        fabAdd.setOnClickListener {
            navigateToAddCoach()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)


        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                return true
            }

        }

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Type"

        searchView.setOnQueryTextListener(

            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {

                    tempCoach.clear()
                    val searchText = newText!!.lowercase(Locale.getDefault())

                    if (searchText.isNotEmpty()) {
                        coach.forEach {

                            if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                                tempCoach.add(it)
                            }
                        }
                        rv.adapter!!.notifyDataSetChanged()
                    } else {
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

                } else {
                    tempCoach.sortByDescending {
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