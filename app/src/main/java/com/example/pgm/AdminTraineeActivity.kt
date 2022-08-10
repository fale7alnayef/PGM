package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AdminTraineeActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    private lateinit var trainee: ArrayList<TraineeData>
    private lateinit var tempTrainee: ArrayList<TraineeData>
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_trainee)

        rv = findViewById(R.id.trecyclerView)
        fabAdd = findViewById(R.id.addtrainee)
        toolBar = findViewById(R.id.ttoolbar)
        toolBar.title = "Trainees"
        setSupportActionBar(toolBar)


        fabAdd.setOnClickListener {
            navigateToAddTrainee()
        }


        trainee = ArrayList()
        tempTrainee = ArrayList()
        val Token = "Bearer " + Data.Token
        val queue = Volley.newRequestQueue(this)
        val url = "http://${Data.url}:8000/api/admin/all_users"

        val jsonObject = @RequiresApi(Build.VERSION_CODES.O)
        object : JsonObjectRequest(Method.POST, url, null, {
            try {
                val traineearray = it.getJSONArray("users")

                for (i in 0 until traineearray.length()) {

                    val firstName = traineearray.getJSONObject(i).getString("first_name")
                    val lastName = traineearray.getJSONObject(i).getString("last_name")

                    val birthday = traineearray.getJSONObject(i).getString("birthday")
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val date = LocalDate.parse(birthday, formatter)
                    val age = (LocalDate.now().compareTo(date)).toString()

                    val height = traineearray.getJSONObject(i).getString("height")
                    val weight = traineearray.getJSONObject(i).getString("weight")
                    val phone = traineearray.getJSONObject(i).getString("phone_number")
                    val imgURL = traineearray.getJSONObject(i).getString("img_url")




                    trainee.add(
                        TraineeData(
                            "$firstName $lastName",
                            "",
                            R.drawable.download1,
                            age,
                            height,
                            weight,
                            phone
                        )
                    )

                    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    rv.adapter = TraineeAdapter(this, trainee)
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
            }

        }, {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", Token)
                return headers
            }
        }
        queue.add(jsonObject)

        tempTrainee.addAll(trainee)


    }

    private fun navigateToAddTrainee() {
        startActivity(Intent(applicationContext, AddTraineeActivity::class.java))

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

                    tempTrainee.clear()
                    val searchText = newText!!.lowercase(Locale.getDefault())

                    if (searchText.isNotEmpty()) {
                        trainee.forEach {

                            if (it.name.lowercase(Locale.getDefault()).contains(searchText)) {
                                tempTrainee.add(it)
                            }
                        }
                        rv.adapter!!.notifyDataSetChanged()
                    } else {
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

                } else {
                    tempTrainee.sortByDescending {
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